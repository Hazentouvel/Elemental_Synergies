package net.hazen.elemental_synergies.Enchantments;

import com.mojang.serialization.MapCodec;
import net.hazen.hazennstuff.Registries.HnSEntityRegistry;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

public class LightningStrikerEnchantmentEffect implements EnchantmentEntityEffect {
    public static final MapCodec<LightningStrikerEnchantmentEffect> CODEC = MapCodec.unit(LightningStrikerEnchantmentEffect::new);


    @Override
    public void apply(ServerLevel pLevel, int pEnchantmentLevel, EnchantedItemInUse pItem, Entity pEntity, Vec3 pOrigin) {
        if(pEnchantmentLevel == 1)
        {
            EntityType.LIGHTNING_BOLT.spawn(pLevel, pEntity.getOnPos(), MobSpawnType.TRIGGERED);
        }
        if(pEnchantmentLevel == 2)
        {
            EntityType.LIGHTNING_BOLT.spawn(pLevel, pEntity.getOnPos(), MobSpawnType.TRIGGERED);
            EntityType.LIGHTNING_BOLT.spawn(pLevel, pEntity.getOnPos(), MobSpawnType.TRIGGERED);
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}
