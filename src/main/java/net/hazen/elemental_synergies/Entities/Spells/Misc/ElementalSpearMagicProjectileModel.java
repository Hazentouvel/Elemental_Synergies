package net.hazen.elemental_synergies.Entities.Spells.Misc;

import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

//Base Cataclysm btw.

public class ElementalSpearMagicProjectileModel extends HierarchicalModel<ElementalSpearMagicProjectile> {
    private final ModelPart root;
    private final ModelPart rot;

    public ElementalSpearMagicProjectileModel(ModelPart root) {
        this.root = root.getChild("root");
        this.rot = this.root.getChild("rot");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, -4.0F, 0.0F));
        PartDefinition rot = root.addOrReplaceChild("rot", CubeListBuilder.create().texOffs(0, -64).addBox(0.0F, -7.5F, -23.0F, 0.0F, 15.0F, 64.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        rot.addOrReplaceChild("root_r1", CubeListBuilder.create().texOffs(0, -64).addBox(0.0F, -7.5F, -23.0F, 0.0F, 15.0F, 64.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));
        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    public void setupAnim(ElementalSpearMagicProjectile entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.root.yRot = netHeadYaw * ((float)Math.PI / 180F);
        this.root.xRot = headPitch * ((float)Math.PI / 180F);
        this.animate(entity.getAnimationState("idle"), ElementalSpearMagicProjectileAnimation.IDLE, ageInTicks, 1.0F);
        this.animate(entity.getAnimationState("spawn"), ElementalSpearMagicProjectileAnimation.SPAWN, ageInTicks, 1.0F);
    }

    public void setupAnim(float yRot, float xRot) {
    }

    public ModelPart root() {
        return this.root;
    }
}
