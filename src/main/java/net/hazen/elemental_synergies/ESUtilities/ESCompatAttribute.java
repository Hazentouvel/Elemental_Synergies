package net.hazen.elemental_synergies.ESUtilities;

import com.bobmowzie.mowziesmobs.server.item.ItemHandler;
import com.gametechbc.gtbcs_geomancy_plus.api.init.GGAttributes;
import com.github.L_Ender.cataclysm.init.ModItems;
import com.github.L_Ender.cataclysm.items.Cursium_Armor;
import com.github.L_Ender.cataclysm.items.Cursium_ChestPlate;
import com.github.L_Ender.cataclysm.items.Ignitium_Armor;
import com.github.L_Ender.cataclysm.items.Ignitium_Elytra_ChestPlate;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.acetheeldritchking.aces_spell_utils.registries.ASAttributeRegistry;
import net.acetheeldritchking.cataclysm_spellbooks.registries.CSAttributeRegistry;
import net.acetheeldritchking.discerning_the_eldritch.registries.ItemRegistries;
import net.hazen.elemental_synergies.ElementalSynergies;
import net.hazen.hazentouvelib.Registries.HLAttributeRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.neoforged.neoforge.event.ItemAttributeModifierEvent;

public class ESCompatAttribute {

    public static void addIgnitiumArmorAttributes(ItemAttributeModifierEvent event) {
        if (!(event.getItemStack().getItem() instanceof Ignitium_Armor armorItem)) {
            return;
        }

        EquipmentSlot slot = armorItem.getEquipmentSlot();
        EquipmentSlotGroup slotGroup = EquipmentSlotGroup.bySlot(slot);

        event.addModifier(
                AttributeRegistry.FIRE_SPELL_POWER.getDelegate(),
                new AttributeModifier(ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "ignitium_fire_spell_power"),
                        0.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE), slotGroup
        );
    }

    public static void addIgnitiumChestplateArmorAttributes(ItemAttributeModifierEvent event) {
        if (!(event.getItemStack().getItem() instanceof Ignitium_Elytra_ChestPlate armorItem)) {
            return;
        }

        EquipmentSlot slot = armorItem.getEquipmentSlot();
        EquipmentSlotGroup slotGroup = EquipmentSlotGroup.bySlot(slot);

        event.addModifier(
                AttributeRegistry.FIRE_SPELL_POWER.getDelegate(),
                new AttributeModifier(ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "ignitium_fire_spell_power"),
                        0.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE), slotGroup
        );
    }

    public static void addCursiumArmorAttributes(ItemAttributeModifierEvent event) {
        if (!(event.getItemStack().getItem() instanceof Cursium_Armor armorItem)) {
            return;
        }

        EquipmentSlot slot = armorItem.getEquipmentSlot();
        EquipmentSlotGroup slotGroup = EquipmentSlotGroup.bySlot(slot);

        event.addModifier(
                AttributeRegistry.ICE_SPELL_POWER.getDelegate(),
                new AttributeModifier(ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "cursium_fire_spell_power"),
                        0.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE), slotGroup
        );
    }

    public static void addCursiumChestplateArmorAttributes(ItemAttributeModifierEvent event) {
        if (!(event.getItemStack().getItem() instanceof Cursium_ChestPlate armorItem)) {
            return;
        }

        EquipmentSlot slot = armorItem.getEquipmentSlot();
        EquipmentSlotGroup slotGroup = EquipmentSlotGroup.bySlot(slot);

        event.addModifier(
                AttributeRegistry.ICE_SPELL_POWER.getDelegate(),
                new AttributeModifier(ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "cursium_fire_spell_power"),
                        0.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE), slotGroup
        );
    }

    public static void addSoulFireScytheAttributes(ItemAttributeModifierEvent event) {

        if (event.getItemStack().getItem() != ItemRegistries.SOUL_FIRE_SCYTHE.get()) {
            return;
        }

        EquipmentSlotGroup slotGroup = EquipmentSlotGroup.MAINHAND;

        event.addModifier(
                HLAttributeRegistry.SHADOW_SPELL_POWER.getDelegate(),
                new AttributeModifier(
                        ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "sfs_shadow_spell_power"),
                        0.15, AttributeModifier.Operation.ADD_MULTIPLIED_BASE
                ),
                slotGroup
        );

        event.addModifier(
                HLAttributeRegistry.SHADOW_SPELL_RESIST.getDelegate(),
                new AttributeModifier(
                        ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "sfs_shadow_spell_resist"),
                        0.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE
                ),
                slotGroup
        );
    }

    public static void addForsakenFlambergeAttributes(ItemAttributeModifierEvent event) {

        if (event.getItemStack().getItem() != ItemRegistries.FORSAKEN_FLAMBERGE.get()) {
            return;
        }

        EquipmentSlotGroup slotGroup = EquipmentSlotGroup.MAINHAND;

        event.addModifier(
                HLAttributeRegistry.SHADOW_SPELL_POWER.getDelegate(),
                new AttributeModifier(
                        ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "ff_shadow_spell_power"),
                        0.05, AttributeModifier.Operation.ADD_MULTIPLIED_BASE
                ),
                slotGroup
        );
    }

    public static void addStaffOfVehemenceAttributes(ItemAttributeModifierEvent event) {

        if (event.getItemStack().getItem() != ItemRegistries.STAFF_OF_VEHEMENCE.get()) {
            return;
        }

        EquipmentSlotGroup slotGroup = EquipmentSlotGroup.MAINHAND;

        event.addModifier(
                HLAttributeRegistry.SHADOW_SPELL_POWER.getDelegate(),
                new AttributeModifier(
                        ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "sov_shadow_spell_power"),
                        0.15, AttributeModifier.Operation.ADD_MULTIPLIED_BASE
                ),
                slotGroup
        );
    }

    public static void addAwakenedWeaponAttributes(ItemAttributeModifierEvent event) {
        var item = event.getItemStack().getItem();

        if (item != ItemRegistries.CATACLYSM_AWAKENED.get()
                && item != ItemRegistries.DEVOURER_AWAKENED.get()
                && item != ItemRegistries.MOURNING_STAR_AWAKENED.get()) {
            return;
        }

        EquipmentSlotGroup slotGroup = EquipmentSlotGroup.MAINHAND;

        event.addModifier(
                HLAttributeRegistry.SHADOW_SPELL_POWER.getDelegate(),
                new AttributeModifier(
                        ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "awakened_weapon_shadow_spell_power"),
                        0.10, AttributeModifier.Operation.ADD_MULTIPLIED_BASE
                ),
                slotGroup
        );
    }


    public static void addCoralStaffAttributes(ItemAttributeModifierEvent event) {

        if (event.getItemStack().getItem() != net.acetheeldritchking.cataclysm_spellbooks.registries.ItemRegistries.CORAL_STAFF.get()) {
            return;
        }

        EquipmentSlotGroup slotGroup = EquipmentSlotGroup.MAINHAND;

        event.addModifier(
                ASAttributeRegistry.HYDRO_MAGIC_POWER.getDelegate(),
                new AttributeModifier(
                        ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "coral_staff_hydro_spell_power"),
                        0.25, AttributeModifier.Operation.ADD_MULTIPLIED_BASE
                ),
                slotGroup
        );
    }


    public static void addBloomStoneAttributes(ItemAttributeModifierEvent event) {

        if (event.getItemStack().getItem() != net.acetheeldritchking.cataclysm_spellbooks.registries.ItemRegistries.BLOOM_STONE_STAFF.get()) {
            return;
        }

        EquipmentSlotGroup slotGroup = EquipmentSlotGroup.MAINHAND;

        event.addModifier(
                HLAttributeRegistry.RADIANCE_SPELL_POWER.getDelegate(),
                new AttributeModifier(
                        ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "bloom_stone_staff_radiance_spell_power"),
                        0.15, AttributeModifier.Operation.ADD_MULTIPLIED_BASE
                ),
                slotGroup
        );

        event.addModifier(
                GGAttributes.GEO_SPELL_POWER.getDelegate(),
                new AttributeModifier(
                        ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "bloom_stone_staff_geo_spell_power"),
                        0.15, AttributeModifier.Operation.ADD_MULTIPLIED_BASE
                ),
                slotGroup
        );
    }


    public static void addIceCrystalAttributes(ItemAttributeModifierEvent event) {

        if (event.getItemStack().getItem() != ItemHandler.ICE_CRYSTAL.get()) {
            return;
        }

        EquipmentSlotGroup slotGroup = EquipmentSlotGroup.MAINHAND;

        event.addModifier(
                AttributeRegistry.ICE_SPELL_POWER.getDelegate(),
                new AttributeModifier(ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "ice_crystal_ice_spell_power"),
                        0.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE), slotGroup
        );
    }

    public static void addCoralAttributes(ItemAttributeModifierEvent event) {
        var item = event.getItemStack().getItem();

        if (item != ModItems.CORAL_BARDICHE.get()
                && item != ModItems.CORAL_SPEAR.get()
                && item != ModItems.ATHAME.get()) {
            return;
        }

        EquipmentSlotGroup slotGroup = EquipmentSlotGroup.MAINHAND;

        event.addModifier(
                ASAttributeRegistry.HYDRO_MAGIC_POWER.getDelegate(),
                new AttributeModifier(
                        ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "coral_hydro_spell_power"),
                        0.05, AttributeModifier.Operation.ADD_MULTIPLIED_BASE
                ),
                slotGroup
        );
    }


    public static void addKhopeshAttributes(ItemAttributeModifierEvent event) {

        if (event.getItemStack().getItem() != ModItems.KHOPESH.get()) {
            return;
        }

        EquipmentSlotGroup slotGroup = EquipmentSlotGroup.MAINHAND;

        event.addModifier(
                GGAttributes.GEO_SPELL_POWER.getDelegate(),
                new AttributeModifier(
                        ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "khopesh_geo_spell_power"),
                        0.05, AttributeModifier.Operation.ADD_MULTIPLIED_BASE
                ),
                slotGroup
        );
    }


    public static void addGauntletOfGuardAttributes(ItemAttributeModifierEvent event) {

        if (event.getItemStack().getItem() != ModItems.GAUNTLET_OF_GUARD.get()) {
            return;
        }

        EquipmentSlotGroup slotGroup = EquipmentSlotGroup.MAINHAND;

        event.addModifier(
                AttributeRegistry.ENDER_SPELL_POWER.getDelegate(),
                new AttributeModifier(
                        ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "gog_ender_spell_power"),
                        0.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE
                ),
                slotGroup
        );
    }


    public static void addGauntletOfBulwarkAttributes(ItemAttributeModifierEvent event) {

        if (event.getItemStack().getItem() != ModItems.GAUNTLET_OF_BULWARK.get()) {
            return;
        }

        EquipmentSlotGroup slotGroup = EquipmentSlotGroup.MAINHAND;

        event.addModifier(
                AttributeRegistry.ENDER_SPELL_POWER.getDelegate(),
                new AttributeModifier(
                        ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "gob_ender_spell_power"),
                        0.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE
                ),
                slotGroup
        );

        event.addModifier(
                AttributeRegistry.FIRE_SPELL_POWER.getDelegate(),
                new AttributeModifier(
                        ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "gob_fire_spell_power"),
                        0.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE
                ),
                slotGroup
        );
    }


    public static void addGauntletOfMaelstromAttributes(ItemAttributeModifierEvent event) {

        if (event.getItemStack().getItem() != ModItems.GAUNTLET_OF_MAELSTROM.get()) {
            return;
        }

        EquipmentSlotGroup slotGroup = EquipmentSlotGroup.MAINHAND;

        event.addModifier(
                AttributeRegistry.ENDER_SPELL_POWER.getDelegate(),
                new AttributeModifier(
                        ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "gom_ender_spell_power"),
                        0.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE
                ),
                slotGroup
        );

        event.addModifier(
                GGAttributes.GEO_SPELL_POWER.getDelegate(),
                new AttributeModifier(
                        ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "gom_ender_spell_power"),
                        0.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE
                ),
                slotGroup
        );
    }


    public static void addMeatShredderAttributes(ItemAttributeModifierEvent event) {

        if (event.getItemStack().getItem() != ModItems.MEAT_SHREDDER.get()) {
            return;
        }

        EquipmentSlotGroup slotGroup = EquipmentSlotGroup.MAINHAND;

        event.addModifier(
                ASAttributeRegistry.TECHNOMANCY_MAGIC_POWER.getDelegate(),
                new AttributeModifier(
                        ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "meat_shredder_technomancy_spell_power"),
                        0.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE
                ),
                slotGroup
        );
    }


    public static void addImmolatorAttributes(ItemAttributeModifierEvent event) {

        if (event.getItemStack().getItem() != ModItems.THE_IMMOLATOR.get()) {
            return;
        }

        EquipmentSlotGroup slotGroup = EquipmentSlotGroup.MAINHAND;

        event.addModifier(
                AttributeRegistry.FIRE_SPELL_POWER.getDelegate(),
                new AttributeModifier(
                        ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "immolator_fire_spell_power"),
                        0.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE
                ),
                slotGroup
        );
    }


    public static void addTidalClawsAttributes(ItemAttributeModifierEvent event) {

        if (event.getItemStack().getItem() != ModItems.TIDAL_CLAWS.get()) {
            return;
        }

        EquipmentSlotGroup slotGroup = EquipmentSlotGroup.MAINHAND;

        event.addModifier(
                ASAttributeRegistry.HYDRO_MAGIC_POWER.getDelegate(),
                new AttributeModifier(
                        ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "tc_hydro_spell_power"),
                        0.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE
                ),
                slotGroup
        );

        event.addModifier(
                CSAttributeRegistry.ABYSSAL_MAGIC_POWER.getDelegate(),
                new AttributeModifier(
                        ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "tc_abyssal_spell_power"),
                        0.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE
                ),
                slotGroup
        );
    }


    public static void addInfernalForgeAttributes(ItemAttributeModifierEvent event) {

        if (event.getItemStack().getItem() != ModItems.INFERNAL_FORGE.get()) {
            return;
        }

        EquipmentSlotGroup slotGroup = EquipmentSlotGroup.MAINHAND;

        event.addModifier(
                AttributeRegistry.FIRE_SPELL_POWER.getDelegate(),
                new AttributeModifier(
                        ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "if_fire_spell_power"),
                        0.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE
                ),
                slotGroup
        );
    }


    public static void addVoidForgeAttributes(ItemAttributeModifierEvent event) {

        if (event.getItemStack().getItem() != ModItems.VOID_FORGE.get()) {
            return;
        }

        EquipmentSlotGroup slotGroup = EquipmentSlotGroup.MAINHAND;

        event.addModifier(
                AttributeRegistry.FIRE_SPELL_POWER.getDelegate(),
                new AttributeModifier(
                        ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "vf_fire_spell_power"),
                        0.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE
                ),
                slotGroup
        );

        event.addModifier(
                AttributeRegistry.ENDER_SPELL_POWER.getDelegate(),
                new AttributeModifier(
                        ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "vf_ender_spell_power"),
                        0.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE
                ),
                slotGroup
        );
    }


    public static void addVoidCoreAttributes(ItemAttributeModifierEvent event) {

        if (event.getItemStack().getItem() != ModItems.VOID_CORE.get()) {
            return;
        }

        EquipmentSlotGroup slotGroup = EquipmentSlotGroup.MAINHAND;

        event.addModifier(
                AttributeRegistry.ENDER_SPELL_POWER.getDelegate(),
                new AttributeModifier(
                        ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "vc_ender_spell_power"),
                        0.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE
                ),
                slotGroup
        );
    }


    public static void addAstrapeAttributes(ItemAttributeModifierEvent event) {

        if (event.getItemStack().getItem() != ModItems.ASTRAPE.get()) {
            return;
        }

        EquipmentSlotGroup slotGroup = EquipmentSlotGroup.MAINHAND;

        event.addModifier(
                AttributeRegistry.LIGHTNING_SPELL_POWER.getDelegate(),
                new AttributeModifier(
                        ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "astrape_lightning_spell_power"),
                        0.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE
                ),
                slotGroup
        );
    }


    public static void addAncientSpearAttributes(ItemAttributeModifierEvent event) {

        if (event.getItemStack().getItem() != ModItems.ANCIENT_SPEAR.get()) {
            return;
        }

        EquipmentSlotGroup slotGroup = EquipmentSlotGroup.MAINHAND;

        event.addModifier(
                GGAttributes.GEO_SPELL_POWER.getDelegate(),
                new AttributeModifier(
                        ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "ac_geo_spell_power"),
                        0.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE
                ),
                slotGroup
        );
    }


    public static void addCeranusAttributes(ItemAttributeModifierEvent event) {

        if (event.getItemStack().getItem() != ModItems.CERAUNUS.get()) {
            return;
        }

        EquipmentSlotGroup slotGroup = EquipmentSlotGroup.MAINHAND;

        event.addModifier(
                ASAttributeRegistry.HYDRO_MAGIC_POWER.getDelegate(),
                new AttributeModifier(
                        ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "ceranus_hydro_spell_power"),
                        0.15, AttributeModifier.Operation.ADD_MULTIPLIED_BASE
                ),
                slotGroup
        );
    }


    public static void addBrontesAttributes(ItemAttributeModifierEvent event) {

        if (event.getItemStack().getItem() != ModItems.BRONTES.get()) {
            return;
        }

        EquipmentSlotGroup slotGroup = EquipmentSlotGroup.MAINHAND;

        event.addModifier(
                AttributeRegistry.LIGHTNING_SPELL_POWER.getDelegate(),
                new AttributeModifier(
                        ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "brontes_lightning_spell_power"),
                        0.15, AttributeModifier.Operation.ADD_MULTIPLIED_BASE
                ),
                slotGroup
        );
    }

}