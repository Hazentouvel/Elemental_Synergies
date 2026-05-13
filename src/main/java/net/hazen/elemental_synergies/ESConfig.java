package net.hazen.elemental_synergies;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;


@EventBusSubscriber(
        modid = "elemental_synergies",
        bus = EventBusSubscriber.Bus.MOD
)
public class ESConfig {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    private static final ModConfigSpec.ConfigValue<Integer> SUPREME_SHIELD_COOLDOWN;

    public static int supremeShieldCooldown;

    public static final ModConfigSpec SPEC;

    public ESConfig() {
    }

    @SubscribeEvent
    static void onLoad(ModConfigEvent event)
    {
        supremeShieldCooldown = (Integer) SUPREME_SHIELD_COOLDOWN.get();
    }

    static {
        SUPREME_SHIELD_COOLDOWN = BUILDER
                .comment("Defines the cooldown in ticks the value for the Supreme Shield's dash ability.")
                .comment("Default is 80")
                .define("Supreme Shield CD", 80);
        SPEC = BUILDER.build();
    }
}
