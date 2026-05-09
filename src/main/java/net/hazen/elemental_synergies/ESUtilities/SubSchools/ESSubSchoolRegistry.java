package net.hazen.elemental_synergies.ESUtilities.SubSchools;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.spells.SchoolType;
import io.redspace.ironsspellbooks.damage.ISSDamageTypes;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import net.hazen.elemental_synergies.Datagen.ESTags;
import net.hazen.elemental_synergies.ElementalSynergies;
import net.hazen.hazentouvelib.Datagen.HLTags;
import net.hazen.hazentouvelib.HazentouveLib;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static io.redspace.ironsspellbooks.api.registry.SchoolRegistry.SCHOOL_REGISTRY_KEY;

public class ESSubSchoolRegistry {
    private static final DeferredRegister<SchoolType> HNS_SCHOOLS = DeferredRegister.create(SCHOOL_REGISTRY_KEY, ElementalSynergies.MOD_ID);

    public static void register(IEventBus eventBus)
    {
        HNS_SCHOOLS.register(eventBus);
    }

    private static Supplier<SchoolType> registerSchool(SchoolType type)
    {
        return HNS_SCHOOLS.register(type.getId().getPath(), () -> type);
    }

    public static final ResourceLocation BRIMSTONE_RESOURCE = ElementalSynergies.id("brimstone");
    public static final Supplier<SchoolType> BRIMSTONE = registerSchool(new SchoolType
            (
                    BRIMSTONE_RESOURCE,
                    ESTags.BRIMSTONE_FOCUS,
                    Component.translatable("school.elemental_synergies.brimstone")
                            .withStyle(Style.EMPTY.withColor(0xe4a6ea)),
                    AttributeRegistry.FIRE_SPELL_POWER,
                    AttributeRegistry.FIRE_SPELL_POWER,
                    SoundRegistry.FIRE_CAST,
                    ISSDamageTypes.FIRE_MAGIC
            ));

    public static final ResourceLocation SOUL_FLAME_RESOURCE = ElementalSynergies.id("soul_flame");

    public static final Supplier<SchoolType> SOUL_FLAME = registerSchool(new SchoolType
            (
                    SOUL_FLAME_RESOURCE,
                    ESTags.SOUL_FLAME_FOCUS,
                    Component.translatable("school.elemental_synergies.soul_flame")
                            .withStyle(Style.EMPTY.withColor(0xe4a6ea)),
                    AttributeRegistry.FIRE_SPELL_POWER,
                    AttributeRegistry.FIRE_SPELL_POWER,
                    SoundRegistry.FIRE_CAST,
                    ISSDamageTypes.FIRE_MAGIC
            ));

    public static final ResourceLocation PROFANE_RESOURCE = ElementalSynergies.id("profane");

    public static final Supplier<SchoolType> PROFANE = registerSchool(new SchoolType
            (
                    PROFANE_RESOURCE,
                    ESTags.PROFANE_FOCUS,
                    Component.translatable("school.elemental_synergies.profane")
                            .withStyle(Style.EMPTY.withColor(0xe4a6ea)),
                    AttributeRegistry.FIRE_SPELL_POWER,
                    AttributeRegistry.FIRE_SPELL_POWER,
                    SoundRegistry.FIRE_CAST,
                    ISSDamageTypes.FIRE_MAGIC
            ));
    }