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
import net.minecraft.util.Mth;
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

        float angleStep = 14.0F;

        double firstAngleOffset =
                ((double)(waveCount - 1) / 2.0D) * angleStep;

        double spawnDistance = 1.5D;

        for (int i = 0; i < waveCount; i++) {

            double angle =
                    entity.getYRot()
                            - firstAngleOffset
                            + (i * angleStep);

            double rad = Math.toRadians(angle);

            double dx = -Math.sin(rad);
            double dz = Math.cos(rad);

            double spawnX = spawn.x + dx * spawnDistance;
            double spawnY = spawn.y;
            double spawnZ = spawn.z + dz * spawnDistance;

            // Push rear waves backward at 5 waves
            if (waveCount == 5 && i >= 3) {

                double backRad = Math.toRadians(entity.getYRot());

                spawnX -= -Math.sin(backRad) * 4.0D;
                spawnZ -=  Math.cos(backRad) * 4.0D;
            }

            WaveMagicProjectileEntity wave =
                    new WaveMagicProjectileEntity(
                            level,
                            entity,
                            100,
                            this.getDamage(spellLevel, entity)
                    );

            wave.setPos(spawnX, spawnY, spawnZ);

            wave.setState(1);

            wave.setYRot(
                    -((float)(Mth.atan2(dx, dz) * (180D / Math.PI)))
            );

            level.addFreshEntity(wave);
        }

        super.onCast(level, spellLevel, entity, castSource, playerMagicData);
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
