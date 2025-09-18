package net.hazen.elemental_synergies.registries;

import net.hazen.elemental_synergies.ElementalSynergies;
import net.hazen.elemental_synergies.registries.effects.WindShearEffect;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ESEffectRegistry {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, ElementalSynergies.MOD_ID);



    public static final DeferredHolder<MobEffect, MobEffect> WIND_SHEAR = MOB_EFFECTS.register("wind_shear",
            () -> new WindShearEffect(MobEffectCategory.HARMFUL, 0x2E2EFF));


    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }

}
