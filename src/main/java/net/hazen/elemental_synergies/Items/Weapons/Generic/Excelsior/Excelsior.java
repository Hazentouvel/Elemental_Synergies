package net.hazen.elemental_synergies.Items.Weapons.Generic.Excelsior;

import io.redspace.ironsspellbooks.api.item.weapons.ExtendedSwordItem;
import io.redspace.ironsspellbooks.api.registry.SpellDataRegistryHolder;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import net.acetheeldritchking.aces_spell_utils.items.example.items.weapons.ASWeaponTiers;
import net.acetheeldritchking.aces_spell_utils.items.weapons.ActiveAndPassiveAbilityMagicSwordItem;
import net.acetheeldritchking.aces_spell_utils.utils.ASRarities;
import net.ender.ess_requiem.registries.GGSpellRegistry;
import net.hazen.elemental_synergies.ESUtilities.ESRarities;
import net.hazen.elemental_synergies.ESUtilities.Items.ESExtendedWeaponsTiers;
import net.hazen.elemental_synergies.Registries.Effects.MidasTouchedEffect;
import net.hazen.hazennstuff.HnSUtilities.Item.HnSExtendedWeaponsTiers;
import net.hazen.hazennstuff.Spells.HnSSpellRegistries;
import net.hazen.hazentouvelib.Rarities.HLRarities;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.function.Consumer;

@EventBusSubscriber
public class Excelsior extends ActiveAndPassiveAbilityMagicSwordItem implements GeoItem {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public Excelsior() {
        super(HnSExtendedWeaponsTiers.NIGHTS_EDGE, ItemPropertiesHelper.equipment(1)
                        .fireResistant()
                        .rarity((Rarity)ESRarities.SPELLBLADE_RARITY.getValue())
                        .attributes(ExtendedSwordItem
                        .createAttributes(ESExtendedWeaponsTiers.EXCELSIOR)),
                SpellDataRegistryHolder.of(new SpellDataRegistryHolder[]{
                        new SpellDataRegistryHolder(GGSpellRegistry.PARRY, 10)
                })
        );
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        //controllerRegistrar.add(animationController);
    }

    // Animations and stuff
    private static final RawAnimation IDLE_ANIMATION = RawAnimation.begin().thenLoop("idle");

    private final AnimationController<Excelsior> animationController = new AnimationController<>(this, "controller", 0, this::predicate);

    // Make your animations in this predicate
    private PlayState predicate(AnimationState<Excelsior> event)
    {
        event.getController().setAnimation(IDLE_ANIMATION);

        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    // Your renderer for items
    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private ExcelsiorRenderer renderer;

            @Override
            public BlockEntityWithoutLevelRenderer getGeoItemRenderer() {
                if (this.renderer == null)
                    this.renderer = new ExcelsiorRenderer();

                return this.renderer;
            }
        });
    }

    @Override
    protected int getPassiveCooldownTicks() {
        return 400;
    }


    @SubscribeEvent
    public static void handleAbility(LivingIncomingDamageEvent event) {

        Entity attacker = event.getSource().getEntity();

        if (!(attacker instanceof ServerPlayer player))
            return;

        ItemStack weapon = player.getWeaponItem();

        if (!(weapon.getItem() instanceof Excelsior excelsior))
            return;

        LivingEntity victim = event.getEntity();

        if (victim == attacker)
            return;

        if (!player.getCooldowns().isOnCooldown(excelsior)) {
            MidasTouchedEffect.tryApplyIfNotImmune(victim, 200, 0, false, true, true);
            player.getCooldowns().addCooldown(excelsior, excelsior.getPassiveCooldownTicks());
        }
    }


}
