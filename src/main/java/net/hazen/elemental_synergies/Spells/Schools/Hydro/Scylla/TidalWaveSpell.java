package net.hazen.elemental_synergies.Spells.Schools.Hydro.Scylla;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.api.spells.CastSource;
import io.redspace.ironsspellbooks.api.spells.CastType;
import io.redspace.ironsspellbooks.api.spells.SpellAnimations;
import io.redspace.ironsspellbooks.api.spells.SpellRarity;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.entity.spells.StompAoe;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import java.util.List;
import java.util.Optional;

import net.acetheeldritchking.aces_spell_utils.registries.ASSchoolRegistry;
import net.hazen.elemental_synergies.Entities.Spells.Hydro.Scylla.TidalWave.TidalWaveStomp;
import net.hazen.elemental_synergies.Entities.Spells.Hydro.Scylla.TidalWave.WaveMagicProjectileEntity;
import net.hazen.hazennstuff.Spells.AbstractSpells.HydroSpells;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class TidalWaveSpell extends HydroSpells {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath("elemental_synergies", "tidal_wave");
    private final DefaultConfig defaultConfig;

    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(Component.translatable("ui.irons_spellbooks.damage", new Object[]{Utils.stringTruncation((double)this.getDamage(spellLevel, caster), 2)}), Component.translatable("ui.irons_spellbooks.distance", new Object[]{this.getRange(spellLevel, caster)}));
    }

    public TidalWaveSpell() {
        this.defaultConfig = (new DefaultConfig())
                .setMinRarity(SpellRarity.UNCOMMON)
                .setSchoolResource(ASSchoolRegistry.HYDRO_RESOURCE)
                .setMaxLevel(5)
                .setCooldownSeconds((double)16.0F)
                .build();
        this.manaCostPerLevel = 10;
        this.baseSpellPower = 8;
        this.spellPowerPerLevel = 2;
        this.castTime = 10;
        this.baseManaCost = 50;
    }

    public Optional<SoundEvent> getCastFinishSound() {
        return Optional.of((SoundEvent)SoundRegistry.EARTHQUAKE_CAST.get());
    }

    public CastType getCastType() {
        return CastType.LONG;
    }

    public boolean canBeInterrupted(Player player) {
        return false;
    }

    public int getEffectiveCastTime(int spellLevel, @Nullable LivingEntity entity) {
        return this.getCastTime(spellLevel);
    }

    public DefaultConfig getDefaultConfig() {
        return this.defaultConfig;
    }

    public ResourceLocation getSpellResource() {
        return this.spellId;
    }

    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        Vec3 spawn = Utils.moveToRelativeGroundLevel(level, entity.getEyePosition().add(entity.getForward().multiply(1.0D, 0.0D, 1.0D)), 1);

        BlockPos bpos = BlockPos.containing(spawn);
        ((ServerLevel) level).sendParticles((new BlockParticleOption(ParticleTypes.BLOCK, level.getBlockState(bpos))).setPos(bpos), spawn.x, spawn.y, spawn.z, 40, 0.0D, 0.0D, 0.0D, 0.2D + (0.05F * spellLevel));

        TidalWaveStomp stomp = new TidalWaveStomp(level, this.getRange(spellLevel, entity), entity.getYRot());

        stomp.moveTo(spawn);
        stomp.setDamage(this.getDamage(spellLevel, entity));
        stomp.setExplosionRadius(this.getEntityPowerMultiplier(entity));
        stomp.setOwner(entity);
        level.addFreshEntity(stomp);
        int waveCount = Math.min(1 + ((spellLevel - 1) * 2), 5);

        float frontSpread = 28.0F;

        if (waveCount == 1) {
            Vec3 direction = entity.getLookAngle().normalize();
            Vec3 waveSpawn = spawn.add(direction.scale(1.25D));
            WaveMagicProjectileEntity wave = new WaveMagicProjectileEntity(level, entity, 100, this.getDamage(spellLevel, entity));

            wave.moveTo(waveSpawn.x, waveSpawn.y, waveSpawn.z, entity.getYRot(), 0.0F);
            wave.setYRot(entity.getYRot());
            wave.setState(1);
            wave.setDeltaMovement(direction.scale(0.35D));

            level.addFreshEntity(wave);
        }
        else if (waveCount == 3) {

            for (int i = 0; i < 3; i++) {
                float angleOffset = -frontSpread / 2.0F + (frontSpread / 2.0F) * i;
                float waveRot = entity.getYRot() + angleOffset;

                Vec3 direction = Vec3.directionFromRotation(0.0F, waveRot);
                Vec3 waveSpawn = spawn.add(direction.scale(1.25D));

                WaveMagicProjectileEntity wave = new WaveMagicProjectileEntity(level, entity, 100, this.getDamage(spellLevel, entity));
                wave.moveTo(waveSpawn.x, waveSpawn.y, waveSpawn.z, waveRot, 0.0F);
                wave.setYRot(waveRot);
                wave.setState(1);
                wave.setDeltaMovement(direction.normalize().scale(0.35D));

                level.addFreshEntity(wave);
            }
        }
        else if (waveCount == 5) {

            // FRONT 3 WAVES
            for (int i = 0; i < 3; i++) {
                float angleOffset = -frontSpread / 2.0F + (frontSpread / 2.0F) * i;
                float waveRot = entity.getYRot() + angleOffset;

                Vec3 direction = Vec3.directionFromRotation(0.0F, waveRot);
                Vec3 waveSpawn = spawn.add(direction.scale(1.25D));
                WaveMagicProjectileEntity wave = new WaveMagicProjectileEntity(level, entity, 100, this.getDamage(spellLevel, entity));

                wave.moveTo(waveSpawn.x, waveSpawn.y, waveSpawn.z, waveRot, 0.0F);
                wave.setYRot(waveRot);
                wave.setState(1);
                wave.setDeltaMovement(direction.normalize().scale(0.35D));

                level.addFreshEntity(wave);
            }

            for (int i = 0; i < 2; i++) {
                float angleOffset = (i == 0) ? -12.0F : 12.0F;
                float waveRot = entity.getYRot() + angleOffset;

                Vec3 direction = Vec3.directionFromRotation(0.0F, waveRot);
                Vec3 backOffset = entity.getLookAngle().normalize().scale(-3.0D);
                Vec3 waveSpawn = spawn.add(backOffset).add(direction.scale(1.0D));

                WaveMagicProjectileEntity wave = new WaveMagicProjectileEntity(level, entity, 100, this.getDamage(spellLevel, entity));
                wave.moveTo(waveSpawn.x, waveSpawn.y, waveSpawn.z, waveRot, 0.0F);
                wave.setYRot(waveRot);
                wave.setState(1);
                wave.setDeltaMovement(direction.normalize().scale(0.35D));
                level.addFreshEntity(wave);
            }
        }

        super.onCast(
                level,
                spellLevel,
                entity,
                castSource,
                playerMagicData
        );
    }
    private float getDamage(int spellLevel, LivingEntity caster) {
        return this.getSpellPower(spellLevel, caster);
    }

    private int getRange(int spellLevel, LivingEntity caster) {
        return (int)(4.0F + (float)spellLevel * this.getEntityPowerMultiplier(caster));
    }

    public AnimationHolder getCastStartAnimation() {
        return SpellAnimations.STOMP;
    }

    public AnimationHolder getCastFinishAnimation() {
        return AnimationHolder.pass();
    }

    public boolean shouldAIStopCasting(int spellLevel, Mob mob, LivingEntity target) {
        float f = (float)this.getRange(spellLevel, mob);
        return mob.distanceToSqr(target) > (double)(f * f) * 1.2;
    }
}
