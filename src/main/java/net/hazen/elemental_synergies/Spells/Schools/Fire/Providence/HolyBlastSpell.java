package net.hazen.elemental_synergies.Spells.Schools.Fire.Providence;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.CastSource;
import io.redspace.ironsspellbooks.api.spells.CastType;
import io.redspace.ironsspellbooks.api.spells.SpellAnimations;
import io.redspace.ironsspellbooks.api.spells.SpellRarity;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.RecastInstance;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import net.hazen.elemental_synergies.ESUtilities.SubSchools.ESSubSchoolRegistry;
import net.hazen.elemental_synergies.ElementalSynergies;
import net.hazen.elemental_synergies.Entities.Spells.Fire.Providence.HolyBlast.HolyBlast;
import net.hazen.elemental_synergies.Registries.ESSounds;
import net.hazen.elemental_synergies.Spells.AbstractSpells.ProvidenceSpells;
import net.hazen.hazennstuff.Registries.HnSSounds;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HolyBlastSpell extends ProvidenceSpells {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(ElementalSynergies.MOD_ID, "holy_blast");
    private boolean isRecast;
    private int recastStage = 1;

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        var li = new ArrayList<>(super.getUniqueInfo(spellLevel, caster));

        li.addFirst(Component.literal("\u3999 - Elemental Synergies - \u3999")
                .withStyle(ChatFormatting.GOLD)
                .withStyle(ChatFormatting.BOLD)
        );

        li.addAll(List.of(
                Component.translatable("ui.irons_spellbooks.damage",
                        new Object[]{Utils.stringTruncation((double)this.getDamage(spellLevel, caster), 2)}
                ),

                Component.translatable("ui.irons_spellbooks.radius", getRadius(spellLevel, caster))
        ));
        return li;
    }

    public boolean allowLooting() {
        return false;
    }

    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.EPIC)
            .setSchoolResource(ESSubSchoolRegistry.PROFANE_RESOURCE)
            .setMaxLevel(5)
            .setCooldownSeconds(25)
            .build();

    public HolyBlastSpell() {
        this.manaCostPerLevel = 15;
        this.baseSpellPower = 1;
        this.spellPowerPerLevel = 1;
        this.castTime = 40;
        this.baseManaCost = 150;
    }

    @Override
    public CastType getCastType() {
        return recastStage == 1 ? CastType.LONG : CastType.INSTANT;
    }

    @Override
    public AnimationHolder getCastStartAnimation() {
        // First cast = long charge animation
        if (recastStage == 1) {
            return SpellAnimations.ANIMATION_CHARGED_CAST;
        }
        // Recasts = instant animation
        return null;
    }

    @Override
    public AnimationHolder getCastFinishAnimation() {
        // Recasts = instant animation
        return SpellAnimations.ANIMATION_INSTANT_CAST;
    }

    @Override
    public Optional<SoundEvent> getCastStartSound() {
        return recastStage == 1 ? Optional.of(SoundRegistry.FIREBALL_START.get()) : Optional.of(ESSounds.HOLY_BLAST_CAST.get());
    }

    @Override
    public boolean canBeInterrupted(@Nullable Player player) {
            return true;
    }

    @Override
    public boolean checkPreCastConditions(Level level, int spellLevel, LivingEntity entity, MagicData playerMagicData) {

        if (playerMagicData != null && playerMagicData.getPlayerRecasts() != null) {

            int remaining = playerMagicData.getPlayerRecasts()
                    .getRemainingRecastsForSpell(spellId.toString());

            if (remaining <= 0 || remaining >= 3) {
                recastStage = 1;
            } else {
                recastStage = 2;
            }

        } else {
            recastStage = 1;
        }

        return super.checkPreCastConditions(level, spellLevel, entity, playerMagicData);
    }


    @Override
    public DefaultConfig getDefaultConfig() {
        return defaultConfig;
    }

    @Override
    public ResourceLocation getSpellResource() {
        return spellId;
    }

    @Override
    public Optional<SoundEvent> getCastFinishSound() {
        if (recastStage == 1) {
            return Optional.of(ESSounds.HOLY_BLAST_CAST.get());
        }
        return Optional.empty();
    }

    @Override
    public int getRecastCount(int spellLevel, @Nullable LivingEntity entity) {
        return 3;
    }

    @Override
    public void onCast(Level world, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {

        isRecast = playerMagicData.getPlayerRecasts().hasRecastForSpell(getSpellId());

        if (!isRecast) {
            playerMagicData.getPlayerRecasts().addRecast(
                    new RecastInstance(getSpellId(), spellLevel, getRecastCount(spellLevel, entity), 8 * 20, castSource, null),
                    playerMagicData
            );
        }

        Vec3 origin = entity.getEyePosition();

        HolyBlast holyBlast = new HolyBlast(world, entity);

        holyBlast.setDamage(getDamage(spellLevel, entity));
        holyBlast.setExplosionRadius((float)this.getRadius(spellLevel, entity));

        holyBlast.setPos(origin.add(entity.getForward()).subtract(0, holyBlast.getBbHeight() / 2, 0));
        holyBlast.shoot(entity.getLookAngle());

        holyBlast.setSpellLevel(spellLevel);

        world.addFreshEntity(holyBlast);

        super.onCast(world, spellLevel, entity, castSource, playerMagicData);
    }


    public float getDamage(int spellLevel, LivingEntity caster) {
        if (caster == null) {
            return (float) getSpellPower(spellLevel, null) * 7;
        }
        double firePower = caster.getAttributeValue(AttributeRegistry.FIRE_SPELL_POWER);
        double holyPower = caster.getAttributeValue(AttributeRegistry.HOLY_SPELL_POWER);
        return (float)(15 + 7.0 * getSpellPower(spellLevel, caster) * ((0.75 * firePower ) + (0.75 * holyPower)));
    }


    public int getRadius(int spellLevel, LivingEntity caster) {
        return 1 + (int) getSpellPower(spellLevel, caster);
    }
}