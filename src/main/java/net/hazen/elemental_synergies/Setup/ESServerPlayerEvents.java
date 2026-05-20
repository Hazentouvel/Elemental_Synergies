package net.hazen.elemental_synergies.Setup;

import com.gametechbc.gtbcs_geomancy_plus.api.init.GGAttributes;
import com.github.L_Ender.cataclysm.entity.effect.Lightning_Area_Effect_Entity;
import com.github.L_Ender.cataclysm.entity.effect.Lightning_Storm_Entity;
import com.github.L_Ender.cataclysm.init.ModEffect;
import com.github.L_Ender.cataclysm.init.ModParticle;
import com.github.L_Ender.cataclysm.util.CMDamageTypes;
import com.github.L_Ender.lionfishapi.server.event.StandOnFluidEvent;
import com.snackpirate.aeromancy.data.AADamageTypes;
import com.snackpirate.aeromancy.spells.AASpells;
import io.redspace.ironsspellbooks.api.entity.IMagicEntity;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.damage.ISSDamageTypes;
import io.redspace.ironsspellbooks.entity.spells.AbstractMagicProjectile;
import io.redspace.ironsspellbooks.entity.spells.ChainLightning;
import it.crystalnest.prometheus.api.FireManager;
import net.acetheeldritchking.aces_spell_utils.registries.ASAttributeRegistry;
import net.acetheeldritchking.aces_spell_utils.registries.ASDamageTypes;
import net.acetheeldritchking.cataclysm_spellbooks.registries.CSDamageTypes;
import net.hazen.elemental_synergies.ESUtilities.ESCompatAttribute;
import net.hazen.elemental_synergies.Items.Armor.AscensionTier.Providence.ProvidenceArmorItem;
import net.hazen.elemental_synergies.Items.Armor.AscensionTier.SupremeCalamitas.SupremeCalamitasArmorItem;
import net.hazen.elemental_synergies.Items.Armor.ParagonTier.Boss.Ignis.IgnisArmorItem;
import net.hazen.elemental_synergies.Items.Armor.ParagonTier.Boss.Scylla.ScyllaArmorItem;
import net.hazen.elemental_synergies.Items.Armor.ParagonTier.MultiSchool.SoulFlame.SoulFlameArmorItem;
import net.hazen.elemental_synergies.Registries.ESEffectRegistry;
import net.hazen.elemental_synergies.Registries.ESItemRegistry;
import net.hazen.elemental_synergies.Registries.ESSounds;
import net.hazen.hazentouvelib.Registries.HLAttributeRegistry;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.ItemAttributeModifierEvent;
import net.neoforged.neoforge.event.entity.ProjectileImpactEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

@EventBusSubscriber
public class ESServerPlayerEvents {
    public ESServerPlayerEvents() {
    }

    private static boolean isWearingFullProvidenceSet(LivingEntity entity) {
        return entity.getItemBySlot(ArmorItem.Type.HELMET.getSlot()).getItem() instanceof ProvidenceArmorItem &&
                entity.getItemBySlot(ArmorItem.Type.CHESTPLATE.getSlot()).getItem() instanceof ProvidenceArmorItem &&
                entity.getItemBySlot(ArmorItem.Type.LEGGINGS.getSlot()).getItem() instanceof ProvidenceArmorItem &&
                entity.getItemBySlot(ArmorItem.Type.BOOTS.getSlot()).getItem() instanceof ProvidenceArmorItem;
    }

    private static boolean isWearingFullSupremeCalamitasSet(LivingEntity entity) {
        return entity.getItemBySlot(ArmorItem.Type.HELMET.getSlot()).getItem() instanceof SupremeCalamitasArmorItem &&
                entity.getItemBySlot(ArmorItem.Type.CHESTPLATE.getSlot()).getItem() instanceof SupremeCalamitasArmorItem &&
                entity.getItemBySlot(ArmorItem.Type.LEGGINGS.getSlot()).getItem() instanceof SupremeCalamitasArmorItem &&
                entity.getItemBySlot(ArmorItem.Type.BOOTS.getSlot()).getItem() instanceof SupremeCalamitasArmorItem;
    }

