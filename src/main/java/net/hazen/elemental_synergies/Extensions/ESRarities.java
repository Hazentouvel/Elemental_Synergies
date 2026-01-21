package net.hazen.elemental_synergies.Extensions;

import net.minecraft.world.item.Rarity;
import net.neoforged.fml.common.asm.enumextension.EnumProxy;
import net.minecraft.network.chat.Style;

import java.util.function.UnaryOperator;

public class ESRarities {

    public static final EnumProxy<Rarity> AEROMANCY_RARITY = new EnumProxy<>(Rarity.class,
            -1,
            "elemental_synergies:aeromancy",
            (UnaryOperator<Style>) ((style) -> style.withColor(0xa3b6ff))
    );

    public static final EnumProxy<Rarity> GEOMANCY_RARITY = new EnumProxy<>(Rarity.class,
            -1,
            "elemental_synergies:geomancy",
            (UnaryOperator<Style>) ((style) -> style.withColor(0xa3b6ff))
    );

    public static final EnumProxy<Rarity> SOUND_RARITY = new EnumProxy<>(Rarity.class,
            -1,
            "elemental_synergies:sound",
            (UnaryOperator<Style>) ((style) -> style.withColor(0xa3b6ff))
    );

    public static final EnumProxy<Rarity> SPELLBLADE_RARITY = new EnumProxy<>(Rarity.class,
            -1,
            "elemental_synergies:spellblade",
            (UnaryOperator<Style>) ((style) -> style.withColor(0xa3b6ff))
    );

    public static final EnumProxy<Rarity> OCCULT_RARITY = new EnumProxy<>(Rarity.class,
            -1,
            "elemental_synergies:occult",
            (UnaryOperator<Style>) ((style) -> style.withColor(0xa3b6ff))
    );

    public static final EnumProxy<Rarity> AQUA_RARITY = new EnumProxy<>(Rarity.class,
            -1,
            "elemental_synergies:aqua",
            (UnaryOperator<Style>) ((style) -> style.withColor(0xa3b6ff))
    );

    public static final EnumProxy<Rarity> ABYSSAL_RARITY = new EnumProxy<>(Rarity.class,
            -1,
            "elemental_synergies:abyssal",
            (UnaryOperator<Style>) ((style) -> style.withColor(0xa3b6ff))
    );
}