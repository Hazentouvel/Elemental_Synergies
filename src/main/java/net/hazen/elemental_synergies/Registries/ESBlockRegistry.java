package net.hazen.elemental_synergies.Registries;

import net.hazen.elemental_synergies.Blocks.HolyFireBlock;
import net.hazen.elemental_synergies.ElementalSynergies;
import net.hazen.hazennstuff.Registries.HnSItemRegistry;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ESBlockRegistry {
        public static final DeferredRegister.Blocks BLOCKS =
                DeferredRegister.createBlocks(ElementalSynergies.MOD_ID);

        public static final DeferredBlock<Block> HOLY_FIRE = registerBlock("holy_fire",
                () -> new HolyFireBlock(
                        BlockBehaviour
                                .Properties
                                .ofFullCopy(Blocks.SOUL_FIRE)
                                .mapColor(MapColor.COLOR_YELLOW)
                                .lightLevel(state -> 15)
                ));


        private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
                DeferredBlock<T> toReturn = BLOCKS.register(name, block);
                registerBlockItem(name, toReturn);
                return toReturn;
        }

        private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
                HnSItemRegistry.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
        }

        public static void register(IEventBus eventBus) {
                BLOCKS.register(eventBus);
        }
}