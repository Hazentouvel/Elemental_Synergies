package net.hazen.elemental_synergies.Setup;

import com.gametechbc.gtbcs_geomancy_plus.api.init.GGAttributes;
import com.snackpirate.aeromancy.data.AADamageTypes;
import com.snackpirate.aeromancy.spells.AASpells;
import io.redspace.ironsspellbooks.api.entity.IMagicEntity;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.util.Utils;
import net.acetheeldritchking.aces_spell_utils.registries.ASAttributeRegistry;
import net.hazen.elemental_synergies.Registries.ESEffectRegistry;
import net.hazen.elemental_synergies.Registries.ESItemRegistry;
import net.hazen.hazennstuff.Registries.HnSAttributeRegistry;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

@EventBusSubscriber
public class ESServerPlayerEvents {
    public ESServerPlayerEvents() {
    }

    private static boolean isWearingFullProvidenceSet(LivingEntity entity) {
        return entity.getItemBySlot(EquipmentSlot.HEAD).getItem() == ESItemRegistry.PROVIDENCE_HELMET.get()
                && entity.getItemBySlot(EquipmentSlot.CHEST).getItem() == ESItemRegistry.PROVIDENCE_CHESTPLATE.get()
                && entity.getItemBySlot(EquipmentSlot.LEGS).getItem() == ESItemRegistry.PROVIDENCE_LEGGINGS.get()
                && entity.getItemBySlot(EquipmentSlot.FEET).getItem() == ESItemRegistry.PROVIDENCE_BOOTS.get();
    }

    private static boolean isWearingFullSupremeCalamitasSet(LivingEntity entity) {
        return entity.getItemBySlot(EquipmentSlot.HEAD).getItem() == ESItemRegistry.PROVIDENCE_HELMET.get()
                && entity.getItemBySlot(EquipmentSlot.CHEST).getItem() == ESItemRegistry.PROVIDENCE_CHESTPLATE.get()
                && entity.getItemBySlot(EquipmentSlot.LEGS).getItem() == ESItemRegistry.PROVIDENCE_LEGGINGS.get()
                && entity.getItemBySlot(EquipmentSlot.FEET).getItem() == ESItemRegistry.PROVIDENCE_BOOTS.get();
    }


    @SubscribeEvent
    public static void onLivingIncomingDamage(LivingIncomingDamageEvent event) {
        LivingEntity livingEntity = event.getEntity();

        if ((livingEntity instanceof ServerPlayer || livingEntity instanceof IMagicEntity) && isWearingFullProvidenceSet(livingEntity) && event.getSource().is(DamageTypeTags.IS_FIRE)) {
            livingEntity.clearFire();
            event.setCanceled(true);
        }
    }


    @SubscribeEvent
    public static void onBeforeDamageTaken(LivingDamageEvent.Pre event) {
        LivingEntity target = event.getEntity();

        if (target instanceof IMagicEntity || target instanceof ServerPlayer) {
            MagicData magicData = MagicData.getPlayerMagicData(target);
        }

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

                        target.addEffect(new MobEffectInstance(AASpells.MobEffects.BREATHLESS, 60, 1));

                        int cooldownTicks = Utils.applyCooldownReduction(40, player);
                        player.getCooldowns().addCooldown(helmet, cooldownTicks);
                    } else {
                        target.addEffect(new MobEffectInstance(AASpells.MobEffects.BREATHLESS, 60, 1));
                    }
                }
            }
        }
    }

    @EventBusSubscriber
    public static class SupremeCalamitasHitEffects {

        @SubscribeEvent
        public static void onLivingDamage(LivingDamageEvent.Post event) {

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
                double totalPercent = 0.0;

                // Helper to normalize attribute → percent
                java.util.function.Function<Double, Double> normalize = val -> {
                    if (val <= 0.0) return 0.0;
                    if (val < 10.0) return val * 100.0; // multiplier → percent
                    return val; // already percent
                };

                double fire = 0.0;
                double blood = 0.0;
                double shadow = 0.0;
                double ritual = 0.0;

                try {
                    fire = attacker.getAttributeValue(AttributeRegistry.FIRE_SPELL_POWER);
                } catch (Exception ignored) {}
                try {
                    blood = attacker.getAttributeValue(AttributeRegistry.BLOOD_SPELL_POWER);
                } catch (Exception ignored) {}
                try {
                    shadow = attacker.getAttributeValue(HnSAttributeRegistry.SHADOW_SPELL_POWER);
                } catch (Exception ignored) {}
                try {
                    ritual = attacker.getAttributeValue(ASAttributeRegistry.RITUAL_MAGIC_POWER);
                } catch (Exception ignored) {}

                totalPercent += normalize.apply(fire);
                totalPercent += normalize.apply(blood);
                totalPercent += normalize.apply(shadow);
                totalPercent += normalize.apply(ritual);

                // 🔥 1 amplifier per 200%
                amplifier = (int)Math.floor(totalPercent / 200.0);

                // Prevent negative just in case
                amplifier = Math.max(0, amplifier);

            } catch (Throwable t) {
                amplifier = 0;
            }

            boolean inSoulState = attacker.hasEffect(ESEffectRegistry.BRIMSTONE_STATE);

            if (inSoulState) {
                target.addEffect(new MobEffectInstance(ESEffectRegistry.VULNERABILITY_HEX, 100, amplifier, false, true, true));
            } else {
                target.addEffect(new MobEffectInstance(ESEffectRegistry.BRIMSTONE_FLAMES, 100, amplifier, false, true, true));
            }
        }
    }

    @EventBusSubscriber
    public static class ProvidenceHitEffects {

        @SubscribeEvent
        public static void onLivingDamage(LivingDamageEvent.Post event) {

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
                double totalPercent = 0.0;

                // Helper to normalize attribute → percent
                java.util.function.Function<Double, Double> normalize = val -> {
                    if (val <= 0.0) return 0.0;
                    if (val < 10.0) return val * 100.0; // multiplier → percent
                    return val; // already percent
                };

                double fire = 0.0;
                double holy = 0.0;
                double radiance = 0.0;
                double geo = 0.0;

                try {
                    fire = attacker.getAttributeValue(AttributeRegistry.FIRE_SPELL_POWER);
                } catch (Exception ignored) {}
                try {
                    holy = attacker.getAttributeValue(AttributeRegistry.HOLY_SPELL_POWER);
                } catch (Exception ignored) {}
                try {
                    radiance = attacker.getAttributeValue(HnSAttributeRegistry.RADIANCE_SPELL_POWER);
                } catch (Exception ignored) {}
                try {
                    geo = attacker.getAttributeValue(GGAttributes.GEO_SPELL_POWER);
                } catch (Exception ignored) {}

                totalPercent += normalize.apply(fire);
                totalPercent += normalize.apply(holy);
                totalPercent += normalize.apply(radiance);
                totalPercent += normalize.apply(geo);

                // 🔥 1 amplifier per 200%
                amplifier = (int)Math.floor(totalPercent / 200.0);

                // Prevent negative just in case
                amplifier = Math.max(0, amplifier);

            } catch (Throwable t) {
                amplifier = 0;
            }

            boolean inSoulState = attacker.hasEffect(ESEffectRegistry.NIGHT_STATE);

            if (inSoulState) {
                target.addEffect(new MobEffectInstance(ESEffectRegistry.NIGHT_WITHER, 100, amplifier, false, true, true));
            } else {
                target.addEffect(new MobEffectInstance(ESEffectRegistry.HOLY_FLAMES, 100, amplifier, false, true, true));
            }
        }
    }

}