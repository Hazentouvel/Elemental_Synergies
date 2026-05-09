package net.hazen.elemental_synergies.Spells.Schools.Fire.SoulFire;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.api.spells.CastSource;
import io.redspace.ironsspellbooks.api.spells.CastType;
import io.redspace.ironsspellbooks.api.spells.SpellRarity;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.damage.SpellDamageSource;
import io.redspace.ironsspellbooks.entity.spells.firebolt.FireboltProjectile;

import java.util.ArrayList;
import java.util.List;

import net.hazen.elemental_synergies.ESUtilities.ESSpellDamageSource;
import net.hazen.elemental_synergies.ESUtilities.SubSchools.ESSubSchoolRegistry;
import net.hazen.elemental_synergies.Entities.Spells.Fire.SoulFlame.SoulflameBolt.SoulflameBolt;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class SoulflameBoltSpell extends AbstractSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath("elemental_synergies", "soulflame_bolt");
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

    public SoulflameBoltSpell() {
        this.defaultConfig = (new DefaultConfig())
                .setMinRarity(SpellRarity.COMMON)
                .setSchoolResource(ESSubSchoolRegistry.SOUL_FLAME_RESOURCE)
                .setMaxLevel(10)
                .setCooldownSeconds((double)1.0F)
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

    public void onCast(Level world, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        SoulflameBolt firebolt = new SoulflameBolt(world, entity);
        firebolt.setPos(entity.position().add((double)0.0F, (double)entity.getEyeHeight() - firebolt.getBoundingBox().getYsize() * (double)0.5F, (double)0.0F));
        firebolt.shoot(entity.getLookAngle());
        firebolt.setDamage(this.getDamage(spellLevel, entity));
        world.addFreshEntity(firebolt);
        super.onCast(world, spellLevel, entity, castSource, playerMagicData);
    }

    public ESSpellDamageSource getDamageSource(@Nullable Entity projectile, Entity attacker) {
        return ESSpellDamageSource.source(projectile == null ? attacker : projectile, attacker, this).setSoulFireTicks(60);
    }

    private float getDamage(int spellLevel, LivingEntity entity) {
        return this.getSpellPower(spellLevel, entity) * 0.5F;
    }
}
