package net.hazen.elemental_synergies.rarity;

import net.minecraft.world.item.Rarity;
import net.neoforged.fml.common.asm.enumextension.EnumProxy;
import net.minecraft.network.chat.Style;

import java.util.function.UnaryOperator;

public class AeromancyRarity {
    public static final EnumProxy<Rarity> AEROMANCY_RARITY_PROXY = new EnumProxy<>(Rarity.class,
            -1,
            "elemental_synergies:aeromancy",
            (UnaryOperator<Style>) ((style) -> style.withColor(0x87CEEB))
    );
}