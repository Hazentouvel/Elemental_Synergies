package net.hazen.elemental_synergies.Entities.Weapons.Violence;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.damage.ISSDamageTypes;
import io.redspace.ironsspellbooks.registries.SoundRegistry;

import javax.annotation.Nullable;

import net.acetheeldritchking.aces_spell_utils.registries.ASDamageTypes;
import net.acetheeldritchking.aces_spell_utils.registries.ASAttributeRegistry;
import net.hazen.elemental_synergies.Registries.ESSounds;
import net.hazen.hazentouvelib.Registries.HLAttributeRegistry;

import net.hazen.elemental_synergies.Registries.ESEntityRegistry;
import net.hazen.elemental_synergies.Registries.ESEffectRegistry;
import net.minecraft.core.Holder;
import net.minecraft.world.damagesource.DamageType;
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

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class Violence extends AbstractArrow implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    private static final EntityDataAccessor<Boolean> ID_FOIL;
    private static final EntityDataAccessor<ItemStack> ID_ITEM;
    private boolean dealtDamage;
    public int clientSideReturnTridentTickCount;
    // removed slowDownCounter/shouldReturn; using continuous decay and returnStarted

    // New fields for improved behavior
    private int flightTicks = 0; // ticks since thrown (while in-flight)
    private double initialSpeed = 0.0; // captured initial speed on first flight tick
    private boolean returnStarted = false; // whether we've initiated the return-phase
    private final Set<UUID> hitEntities = new HashSet<>(); // track pierced targets

    public Violence(EntityType<? extends AbstractArrow> entityType, Level level) {
        super(entityType, level);
        this.setNoGravity(true);
        // previously called setPierceLevel which is private; we now implement piercing manually
    }

    public Violence(Level level, ItemStack spearitem, double damage) {
        this(ESEntityRegistry.VIOLENCE.get(), level);
        this.setBaseDamage(damage);
        this.setWeaponItem(spearitem);
        this.setNoGravity(true);
    }


    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(ID_FOIL, false);
        builder.define(ID_ITEM, ItemStack.EMPTY);
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
        this.entityData.set(ID_FOIL, itemStack.hasFoil());
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

    protected void onHit(HitResult result) {
        super.onHit(result);
        this.setSoundEvent(SoundEvents.TRIDENT_HIT_GROUND);

        // If we hit a block, begin returning to the owner
        if (result.getType() == HitResult.Type.BLOCK && !this.returnStarted) {
            this.returnStarted = true;
            // stop current movement and allow homing return logic to take over
            this.setDeltaMovement(Vec3.ZERO);
            this.setNoPhysics(true);
            // ensure next tick will set velocity toward owner and play return sound
            this.clientSideReturnTridentTickCount = 0;
            if (!this.level.isClientSide) {
                this.playSound(SoundEvents.TRIDENT_RETURN, 1.0F, 1.0F);
            }
        }
    }

    // Helper: when returning, manually check for entities in path and apply hits (server-side)
    private void checkReturnCollisions() {
        if (this.level.isClientSide) return;
        Entity owner = this.getOwner();
        if (owner == null) return;
        Vec3 motion = this.getDeltaMovement();
        if (motion.lengthSqr() <= 1e-6) return;

        // Expand bounding box along motion to look ahead for entities
        var box = this.getBoundingBox().expandTowards(motion.x, motion.y, motion.z).inflate(0.5D);
        List<LivingEntity> targets = this.level.getEntitiesOfClass(LivingEntity.class, box, e -> e.isAlive() && e != owner && !hitEntities.contains(e.getUUID()));
        for (LivingEntity target : targets) {
            // create an EntityHitResult and call onHitEntity to apply damage/effects
            EntityHitResult ehr = new EntityHitResult(target);
            this.onHitEntity(ehr);
        }
    }

    public void tick() {
        // Run base physics first so we can reliably modify deltaMovement after engine updates/collision
        super.tick();

        // If we land in the ground (sometimes onHit may not run), start return automatically
        if (this.inGround && !this.returnStarted) {
            this.returnStarted = true;
            this.setNoPhysics(true);
            this.clientSideReturnTridentTickCount = 0;
            if (!this.level.isClientSide) this.playSound(SoundEvents.TRIDENT_RETURN, 1.0F, 1.0F);
        }

        // If the entity is already in return mode, skip mid-air decay logic
        // Track flight ticks while not returning and not embedded
        if (!this.returnStarted && !this.inGround && !this.isNoPhysics()) {
            this.flightTicks++;
            if (this.flightTicks == 1) {
                // capture initial speed on first tick after spawn
                this.initialSpeed = this.getDeltaMovement().length();
                if (this.initialSpeed <= 0.001) this.initialSpeed = 2.5; // fallback
            }

            // Continuous mid-air decay: reduce speed each tick so projectile slows over its lifetime.
            // We pick a per-tick decay factor so it reduces noticeably over ~80 ticks.
            double decayPerTick = 0.98D; // tuned so 2.5 -> ~0.5 over ~80 ticks
            Vec3 vel = this.getDeltaMovement();
            this.setDeltaMovement(vel.scale(decayPerTick));

            // If speed drops below threshold, start return
            double returnSpeedThreshold = 0.6D; // when speed is low enough, start returning
            if (this.getDeltaMovement().length() <= returnSpeedThreshold || this.flightTicks >= 160) {
                this.returnStarted = true;
                this.setNoPhysics(true);
            }
        }

        Entity entity = this.getOwner();
        // Return behavior: only trigger when explicitly started (by low speed or timeout) or flagged as noPhysics
        if ((this.returnStarted || this.isNoPhysics()) && entity != null) {
            // ensure we are in return state
            if (!this.returnStarted) {
                this.returnStarted = true;
                this.setNoPhysics(true);
            }

            Vec3 vec3 = entity.getEyePosition().subtract(this.position());
            if (this.clientSideReturnTridentTickCount == 0) {
                Vec3 dir = vec3.normalize();
                double speed = Math.max(this.initialSpeed, 0.5);
                this.setDeltaMovement(dir.scale(speed));
                this.playSound(SoundEvents.TRIDENT_RETURN, 10.0F, 1.0F);
            }

            // apply homing acceleration for smoother tracking
            double homingAccel = Math.max(0.07, Math.min(0.5, this.initialSpeed * 0.05));
            this.setPosRaw(this.getX(), this.getY() + vec3.y * 0.015, this.getZ());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.96).add(vec3.normalize().scale(homingAccel)));

            if (this.level().isClientSide) {
                this.yOld = this.getY();
            }

            // While returning, perform server-side collision checks so it damages entities on the way back
            if (!this.level.isClientSide) {
                this.checkReturnCollisions();
            }

            Player player = this.level.getPlayerByUUID(entity.getUUID());
            if (player != null && player.distanceToSqr(this) < Math.clamp(this.getDeltaMovement().lengthSqr() * 3.0D, 4.0D, 25.0D)) {
                this.playerTouch(player);
            }

            ++this.clientSideReturnTridentTickCount;
        }
    }

     public boolean isFoil() {
         return (Boolean)this.entityData.get(ID_FOIL);
     }

     @Nullable
     protected EntityHitResult findHitEntity(Vec3 startVec, Vec3 endVec) {
         // allow piercing by using super to find the next entity along path; we handle duplicate hits in onHitEntity
         return super.findHitEntity(startVec, endVec);
     }

     protected void onHitEntity(EntityHitResult result) {
         Entity victim = result.getEntity();
         if (victim.getUUID() != null && hitEntities.contains(victim.getUUID())) {
             // already hit this target, ignore
             return;
         }
         if (victim.getUUID() != null) hitEntities.add(victim.getUUID());

         // Prevent hitting the owner
         Entity owner = this.getOwner();
         if (owner != null && victim.getUUID().equals(owner.getUUID())) return;

         float f = (float)this.getBaseDamage();

        Level var8 = this.level();
        if (var8 instanceof ServerLevel serverlevel) {
            // Prepare a generic damagesource for enchantment calculation (use trident style)
            DamageSource enchantCalcSource = this.damageSources().trident(this, (Entity)(owner == null ? this : owner));
            f = EnchantmentHelper.modifyDamage(serverlevel, this.getWeaponItem(), victim, enchantCalcSource, f);
        }

        boolean didHurt = false;

        double totalDamage = f;
        double baseHalf = totalDamage / 2.0D;
        int bonus = 0;
        if (owner instanceof LivingEntity livingOwner) {
            double firePower = livingOwner.getAttributeValue(AttributeRegistry.FIRE_SPELL_POWER);
            double bloodPower = livingOwner.getAttributeValue(AttributeRegistry.BLOOD_SPELL_POWER);
            double ritualPower = livingOwner.getAttributeValue(ASAttributeRegistry.RITUAL_MAGIC_POWER);
            double shadowPower = livingOwner.getAttributeValue(HLAttributeRegistry.SHADOW_SPELL_POWER);

            bonus += (int)Math.floor(firePower + bloodPower + ritualPower + shadowPower/ 0.2);
        }

        double computedTotal = baseHalf + bonus;

        double each = computedTotal / 1.5D;
        double remainder = computedTotal - each * 4.0D;

        // Create DamageSources for each type
        Holder<DamageType> ritualHolder = DamageSources.getHolderFromResource(victim, ASDamageTypes.RITUAL_MAGIC);
        DamageSource ritual = victim.level().damageSources().source(ritualHolder.unwrapKey().orElseThrow(), this.getOwner());

        Holder<DamageType> bloodHolder = DamageSources.getHolderFromResource(victim, ISSDamageTypes.BLOOD_MAGIC);
        DamageSource blood = victim.level().damageSources().source(bloodHolder.unwrapKey().orElseThrow(), this.getOwner());

        Holder<DamageType> fireHolder = DamageSources.getHolderFromResource(victim, ISSDamageTypes.FIRE_MAGIC);
        DamageSource fire = victim.level().damageSources().source(fireHolder.unwrapKey().orElseThrow(), this.getOwner());

        // For shadow damage we attempt to reuse ritual if a specific holder isn't available.
        DamageSource shadow = ritual;
        try {
            var shadowHolder = DamageSources.getHolderFromResource(victim, null);
        } catch (Exception ignored) {
        }

        // Apply the split damage types. Use DamageSources.applyDamage helper where available.
        if (victim instanceof LivingEntity) {
            // Apply the quartered damage with each type; add remainder to first (fire)
            DamageSources.applyDamage(victim, (float)(each + remainder), fire);
            DamageSources.applyDamage(victim, (float)each, blood);
            DamageSources.applyDamage(victim, (float)each, ritual);
            DamageSources.applyDamage(victim, (float)each, shadow);
            didHurt = true;
        }

        // Post hit effects
        if (didHurt) {
            if (victim.getType() == EntityType.ENDERMAN) {
                return;
            }

            if (var8 instanceof ServerLevel) {
                ServerLevel serverlevel1 = (ServerLevel)var8;
                EnchantmentHelper.doPostAttackEffectsWithItemSource(serverlevel1, victim, this.damageSources().trident(this, (Entity)(owner == null ? this : owner)), this.getWeaponItem());
            }

            if (victim instanceof LivingEntity) {
                LivingEntity livingentity = (LivingEntity)victim;
                try {
                    livingentity.addEffect(new MobEffectInstance(ESEffectRegistry.VULNERABILITY_HEX, 100, 0, false, true, true));
                } catch (Exception ignored) {
                }
                this.doKnockback(livingentity, this.damageSources().trident(this, (Entity)(owner == null ? this : owner)));
                this.doPostHurtEffects(livingentity);
            }

            this.playSound(ESSounds.VIOLENCE_IMPACT.get(), 1.5F, 1F);
        }
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
         // Allow pickup by owner even without loyalty enchantment; follow vanilla pickup rules otherwise
         if (!this.isRemoved() && this.getOwner() != null && this.ownedBy(player)) {
             int loyalty = this.getLoyaltyFromItem(this.getWeaponItem());
             if (player.hasInfiniteMaterials() && this.pickup == Pickup.CREATIVE_ONLY || !player.hasInfiniteMaterials() && this.pickup == Pickup.ALLOWED || this.pickup != Pickup.DISALLOWED) {
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
         this.initialSpeed = compound.getDouble("InitialSpeed");
         this.flightTicks = compound.getInt("FlightTicks");
     }

     public void addAdditionalSaveData(CompoundTag compound) {
         super.addAdditionalSaveData(compound);
         compound.putBoolean("DealtDamage", this.dealtDamage);
         compound.putDouble("InitialSpeed", this.initialSpeed);
         compound.putInt("FlightTicks", this.flightTicks);
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
         int i = this.getLoyaltyFromItem(this.getWeaponItem());
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
         ID_FOIL = SynchedEntityData.defineId(Violence.class, EntityDataSerializers.BOOLEAN);
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
