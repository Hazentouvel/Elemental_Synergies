package net.hazen.elemental_synergies.Setup;

import com.gametechbc.gtbcs_geomancy_plus.api.init.GGAttributes;
import com.snackpirate.aeromancy.data.AADamageTypes;
import com.snackpirate.aeromancy.spells.AASpells;
import io.redspace.ironsspellbooks.api.entity.IMagicEntity;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.util.Utils;
import net.acetheeldritchking.aces_spell_utils.registries.ASAttributeRegistry;
import net.acetheeldritchking.discerning_the_eldritch.registries.DTEDataComponentRegistry;
import net.hazen.elemental_synergies.Items.Armor.AscensionTier.Providence.ProvidenceArmorItem;
import net.hazen.elemental_synergies.Items.Armor.AscensionTier.SupremeCalamitas.SupremeCalamitasArmorItem;
import net.hazen.elemental_synergies.Items.Armor.PureTier.MultiSchool.SoulFlame.GeckoLib.GeckolibSoulFlameArmorItem;
import net.hazen.elemental_synergies.Items.Armor.PureTier.MultiSchool.SoulFlame.SoulFlameArmorItem;
import net.hazen.elemental_synergies.Registries.ESEffectRegistry;
import net.hazen.elemental_synergies.Registries.ESItemRegistry;
import net.hazen.hazentouvelib.Registries.HLAttributeRegistry;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
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

    private static boolean isWearingFullSoulFlame(LivingEntity entity) {
        return entity.getItemBySlot(ArmorItem.Type.HELMET.getSlot()).getItem() instanceof SoulFlameArmorItem &&
                entity.getItemBySlot(ArmorItem.Type.CHESTPLATE.getSlot()).getItem() instanceof SoulFlameArmorItem &&
                entity.getItemBySlot(ArmorItem.Type.LEGGINGS.getSlot()).getItem() instanceof SoulFlameArmorItem &&
                entity.getItemBySlot(ArmorItem.Type.BOOTS.getSlot()).getItem() instanceof SoulFlameArmorItem;
    }

    private static boolean isWearingFullSoulFlameGeckolib(LivingEntity entity) {
        return entity.getItemBySlot(ArmorItem.Type.HELMET.getSlot()).getItem() instanceof GeckolibSoulFlameArmorItem &&
                entity.getItemBySlot(ArmorItem.Type.CHESTPLATE.getSlot()).getItem() instanceof GeckolibSoulFlameArmorItem &&
                entity.getItemBySlot(ArmorItem.Type.LEGGINGS.getSlot()).getItem() instanceof GeckolibSoulFlameArmorItem &&
                entity.getItemBySlot(ArmorItem.Type.BOOTS.getSlot()).getItem() instanceof GeckolibSoulFlameArmorItem;
    }


    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        LivingEntity dead = event.getEntity();

        Entity rawSource = event.getSource().getEntity();
        if (rawSource == null) return;

        LivingEntity attacker = null;
        if (rawSource instanceof LivingEntity le) attacker = le;
        else {
            try {
                if (rawSource instanceof net.minecraft.world.entity.projectile.Projectile proj) {
                    Entity owner = proj.getOwner();
                    if (owner instanceof LivingEntity oLe) attacker = oLe;
                }
            } catch (Exception ignored) {}
            if (attacker == null) {
                try {
                    java.lang.reflect.Method m = rawSource.getClass().getMethod("getOwner");
                    Object owner = m.invoke(rawSource);
                    if (owner instanceof LivingEntity oLe) attacker = oLe;
                } catch (Exception ignored) {
                }
            }
        }

        if (attacker == null) return;
        if (attacker.level().isClientSide()) return;
        if (dead == attacker) return;

        if (!isWearingFullSoulFlame(attacker) && !isWearingFullSoulFlameGeckolib(attacker)) return;

        ItemStack chest = attacker.getItemBySlot(ArmorItem.Type.CHESTPLATE.getSlot());
        boolean isChestplate = chest.getItem() instanceof SoulFlameArmorItem;
        boolean isGeckolibChestplate = chest.getItem() instanceof GeckolibSoulFlameArmorItem;

        if (!isChestplate && !isGeckolibChestplate) return;

        Integer stacks = chest.get(DTEDataComponentRegistry.SOUL_FIRE_STACKS);
        if (stacks == null) stacks = 0;

        chest.set(DTEDataComponentRegistry.SOUL_FIRE_STACKS, stacks + 1);
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

                int fullHundreds = (int)Math.floor(percent / 200.0);
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
}
