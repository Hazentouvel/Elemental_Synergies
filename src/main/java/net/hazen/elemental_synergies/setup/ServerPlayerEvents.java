package net.hazen.elemental_synergies.setup;

import com.snackpirate.aeromancy.data.AADamageTypes;
import io.redspace.ironsspellbooks.api.entity.IMagicEntity;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.util.Utils;
import net.hazen.elemental_synergies.registries.ESEffectRegistry;
import net.hazen.elemental_synergies.registries.ESItemRegistry;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

@EventBusSubscriber
public class ServerPlayerEvents {
    public ServerPlayerEvents() {
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

                        target.addEffect(new MobEffectInstance(ESEffectRegistry.WIND_SHEAR, 60, 1));

                        int cooldownTicks = Utils.applyCooldownReduction(40, player);
                        player.getCooldowns().addCooldown(helmet, cooldownTicks);
                    } else {
                        target.addEffect(new MobEffectInstance(ESEffectRegistry.WIND_SHEAR, 60, 1));
                    }
                }
            }
        }
    }



}