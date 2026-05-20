package net.hazen.elemental_synergies.Spells.Schools.Hydro.Scylla;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.spells.CastSource;
import io.redspace.ironsspellbooks.api.spells.CastType;
import io.redspace.ironsspellbooks.api.spells.SpellRarity;
import io.redspace.ironsspellbooks.api.util.Utils;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import net.acetheeldritchking.aces_spell_utils.registries.ASSchoolRegistry;
import net.hazen.elemental_synergies.Datagen.ESTags;
import net.hazen.elemental_synergies.Entities.Spells.Hydro.Scylla.SpearsOfAcropolis.WaterSpearMagicProjectile;
import net.hazen.elemental_synergies.Entities.Spells.Hydro.Scylla.SpearsOfAcropolis.LightningSpearMagicProjectile;
import net.hazen.elemental_synergies.Registries.ESSounds;
import net.hazen.hazentouvelib.Spells.AbstractSpells.AbstractTaggedSpell;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class SpearsOfAcropolisSpell extends AbstractTaggedSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath("elemental_synergies", "spears_of_acropolis");
    private final DefaultConfig defaultConfig;
    private final Random rand = new Random();

    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        ArrayList<MutableComponent> li = new ArrayList<>(super.getUniqueInfo(spellLevel, caster));
        li.addFirst(Component.literal("⦙ - Hazen 'n Stuff - ⦙").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.BOLD));
        li.add(Component.translatable("ui.irons_spellbooks.damage", new Object[]{Utils.stringTruncation((double)this.getDamage(spellLevel, caster), 2)}));
        return li;
    }

    public SpearsOfAcropolisSpell() {
        this.defaultConfig = (new DefaultConfig())
                .setMinRarity(SpellRarity.UNCOMMON)
                .setSchoolResource(ASSchoolRegistry.HYDRO_RESOURCE)
                .setMaxLevel(10)
                .setCooldownSeconds((double)2.5F)
                .build();
        this.manaCostPerLevel = 2;
        this.baseSpellPower = 12;
        this.spellPowerPerLevel = 1;
        this.castTime = 0;
        this.baseManaCost = 10;
    }

    public DefaultConfig getDefaultConfig() {
        return this.defaultConfig;
    }

    public CastType getCastType() {
        return CastType.INSTANT;
    }

    @Override
    public Optional<SoundEvent> getCastStartSound() {
        return Optional.of(ESSounds.WATER_SPEAR_CAST.get());
    }

    public ResourceLocation getSpellResource() {
        return this.spellId;
    }

    private float getDamage(int spellLevel, LivingEntity entity) {
        return this.getSpellPower(spellLevel, entity) * 0.6F;
    }

    public void onCast(Level world, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {

        if (world.isClientSide) return;

        Vec3 lookVec = entity.getLookAngle();
        boolean forceMixed = this.hasTaggedItem(entity, ESTags.HYDRO_NORMALIZING_CURIO);
        boolean hydroOnly = !forceMixed && this.hasTaggedItem(entity, ESTags.HYDRO_ENHANCING_CURIO);
        boolean lightningOnly = !forceMixed && !hydroOnly && this.hasTaggedItem(entity, ESTags.LIGHTNING_ENHANCING_CURIO);

        double lightningPower = entity.getAttributeValue(AttributeRegistry.LIGHTNING_SPELL_POWER);

        float tiltDeg = 22.5F;
        Vec3 dirCenter = lookVec.normalize();
        Vec3 dirLeft = lookVec.yRot((float)Math.toRadians(-tiltDeg)).normalize();
        Vec3 dirRight = lookVec.yRot((float)Math.toRadians(tiltDeg)).normalize();

        Vec3[] dirs = new Vec3[]{dirCenter, dirLeft, dirRight};

        int sharedDelay = 8 + rand.nextInt(6);

        for (int i = 0; i < 3; i++) {
            boolean spawnLightning = false;
            if (lightningOnly) {
                spawnLightning = true;
            } else if (!hydroOnly && !lightningOnly) {
                if (lightningPower >= 0.75D && rand.nextInt(3) == i) {
                    spawnLightning = true;
                }
            }

            spawnSpear(world, entity, dirs[i], spawnLightning, spellLevel, sharedDelay);
        }

        super.onCast(world, spellLevel, entity, castSource, playerMagicData);
    }

    private void spawnSpear(Level world, LivingEntity owner, Vec3 dir, boolean lightning, int spellLevel, int delay) {
        if (lightning) {
            LightningSpearMagicProjectile spear = new LightningSpearMagicProjectile(world, owner);
            spear.setPos(owner.position().add(0.0D, owner.getEyeHeight() - spear.getBoundingBox().getYsize() * 0.5D, 0.0D));
            spear.setDamage(this.getDamage(spellLevel, owner));
            spear.setLaunchDir(dir);
            spear.setDelay(delay);
            world.addFreshEntity(spear);
        } else {
            WaterSpearMagicProjectile spear = new WaterSpearMagicProjectile(world, owner);
            spear.setPos(owner.position().add(0.0D, owner.getEyeHeight() - spear.getBoundingBox().getYsize() * 0.5D, 0.0D));
            spear.setDamage(this.getDamage(spellLevel, owner));
            spear.setLaunchDir(dir);
            spear.setDelay(delay);
            world.addFreshEntity(spear);
        }
    }
}
