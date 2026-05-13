package net.hazen.elemental_synergies.Spells.Schools.Fire.SupremeCalamitas;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.spells.CastSource;
import io.redspace.ironsspellbooks.api.spells.CastType;
import io.redspace.ironsspellbooks.api.spells.SpellRarity;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.damage.SpellDamageSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import net.acetheeldritchking.aces_spell_utils.registries.ASAttributeRegistry;
import net.hazen.elemental_synergies.ESUtilities.SubSchools.ESSubSchoolRegistry;
import net.hazen.elemental_synergies.Entities.Spells.Fire.SupremeCalamitas.BrimflameBolt.BrimflameBolt;
import net.hazen.elemental_synergies.Registries.ESSounds;
import net.hazen.elemental_synergies.Spells.AbstractSpells.BrimstoneSpells;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class BrimflameBoltSpell extends BrimstoneSpells {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath("elemental_synergies", "brimflame_bolt");
    private final DefaultConfig defaultConfig;

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
                )
        ));
        return li;
    }

    public BrimflameBoltSpell() {
        this.defaultConfig = (new DefaultConfig())
                .setMinRarity(SpellRarity.COMMON)
                .setSchoolResource(ESSubSchoolRegistry.BRIMSTONE_RESOURCE)
                .setMaxLevel(10)
                .setCooldownSeconds((double)1.5F)
                .build();
        this.manaCostPerLevel = 2;
        this.baseSpellPower = 12;
        this.spellPowerPerLevel = 1;
        this.castTime = 0;
        this.baseManaCost = 10;
    }

    public CastType getCastType() {
        return CastType.INSTANT;
    }

    public DefaultConfig getDefaultConfig() {
        return this.defaultConfig;
    }

    public ResourceLocation getSpellResource() {
        return this.spellId;
    }

    @Override
    public Optional<SoundEvent> getCastStartSound() {
        return Optional.of(ESSounds.BRIMFLAME_BOLT_CAST.get());
    }

    public void onCast(Level world, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        BrimflameBolt brimflameBolt = new BrimflameBolt(world, entity);
        brimflameBolt.setPos(entity.position().add((double)0.0F, (double)entity.getEyeHeight() - brimflameBolt.getBoundingBox().getYsize() * (double)0.5F, (double)0.0F));
        brimflameBolt.shoot(entity.getLookAngle());
        brimflameBolt.setDamage(this.getDamage(spellLevel, entity));
        world.addFreshEntity(brimflameBolt);
        super.onCast(world, spellLevel, entity, castSource, playerMagicData);
    }

    public SpellDamageSource getDamageSource(@Nullable Entity projectile, Entity attacker) {
        return super.getDamageSource(projectile, attacker).setFireTicks(60);
    }

    public float getDamage(int spellLevel, LivingEntity caster) {
        if (caster == null) {
            return (float) getSpellPower(spellLevel, null) * 7;
        }
        double firePower = caster.getAttributeValue(AttributeRegistry.FIRE_SPELL_POWER);
        double bloodPower = caster.getAttributeValue(AttributeRegistry.BLOOD_SPELL_POWER);
        double occultPower = caster.getAttributeValue(ASAttributeRegistry.RITUAL_MAGIC_POWER);
        return (float)(2.0 + getSpellPower(spellLevel, caster) * (((firePower + bloodPower + occultPower) * 0.85))
        );
    }
}
