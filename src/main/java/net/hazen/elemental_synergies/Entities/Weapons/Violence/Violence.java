package net.hazen.elemental_synergies.Entities.Weapons.Violence;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.damage.ISSDamageTypes;
import io.redspace.ironsspellbooks.registries.EntityRegistry;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import io.redspace.ironsspellbooks.util.ParticleHelper;
import javax.annotation.Nullable;

import net.hazen.elemental_synergies.Registries.ESEntityRegistry;
import net.hazen.elemental_synergies.Registries.ESEffectRegistry;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

public class Violence extends AbstractArrow implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    private static final EntityDataAccessor<Byte> ID_LOYALTY;
    private static final EntityDataAccessor<Boolean> ID_FOIL;
    private static final EntityDataAccessor<Boolean> ID_CHANNELED;
    private static final EntityDataAccessor<ItemStack> ID_ITEM;
    private boolean dealtDamage;
    public int clientSideReturnTridentTickCount;
    // Slowdown timer state: -1 = not started, >0 = slowing ticks remaining
    private int slowDownCounter = -1;
    // Explicit flag to start return behavior regardless of loyalty
    private boolean shouldReturn = false;

    public Violence(EntityType<? extends AbstractArrow> entityType, Level level) {
        super(entityType, level);
        // This projectile should not be affected by gravity
        this.setNoGravity(true);
    }

    public Violence(Level level, ItemStack spearitem, double damage) {
        this(ESEntityRegistry.VIOLENCE.get(), level);
        this.setBaseDamage(damage);
        this.setWeaponItem(spearitem);
        // Ensure no gravity when constructed via convenience ctor
        this.setNoGravity(true);
    }

    public boolean isChanneled() {
        return (Boolean)this.entityData.get(ID_CHANNELED);
    }

    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(ID_LOYALTY, (byte)0);
        builder.define(ID_FOIL, false);
        builder.define(ID_ITEM, ItemStack.EMPTY);
        builder.define(ID_CHANNELED, false);
    }

    protected void setPickupItemStack(ItemStack pickupItemStack) {
        if (!pickupItemStack.isEmpty()) {
            this.setWeaponItem(pickupItemStack);
        } else {
            super.setPickupItemStack(pickupItemStack);
        }

    }

    public void setWeaponItem(ItemStack itemStack) {
        this.entityData.set(ID_ITEM, itemStack);
        this.entityData.set(ID_LOYALTY, this.getLoyaltyFromItem(itemStack));
        this.entityData.set(ID_FOIL, itemStack.hasFoil());
        this.entityData.set(ID_CHANNELED, Utils.getEnchantmentLevel(this.level, itemStack, Enchantments.CHANNELING) > 0);
    }

    protected ItemStack getPickupItem() {
        return this.getWeaponItem();
    }

    public ItemStack getPickupItemStackOrigin() {
        return this.getWeaponItem();
    }

    public ItemStack getWeaponItem() {
        return (ItemStack)this.entityData.get(ID_ITEM);
    }

    public void tick() {
        if (this.inGroundTime > 4) {
            this.dealtDamage = true;
        }

        // After 4 seconds (80 ticks) start slowing down, then trigger return
        if (!this.level.isClientSide) {
            if (this.tickCount >= 80 && !this.dealtDamage && !this.isNoPhysics()) {
                if (this.slowDownCounter == -1) {
                    this.slowDownCounter = 20; // slow for 20 ticks before returning
                }
            }

            if (this.slowDownCounter > 0) {
                // reduce motion to create slowing effect
                Vec3 vel = this.getDeltaMovement();
                this.setDeltaMovement(vel.scale(0.85));
                this.slowDownCounter--;
                if (this.slowDownCounter == 0) {
                    // trigger return behavior; mark shouldReturn so it will return even without loyalty
                    this.shouldReturn = true;
                    this.setNoPhysics(true);
                }
            }
        }

        Entity entity = this.getOwner();
        int loyalty = (Byte)this.entityData.get(ID_LOYALTY);
        // Allow return when we've dealt damage OR we're flagged as noPhysics OR when a slowdown requested return
        if ((this.dealtDamage || this.isNoPhysics() || this.shouldReturn) && entity != null) {
            this.setNoPhysics(true);
            Vec3 vec3 = entity.getEyePosition().subtract(this.position());
            // use a fallback loyalty multiplier so the projectile actually moves if loyalty == 0
            int effectiveLoyalty = Math.max(1, loyalty);
            this.setPosRaw(this.getX(), this.getY() + vec3.y * 0.015 * (double)effectiveLoyalty, this.getZ());
            if (this.level().isClientSide) {
                this.yOld = this.getY();
            }

            double d0 = 0.07 * (double)effectiveLoyalty;
            this.setDeltaMovement(this.getDeltaMovement().scale(0.95).add(vec3.normalize().scale(d0)));
            if (this.clientSideReturnTridentTickCount == 0) {
                this.setDeltaMovement(Vec3.ZERO);
                this.playSound(SoundEvents.TRIDENT_RETURN, 10.0F, 1.0F);
            }

            Player player = this.level.getPlayerByUUID(entity.getUUID());
            if (player != null && player.distanceToSqr(this) < Math.clamp(this.getDeltaMovement().lengthSqr() * (double)3.0F, (double)4.0F, (double)25.0F)) {
                this.playerTouch(player);
            }

            ++this.clientSideReturnTridentTickCount;
        }

        super.tick();
    }

    public boolean isFoil() {
        return (Boolean)this.entityData.get(ID_FOIL);
    }

    @Nullable
    protected EntityHitResult findHitEntity(Vec3 startVec, Vec3 endVec) {
        return this.dealtDamage ? null : super.findHitEntity(startVec, endVec);
    }

    protected void onHit(HitResult result) {
        if (this.isChanneled() && !this.level.isClientSide && !this.dealtDamage) {
            this.playSound((SoundEvent)SoundRegistry.SPEAR_CHANNELING_STRIKE.get(), 6.0F, 0.9F + (float)Utils.random.nextInt(20) * 0.01F);
            MagicManager.spawnParticles(this.level, ParticleHelper.ELECTRICITY, this.getX(), this.getY(), this.getZ(), 75, 0.1, 0.1, 0.1, (double)2.0F, true);
            MagicManager.spawnParticles(this.level, ParticleHelper.ELECTRICITY, this.getX(), this.getY(), this.getZ(), 75, 0.1, 0.1, 0.1, (double)0.5F, false);
        }

        super.onHit(result);
        this.setSoundEvent(SoundEvents.TRIDENT_HIT_GROUND);
    }

    protected void onHitEntity(EntityHitResult result) {
        Entity victim = result.getEntity();
        float f = (float)this.getBaseDamage();
        Entity owner = this.getOwner();
        boolean channeled = this.isChanneled();
        DamageSource damagesource = channeled ? this.damageSources().source(ISSDamageTypes.LIGHTNING_MAGIC, this, (Entity)(owner == null ? this : owner)) : this.damageSources().trident(this, (Entity)(owner == null ? this : owner));
        Level var8 = this.level();
        if (var8 instanceof ServerLevel serverlevel) {
            f = EnchantmentHelper.modifyDamage(serverlevel, this.getWeaponItem(), victim, damagesource, f);
        }

        if (channeled && owner instanceof LivingEntity livingOwner) {
            f *= (float)livingOwner.getAttributeValue(AttributeRegistry.LIGHTNING_SPELL_POWER);
        }

        this.dealtDamage = true;
        if (victim.hurt(damagesource, f)) {
            if (victim.getType() == EntityType.ENDERMAN) {
                return;
            }

            var8 = this.level();
            if (var8 instanceof ServerLevel) {
                ServerLevel serverlevel1 = (ServerLevel)var8;
                EnchantmentHelper.doPostAttackEffectsWithItemSource(serverlevel1, victim, damagesource, this.getWeaponItem());
            }

            if (victim instanceof LivingEntity) {
                LivingEntity livingentity = (LivingEntity)victim;
                // Apply Invulnerability Hex on hit
                try {
                    livingentity.addEffect(new MobEffectInstance(ESEffectRegistry.VULNERABILITY_HEX, 100, 0, false, true, true));
                } catch (Exception ignored) {
                }
                this.doKnockback(livingentity, damagesource);
                this.doPostHurtEffects(livingentity);
            }
        }

        this.setDeltaMovement(this.getDeltaMovement().multiply(-0.03, -0.1, -0.03));
        this.playSound(SoundEvents.TRIDENT_HIT, 1.0F, 0.7F);
    }

    protected void hitBlockEnchantmentEffects(ServerLevel level, BlockHitResult hitResult, ItemStack stack) {
        Vec3 vec3 = hitResult.getBlockPos().clampLocationWithin(hitResult.getLocation());
        Entity var6 = this.getOwner();
        LivingEntity var10002;
        if (var6 instanceof LivingEntity livingentity) {
            var10002 = livingentity;
        } else {
            var10002 = null;
        }

        EnchantmentHelper.onHitBlock(level, stack, var10002, this, (EquipmentSlot)null, vec3, level.getBlockState(hitResult.getBlockPos()), (p_348680_) -> this.kill());
    }

    protected boolean tryPickup(Player player) {
        if (!this.isRemoved() && this.getOwner() != null && this.ownedBy(player)) {
            int loyalty = (Byte)this.entityData.get(ID_LOYALTY);
            if (player.hasInfiniteMaterials() && this.pickup == Pickup.CREATIVE_ONLY || !player.hasInfiniteMaterials() && this.pickup == Pickup.ALLOWED || this.pickup != Pickup.DISALLOWED && loyalty > 0) {
                player.getCooldowns().removeCooldown(this.getPickupItem().getItem());
                if (loyalty > 0) {
                    this.playSound((SoundEvent)SoundRegistry.SPEAR_RETURN.get());
                }

                return true;
            }
        }

        return false;
    }

    protected ItemStack getDefaultPickupItem() {
        return new ItemStack(Items.TRIDENT);
    }

    protected SoundEvent getDefaultHitGroundSoundEvent() {
        return SoundEvents.TRIDENT_HIT_GROUND;
    }

    public void playerTouch(Player entity) {
        if (this.ownedBy(entity) || this.getOwner() == null) {
            super.playerTouch(entity);
        }

    }

    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.dealtDamage = compound.getBoolean("DealtDamage");
    }

    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("DealtDamage", this.dealtDamage);
        compound.put("item", this.getWeaponItem().save(this.registryAccess()));
        compound.remove("weapon");
    }

    private byte getLoyaltyFromItem(ItemStack stack) {
        Level var3 = this.level();
        byte var10000;
        if (var3 instanceof ServerLevel serverlevel) {
            var10000 = (byte)Mth.clamp(EnchantmentHelper.getTridentReturnToOwnerAcceleration(serverlevel, stack, this), 0, 127);
        } else {
            var10000 = 0;
        }

        return var10000;
    }

    public void tickDespawn() {
        int i = (Byte)this.entityData.get(ID_LOYALTY);
        if (this.pickup != Pickup.ALLOWED || i <= 0) {
            super.tickDespawn();
        }

    }

    protected float getWaterInertia() {
        return 0.99F;
    }

    public boolean shouldRender(double x, double y, double z) {
        return true;
    }

    static {
        ID_LOYALTY = SynchedEntityData.defineId(Violence.class, EntityDataSerializers.BYTE);
        ID_FOIL = SynchedEntityData.defineId(Violence.class, EntityDataSerializers.BOOLEAN);
        ID_CHANNELED = SynchedEntityData.defineId(Violence.class, EntityDataSerializers.BOOLEAN);
        ID_ITEM = SynchedEntityData.defineId(Violence.class, EntityDataSerializers.ITEM_STACK);
    }


    //ANIMATION
    private final RawAnimation idle = RawAnimation.begin().thenLoop("idle");

    private PlayState predicate(AnimationState event) {
        event.getController().setAnimation(idle);
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<GeoAnimatable>(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}
