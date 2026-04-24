package net.hazen.elemental_synergies.Datagen.Items;

import net.hazen.elemental_synergies.Datagen.ESTags;
import net.hazen.elemental_synergies.ElementalSynergies;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ESBlockTagProvider extends BlockTagsProvider {
    public ESBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, ElementalSynergies.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        tag(ESTags.Blocks.HOLY_FIRE_SURVIVES_ON)
                .add(Blocks.SAND)
        ;



    }
}