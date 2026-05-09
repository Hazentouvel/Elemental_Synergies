package net.hazen.elemental_synergies.Setup;

import it.crystalnest.prometheus.api.FireManager;
import net.hazen.elemental_synergies.ESUtilities.ESSpellDamageSource;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

@EventBusSubscriber
public class ESDamageSources {

    @SubscribeEvent
    public static void postHitEffects(LivingDamageEvent.Post event) {

        var damageSource = event.getSource();

        if (damageSource instanceof ESSpellDamageSource spellDamageSource
                && spellDamageSource.hasPostHitEffects()) {

            var target = event.getEntity();

            if (spellDamageSource.getSoulFireTime() > 0) {

                FireManager.setOnFire(target, spellDamageSource.getSoulFireTime() / 20f, FireManager.SOUL_FIRE_TYPE
                );
            }
        }
    }
}