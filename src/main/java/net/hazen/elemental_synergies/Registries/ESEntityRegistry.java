package net.hazen.elemental_synergies.Registries;

import net.hazen.elemental_synergies.ElementalSynergies;
import net.hazen.elemental_synergies.Entities.Mobs.Wizards.CloudmasterSageEntity;
import net.hazen.elemental_synergies.Entities.Spells.Fire.SoulFlame.SoulflameBolt.SoulflameBolt;
import net.hazen.elemental_synergies.Entities.Spells.Fire.SupremeCalamitas.BrimflameBolt.BrimflameBolt;
import net.hazen.elemental_synergies.Entities.Spells.Fire.SupremeCalamitas.BrimstoneHellblast.BrimstoneHellblast;
import net.hazen.elemental_synergies.Entities.Spells.Fire.Providence.HolyBlast.HolyBlast;
import net.hazen.elemental_synergies.Entities.Spells.Fire.Providence.HolyBlast.HolyFire.HolyFlame;
import net.hazen.elemental_synergies.Entities.Spells.Hydro.Scylla.SpearsOfAcropolis.WaterSpearMagicProjectile;
import net.hazen.elemental_synergies.Entities.Spells.Hydro.Scylla.SpearsOfAcropolis.LightningSpearMagicProjectile;
import net.hazen.elemental_synergies.Entities.Spells.Hydro.Scylla.TidalWave.TidalWaveStomp;
import net.hazen.elemental_synergies.Entities.Spells.Hydro.Scylla.TidalWave.WaveMagicProjectileEntity;
import net.hazen.elemental_synergies.Entities.Weapons.Violence.Violence;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ESEntityRegistry {
    private static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister
            .create(Registries.ENTITY_TYPE, ElementalSynergies.MOD_ID);

    /*
     *** Spells
     */

    // Brimstone Hellblast
    public static final DeferredHolder<EntityType<?>, EntityType<BrimstoneHellblast>> BRIMSTONE_HELLBLAST =
            ENTITIES.register("brimstone_hellblast", () -> EntityType.Builder.<BrimstoneHellblast>of(BrimstoneHellblast::new, MobCategory.MISC)
                    .sized(2f, 2f)
                    .clientTrackingRange(4)
                    .build(ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "brimstone_hellblast").toString())
            );

    // Holy Blast
    public static final DeferredHolder<EntityType<?>, EntityType<HolyBlast>> HOLY_BLAST =
            ENTITIES.register("holy_blast", () -> EntityType.Builder.<HolyBlast>of(HolyBlast::new, MobCategory.MISC)
                    .sized(2.5f, 2.5f)
                    .clientTrackingRange(4)
                    .build(ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "holy_blast").toString())
            );

    // Holy Flame
    public static final DeferredHolder<EntityType<?>, EntityType<HolyFlame>> HOLY_FLAME =
            ENTITIES.register("holy_flame", () -> EntityType.Builder.<HolyFlame>of(HolyFlame::new, MobCategory.MISC)
                    .sized(1f, 1f)
                    .clientTrackingRange(4)
                    .build(ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "holy_flame").toString())
            );

    // Brimflame Bolt
    public static final DeferredHolder<EntityType<?>, EntityType<BrimflameBolt>> BRIMFLAME_BOLT =
            ENTITIES.register("brimflame_bolt", () -> EntityType.Builder.<BrimflameBolt>of(BrimflameBolt::new, MobCategory.MISC)
                    .sized(.5f, .5f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "brimflame_bolt").toString()));

    // Soulflame Bolt
    public static final DeferredHolder<EntityType<?>, EntityType<SoulflameBolt>> SOULFLAME_BOLT =
            ENTITIES.register("soulflame_bolt", () -> EntityType.Builder.<SoulflameBolt>of(SoulflameBolt::new, MobCategory.MISC)
                    .sized(.5f, .5f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "soulflame_bolt").toString()));


    // Elemental Spear
    public static final DeferredHolder<EntityType<?>, EntityType<WaterSpearMagicProjectile>> ELEMENTAL_SPEAR =
            ENTITIES.register("elemental_spear", () -> EntityType.Builder.<WaterSpearMagicProjectile>of(WaterSpearMagicProjectile::new, MobCategory.MISC)
                    .sized(.5f, .5f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "elemental_spear").toString()));


    // Water Spear
    public static final DeferredHolder<EntityType<?>, EntityType<WaterSpearMagicProjectile>> WATER_SPEAR =
            ENTITIES.register("water_spear", () -> EntityType.Builder.<WaterSpearMagicProjectile>of(WaterSpearMagicProjectile::new, MobCategory.MISC)
                    .sized(.5f, .5f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "water_spear").toString()));


    // Lightning Spear
    public static final DeferredHolder<EntityType<?>, EntityType<LightningSpearMagicProjectile>> LIGHTNING_SPEAR =
            ENTITIES.register("lightning_spear", () -> EntityType.Builder.<LightningSpearMagicProjectile>of(LightningSpearMagicProjectile::new, MobCategory.MISC)
                    .sized(.5f, .5f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "lightning_spear").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<TidalWaveStomp>> TIDAL_WAVE_STOMP =
            ENTITIES.register("tidal_wave_stomp", () -> EntityType.Builder.<TidalWaveStomp>of(TidalWaveStomp::new, MobCategory.MISC)
                    .sized(1f, 1f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "tidal_wave_stomp").toString()));


    public static final DeferredHolder<EntityType<?>, EntityType<WaveMagicProjectileEntity>> WAVE =
            ENTITIES.register("wave", () -> EntityType.Builder.<WaveMagicProjectileEntity>of(WaveMagicProjectileEntity::new, MobCategory.MISC)
                    .sized(1.7F, 2.0F)
                    .fireImmune()
                    .clientTrackingRange(6)
                    .setUpdateInterval(1)
                    .build(ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "wave").toString()));



    /*
     *** Weapons
     */


    // Violence
    public static final DeferredHolder<EntityType<?>, EntityType<Violence>> VIOLENCE =
            ENTITIES.register("violence", () -> EntityType.Builder.<Violence>of(Violence::new, MobCategory.MISC)
                    .sized(1f, 1.5f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "violence").toString()));


    /*
     *** Entities
     */

    //Electromancer
    public static final DeferredHolder<EntityType<?>, EntityType<CloudmasterSageEntity>> CLOUDMASTER_SAGE =
            ENTITIES.register("cloudmaster_sage", () -> EntityType.Builder.of(CloudmasterSageEntity::new, MobCategory.MONSTER)
                    .sized(.6f, 1.8f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "cloudmaster_sage").toString()));

    public static void register(IEventBus eventBus)
    {
        ENTITIES.register(eventBus);
    }
}