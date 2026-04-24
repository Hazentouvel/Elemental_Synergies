package net.hazen.elemental_synergies.Registries;

import net.hazen.elemental_synergies.ElementalSynergies;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageScaling;
import net.minecraft.world.damagesource.DamageType;

public class ESDamageTypes {
    public static ResourceKey<DamageType> register(String name) {
        return ResourceKey.create(
                Registries.DAMAGE_TYPE,
                ResourceLocation.parse(ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, name).toString())
        );
    }

    public static final ResourceKey<DamageType> BRIMSTONE_BURN = register("brimstone_burn");
    public static final ResourceKey<DamageType> VULNERABILITY_HEX = register("vulnerability_hex");
    public static final ResourceKey<DamageType> HOLY_BURN = register("holy_burn");
    public static final ResourceKey<DamageType> NIGHT_WITHER = register("night_wither");

    public static void bootstrap(BootstrapContext<DamageType> context)
    {
        context.register(BRIMSTONE_BURN, new DamageType(BRIMSTONE_BURN.location().getPath(), DamageScaling.WHEN_CAUSED_BY_LIVING_NON_PLAYER, 0F));
        context.register(VULNERABILITY_HEX, new DamageType(VULNERABILITY_HEX.location().getPath(), DamageScaling.WHEN_CAUSED_BY_LIVING_NON_PLAYER, 0F));
        context.register(HOLY_BURN, new DamageType(HOLY_BURN.location().getPath(), DamageScaling.WHEN_CAUSED_BY_LIVING_NON_PLAYER, 0F));
        context.register(NIGHT_WITHER, new DamageType(NIGHT_WITHER.location().getPath(), DamageScaling.WHEN_CAUSED_BY_LIVING_NON_PLAYER, 0F));
    }
}