    private static boolean isWearingFullSoulFlameSet(LivingEntity entity) {
        return entity.getItemBySlot(ArmorItem.Type.HELMET.getSlot()).getItem() instanceof SoulFlameArmorItem &&
                entity.getItemBySlot(ArmorItem.Type.CHESTPLATE.getSlot()).getItem() instanceof SoulFlameArmorItem &&
                entity.getItemBySlot(ArmorItem.Type.LEGGINGS.getSlot()).getItem() instanceof SoulFlameArmorItem &&
                entity.getItemBySlot(ArmorItem.Type.BOOTS.getSlot()).getItem() instanceof SoulFlameArmorItem;
    }

    private static boolean isWearingFullIgnisSet(LivingEntity entity) {
        return entity.getItemBySlot(ArmorItem.Type.HELMET.getSlot()).getItem() instanceof IgnisArmorItem &&
                entity.getItemBySlot(ArmorItem.Type.CHESTPLATE.getSlot()).getItem() instanceof IgnisArmorItem &&
                entity.getItemBySlot(ArmorItem.Type.LEGGINGS.getSlot()).getItem() instanceof IgnisArmorItem &&
                entity.getItemBySlot(ArmorItem.Type.BOOTS.getSlot()).getItem() instanceof IgnisArmorItem;
    }

    private static boolean isWearingFullScyllaSet(LivingEntity entity) {
        return entity.getItemBySlot(ArmorItem.Type.HELMET.getSlot()).getItem() instanceof ScyllaArmorItem &&
                entity.getItemBySlot(ArmorItem.Type.CHESTPLATE.getSlot()).getItem() instanceof ScyllaArmorItem &&
                entity.getItemBySlot(ArmorItem.Type.LEGGINGS.getSlot()).getItem() instanceof ScyllaArmorItem &&
                entity.getItemBySlot(ArmorItem.Type.BOOTS.getSlot()).getItem() instanceof ScyllaArmorItem;
    }


    @SubscribeEvent
    public static void onLivingIncomingDamage(LivingIncomingDamageEvent event) {
        LivingEntity livingEntity = event.getEntity();

        if ((livingEntity instanceof ServerPlayer || livingEntity instanceof IMagicEntity) && isWearingFullProvidenceSet(livingEntity) && event.getSource().is(DamageTypeTags.IS_FIRE)) {
            livingEntity.clearFire();
            event.setCanceled(true);
        }
        if (!event.getEntity().getItemBySlot(EquipmentSlot.LEGS).isEmpty() && event.getEntity().getItemBySlot(EquipmentSlot.LEGS).getItem() == ESItemRegistry.MALEDICTUS_LEGGINGS.get()) {
            if (event.getSource().is(DamageTypeTags.IS_PROJECTILE)) {
                if (event.getEntity().getRandom().nextFloat() < 0.15F) {
                    event.setCanceled(true);
                }
            } else if (!event.getSource().is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
                if (event.getEntity().getRandom().nextFloat() < 0.08F) {
                    event.setCanceled(true);
                }
            }
        }


    }


    @SubscribeEvent
    public static void onBeforeDamageTaken(LivingDamageEvent.Pre event) {
        LivingEntity target = event.getEntity();

        if (event.getSource().is(AADamageTypes.WIND_MAGIC)) {
            Entity attackerEntity = event.getSource().getEntity();

            if (attackerEntity instanceof LivingEntity livingAttacker) {
                Item helmet = livingAttacker.getItemBySlot(EquipmentSlot.HEAD).getItem();

                boolean isCloudmasterHat = helmet == ESItemRegistry.CLOUDMASTER_HAT.get();
                boolean isCloudmasterCrown = helmet == ESItemRegistry.CLOUDMASTER_CROWN.get();

                if (isCloudmasterHat || isCloudmasterCrown) {
                    if (livingAttacker instanceof Player player) {
                        if (player.getCooldowns().isOnCooldown(helmet)) {
                            return;
                        }

                        target.addEffect(new MobEffectInstance(AASpells.MobEffects.BREATHLESS, 60, 0));

                        int cooldownTicks = Utils.applyCooldownReduction(40, player);
                        player.getCooldowns().addCooldown(helmet, cooldownTicks);
                    } else {
                        target.addEffect(new MobEffectInstance(AASpells.MobEffects.BREATHLESS, 60, 0));
                    }
                }
            }
        }
        if (event.getSource().is(ASDamageTypes.HYDRO_MAGIC) || event.getSource().is(CSDamageTypes.ABYSSAL_MAGIC) || event.getSource().is(CMDamageTypes.PLAYER_CERAUNUS)) {
            Entity attackerEntity = event.getSource().getEntity();

            if (attackerEntity instanceof LivingEntity livingAttacker) {
                Item chestplate = livingAttacker.getItemBySlot(EquipmentSlot.CHEST).getItem();

                boolean scyllaChestplate = chestplate == ESItemRegistry.SCYLLA_CHESTPLATE.get();

                if (scyllaChestplate) {
                    if (livingAttacker instanceof Player player) {
                        if (player.getCooldowns().isOnCooldown(chestplate)) {
                            return;
                        }

                        target.addEffect(new MobEffectInstance(ModEffect.EFFECTWETNESS, 60, 0));

                    } else {
                        target.addEffect(new MobEffectInstance(ModEffect.EFFECTWETNESS, 60, 0));
                    }
                }
            }
        }
        if (event.getSource().is(ISSDamageTypes.LIGHTNING_MAGIC) || event.getSource().is(CMDamageTypes.LIGHTNING) || event.getSource().is(DamageTypeTags.IS_LIGHTNING)) {
            Entity attackerEntity = event.getSource().getEntity();

            if (attackerEntity instanceof LivingEntity livingAttacker) {
                Item chestplate = livingAttacker.getItemBySlot(EquipmentSlot.CHEST).getItem();

                boolean scyllaChestplate = chestplate == ESItemRegistry.SCYLLA_CHESTPLATE.get();

                if (scyllaChestplate && target.hasEffect(ModEffect.EFFECTWETNESS)) {

                    if (!livingAttacker.level().isClientSide()
                            && target.level() instanceof ServerLevel serverLevel) {

                        if (livingAttacker instanceof Player player) {
                            if (player.getCooldowns().isOnCooldown(chestplate)) {
                                return;
                            }

                            player.getCooldowns().addCooldown(chestplate, 120);
                        }

                        ChainLightning chainLightning = new ChainLightning(serverLevel, livingAttacker, target);

                        float lightningPower = 1.0F;

                        try {
                            lightningPower = (float) livingAttacker.getAttributeValue(AttributeRegistry.LIGHTNING_SPELL_POWER);
                        } catch (Exception ignored) {
                        }

                        float chainDamage = 5.0F + (event.getNewDamage() * lightningPower);

                        chainLightning.setDamage(chainDamage);

                        Vec3 targetPos = target.position();

                        Lightning_Storm_Entity lightningStorm = new Lightning_Storm_Entity(serverLevel, targetPos.x, targetPos.y, targetPos.z, 0.0F, 8, chainDamage, 0.02F, livingAttacker, 2.5F);
                        serverLevel.addFreshEntity(lightningStorm);

                        chainLightning.range = 6.0F;
                        chainLightning.maxConnections = 6;
                        chainLightning.maxConnectionsPerWave = 2;

                        serverLevel.addFreshEntity(chainLightning);

                        Vec3 center = target.position().add(0, target.getBbHeight() * 0.5F, 0);


                       }

                }

            }
        }


    }

    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent.Post event) {

        if (!event.getEntity().getItemBySlot(EquipmentSlot.LEGS).isEmpty() && event.getSource() != null && event.getSource().getEntity() != null) {
            if(event.getEntity().getItemBySlot(EquipmentSlot.LEGS).getItem() == ESItemRegistry.IGNIS_LEGGINGS.get()){
                Entity attacker = event.getSource().getEntity();
                if (attacker instanceof LivingEntity && attacker != event.getEntity()) {
                    if (event.getEntity().getRandom().nextFloat() < 0.5F) {
                        MobEffectInstance effectinstance = new MobEffectInstance(ModEffect.EFFECTBLAZING_BRAND, 100, 0, false, false, true);
                        ((LivingEntity) attacker).addEffect(effectinstance);

                        if (!attacker.isOnFire()) {
                            attacker.igniteForSeconds(5);
                        }
                    }
                }
            }
        }

    }

    @SubscribeEvent
    public static void DeathEvent(LivingDeathEvent event) {
        DamageSource source = event.getSource();
        if (!source.is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
            if(tryCursiumPlateRebirth(event.getEntity())){
                event.setCanceled(true);
            }

        }
    }

    private static boolean tryCursiumPlateRebirth(LivingEntity living) {
        ItemStack chestplate = living.getItemBySlot(EquipmentSlot.CHEST);
        if ((living.level() instanceof ServerLevel serverLevel)&& chestplate.getItem() == ESItemRegistry.MALEDICTUS_CHESTPLATE.get() && !living.hasEffect(ModEffect.EFFECTGHOST_SICKNESS) && !living.hasEffect(ModEffect.EFFECTGHOST_FORM)) {
            living.setHealth(7.5F);
            serverLevel.playSound(
                    null,
                    living.getX(), living.getY(), living.getZ(),
                    ESSounds.MALEDICTUS_ARMOR_REVIVE,
                    living.getSoundSource(),
                    1.25f,
                    1.0F
            );
            living.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 200, 0));
            living.addEffect(new MobEffectInstance(ModEffect.EFFECTGHOST_FORM, 100, 0, false, true, true));
            double d0 = living.getX();
            double d1 = living.getY() + 3F;
            double d2 = living.getZ();
            serverLevel.sendParticles(ModParticle.CURSED_ALGIZ.get(), d0, d1, d2, 1, 0.0, 0, 0.0, 0);
            return true;
        }
        return false;
    }


    @EventBusSubscriber
    public static class setBonuses {

        @SubscribeEvent
        public static void providenceSetBonus(LivingDamageEvent.Post event) {

            LivingEntity target = event.getEntity();
            Entity sourceEntity = event.getSource().getEntity();

            if (!(sourceEntity instanceof LivingEntity attacker)) return;

            if (!isWearingFullProvidenceSet(attacker)) return;

            if (attacker.level().isClientSide) return;

            Item head = attacker.getItemBySlot(EquipmentSlot.HEAD).getItem();
            if (attacker instanceof Player player) {
                if (player.getCooldowns().isOnCooldown(head)) {
                    return;
                }
                player.getCooldowns().addCooldown(head, 300);
            }

            int amplifier = 0;

            try {
                double attrVal = 0.0;
                // Sum multiple spell power attributes (HOLY, GEO, RADIANCE) if available
                try {
                    attrVal += attacker.getAttributeValue(AttributeRegistry.HOLY_SPELL_POWER);
                } catch (IllegalArgumentException ex) {
                }
                try {
                    attrVal += attacker.getAttributeValue(GGAttributes.GEO_SPELL_POWER);
                } catch (IllegalArgumentException ex) {
                }
                try {
                    attrVal += attacker.getAttributeValue(HLAttributeRegistry.RADIANCE_SPELL_POWER);
                } catch (IllegalArgumentException ex) {
                }
                try {
                    attrVal += attacker.getAttributeValue(AttributeRegistry.FIRE_SPELL_POWER);
                } catch (IllegalArgumentException ex) {
                }

                double percent;
                if (attrVal <= 0.0) {
                    percent = 0.0;
                } else if (attrVal < 10.0) {
                    percent = attrVal * 100.0;
                } else {
                    percent = attrVal;
                }

                int fullHundreds = (int)Math.floor(percent / 200.0);
                amplifier = Math.max(0, fullHundreds - 1);
            } catch (Throwable t) {
                amplifier = 0;
            }

            boolean inNightState = attacker.hasEffect(ESEffectRegistry.NIGHT_STATE);

            if (inNightState) {
                target.addEffect(new MobEffectInstance(ESEffectRegistry.NIGHT_WITHER, 100, amplifier, false, true, true));
            } else {
                target.addEffect(new MobEffectInstance(ESEffectRegistry.HOLY_FLAMES, 100, amplifier, false, true, true));
            }
        }
        @SubscribeEvent
        public static void supremeCalamitasSetBonus(LivingDamageEvent.Post event) {

            LivingEntity target = event.getEntity();
            Entity sourceEntity = event.getSource().getEntity();

            if (!(sourceEntity instanceof LivingEntity attacker)) return;

            if (!isWearingFullSupremeCalamitasSet(attacker)) return;

            if (attacker.level().isClientSide) return;

            Item head = attacker.getItemBySlot(EquipmentSlot.HEAD).getItem();
            if (attacker instanceof Player player) {
                if (player.getCooldowns().isOnCooldown(head)) {
                    return;
                }
                player.getCooldowns().addCooldown(head, 300);
            }

            int amplifier = 0;

            try {
                double attrVal = 0.0;
                // Sum multiple spell power attributes (HOLY, GEO, RADIANCE) if available
                try {
                    attrVal += attacker.getAttributeValue(AttributeRegistry.BLOOD_SPELL_POWER);
                } catch (IllegalArgumentException ex) {
                }
                try {
                    attrVal += attacker.getAttributeValue(ASAttributeRegistry.RITUAL_MAGIC_POWER);
                } catch (IllegalArgumentException ex) {
                }
                try {
                    attrVal += attacker.getAttributeValue(HLAttributeRegistry.SHADOW_SPELL_POWER);
                } catch (IllegalArgumentException ex) {
                }
                try {
                    attrVal += attacker.getAttributeValue(AttributeRegistry.FIRE_SPELL_POWER);
                } catch (IllegalArgumentException ex) {
                }

                double percent;
                if (attrVal <= 0.0) {
                    percent = 0.0;
                } else if (attrVal < 10.0) {
                    percent = attrVal * 100.0;
                } else {
                    percent = attrVal;
                }

                int fullHundreds = (int) Math.floor(percent / 200.0);
                amplifier = Math.max(0, fullHundreds - 1);
            } catch (Throwable t) {
                amplifier = 0;
            }

            boolean inBrimstoneState = attacker.hasEffect(ESEffectRegistry.BRIMSTONE_STATE);

            if (inBrimstoneState) {
                target.addEffect(new MobEffectInstance(ESEffectRegistry.VULNERABILITY_HEX, 100, amplifier, false, true, true));
            } else {
                target.addEffect(new MobEffectInstance(ESEffectRegistry.BRIMSTONE_FLAMES, 100, amplifier, false, true, true));
            }
        }

        @SubscribeEvent
        public static void soulFlameSetBonus(LivingDamageEvent.Post event) {

            LivingEntity target = event.getEntity();
            Entity sourceEntity = event.getSource().getEntity();

            if (!(sourceEntity instanceof LivingEntity attacker)) return;

            if (!isWearingFullSoulFlameSet(attacker)) return;

            if (attacker.level().isClientSide) return;

            Item head = attacker.getItemBySlot(EquipmentSlot.HEAD).getItem();

            if (attacker instanceof Player player) {
                if (player.getCooldowns().isOnCooldown(head)) {
                    return;
                }

                player.getCooldowns().addCooldown(head, 60);
            }

            int soulFireTicks = 100;

            FireManager.setOnFire(target, soulFireTicks / 20f, FireManager.SOUL_FIRE_TYPE);
        }

        @SubscribeEvent
        public static void ignisSetBonus(LivingDamageEvent.Post event) {

            LivingEntity target = event.getEntity();
            Entity sourceEntity = event.getSource().getEntity();

            if (!(sourceEntity instanceof LivingEntity attacker)) return;

            // Must wear full Ignis set
            if (!isWearingFullIgnisSet(attacker)) return;
            if (!attacker.hasEffect(ESEffectRegistry.IGNIS_SOUL_STATE)) return;
            if (attacker.level().isClientSide) return;

            Item head = attacker.getItemBySlot(EquipmentSlot.HEAD).getItem();

            // Cooldown handling
            if (attacker instanceof Player player) {
                if (player.getCooldowns().isOnCooldown(head)) {
                    return;
                }
                player.getCooldowns().addCooldown(head, 60);
            }
            // Apply soul fire
            int soulFireTicks = 100;

            FireManager.setOnFire(target, soulFireTicks / 20f, FireManager.SOUL_FIRE_TYPE);
        }

        @SubscribeEvent
        public static void scyllaLightningBonus(ProjectileImpactEvent event) {
            if (!(event.getRayTraceResult() instanceof BlockHitResult blockHit)) {
                return;
            }
            Projectile projectile = event.getProjectile();
            if (!(projectile instanceof AbstractMagicProjectile magicProjectile)) {
                return;
            }
            DamageSource damageSource = magicProjectile.damageSources().source(
                    ISSDamageTypes.LIGHTNING_MAGIC,
                    magicProjectile,
                    magicProjectile.getOwner()
            );
            if (!damageSource.is(ISSDamageTypes.LIGHTNING_MAGIC) || !damageSource.is(DamageTypeTags.IS_LIGHTNING) || !damageSource.is(CMDamageTypes.LIGHTNING)) {
                return;
            }

            Entity owner = projectile.getOwner();

            if (!(owner instanceof LivingEntity livingAttacker)) {
                return;
            }

            if (!isWearingFullScyllaSet(livingAttacker)) {
                return;
            }

            if (!(livingAttacker.level() instanceof ServerLevel serverLevel)) {
                return;
            }

            Item helmet = livingAttacker.getItemBySlot(EquipmentSlot.HEAD).getItem();

            if (livingAttacker instanceof Player player) {

                if (player.getCooldowns().isOnCooldown(helmet)) {
                    return;
                }

                player.getCooldowns().addCooldown(helmet, 80);
            }

            Vec3 pos = blockHit.getLocation();

            float lightningPower = 1.0F;

            try {
                lightningPower = (float) livingAttacker.getAttributeValue(
                        AttributeRegistry.LIGHTNING_SPELL_POWER
                );
            } catch (Exception ignored) {
            }

            float damage = 4.0F + lightningPower;

            Lightning_Area_Effect_Entity areaEffect = new Lightning_Area_Effect_Entity(serverLevel, pos.x, pos.y, pos.z);

            areaEffect.setOwner(livingAttacker);
            areaEffect.setRadius(4.0F);
            areaEffect.setRadiusOnUse(-0.5F);
            areaEffect.setWaitTime(8);
            areaEffect.setDuration(40);
            areaEffect.setRadiusPerTick(-areaEffect.getRadius() / areaEffect.getDuration());

            serverLevel.addFreshEntity(areaEffect);

            // Lightning storm
            Lightning_Storm_Entity lightningStorm = new Lightning_Storm_Entity(serverLevel, pos.x, pos.y, pos.z, 0.0F, 8, damage, 0.02F, livingAttacker, 2.5F);

            serverLevel.addFreshEntity(lightningStorm);
        }

    }


    @SubscribeEvent
    public static void StandOnFluidEventEvent(StandOnFluidEvent event) {
        if (!event.getEntity().getItemBySlot(EquipmentSlot.FEET).isEmpty() && event.getEntity().getItemBySlot(EquipmentSlot.FEET).getItem() == ESItemRegistry.IGNIS_BOOTS.get()) {
            if (!event.getEntity().isShiftKeyDown() && (event.getFluidState().is(Fluids.LAVA) || event.getFluidState().is(Fluids.FLOWING_LAVA))) {
                event.setCanceled(true);
            }
        }
        if (!event.getEntity().getItemBySlot(EquipmentSlot.FEET).isEmpty() && event.getEntity().getItemBySlot(EquipmentSlot.FEET).getItem() == ESItemRegistry.SUPREME_CALAMITAS_BOOTS.get()) {
            if (!event.getEntity().isShiftKeyDown() && (event.getFluidState().is(Fluids.LAVA) || event.getFluidState().is(Fluids.FLOWING_LAVA))) {
                event.setCanceled(true);
            }
        }
        if (!event.getEntity().getItemBySlot(EquipmentSlot.FEET).isEmpty() && event.getEntity().getItemBySlot(EquipmentSlot.FEET).getItem() == ESItemRegistry.PROVIDENCE_BOOTS.get()) {
            if (!event.getEntity().isShiftKeyDown() && (event.getFluidState().is(Fluids.LAVA) || event.getFluidState().is(Fluids.FLOWING_LAVA))) {
                event.setCanceled(true);
            }
        }
        if (!event.getEntity().getItemBySlot(EquipmentSlot.FEET).isEmpty() && event.getEntity().getItemBySlot(EquipmentSlot.FEET).getItem() == ESItemRegistry.SCYLLA_BOOTS.get()) {
            if (!event.getEntity().isShiftKeyDown() && (event.getFluidState().is(Fluids.WATER) || event.getFluidState().is(Fluids.FLOWING_WATER))) {
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public static void giveItemAttributes(ItemAttributeModifierEvent event) {
        ESCompatAttribute.addIgnitiumArmorAttributes(event);
        ESCompatAttribute.addIgnitiumChestplateArmorAttributes(event);
        ESCompatAttribute.addCursiumArmorAttributes(event);
        ESCompatAttribute.addCursiumChestplateArmorAttributes(event);
        ESCompatAttribute.addSoulFireScytheAttributes(event);
        ESCompatAttribute.addForsakenFlambergeAttributes(event);
        ESCompatAttribute.addStaffOfVehemenceAttributes(event);
        ESCompatAttribute.addAwakenedWeaponAttributes(event);
        ESCompatAttribute.addCoralStaffAttributes(event);
        ESCompatAttribute.addBloomStoneAttributes(event);
        ESCompatAttribute.addIceCrystalAttributes(event);
        ESCompatAttribute.addCoralAttributes(event);
        ESCompatAttribute.addKhopeshAttributes(event);
        ESCompatAttribute.addGauntletOfGuardAttributes(event);
        ESCompatAttribute.addGauntletOfBulwarkAttributes(event);
        ESCompatAttribute.addGauntletOfMaelstromAttributes(event);
        ESCompatAttribute.addMeatShredderAttributes(event);
        ESCompatAttribute.addImmolatorAttributes(event);
        ESCompatAttribute.addTidalClawsAttributes(event);
        ESCompatAttribute.addInfernalForgeAttributes(event);
        ESCompatAttribute.addVoidForgeAttributes(event);
        ESCompatAttribute.addVoidCoreAttributes(event);
        ESCompatAttribute.addAstrapeAttributes(event);
        ESCompatAttribute.addAncientSpearAttributes(event);
        ESCompatAttribute.addCeranusAttributes(event);
        ESCompatAttribute.addBrontesAttributes(event);
    }
    
}
