package net.hydra.jojomod.client.models.visages;

import net.hydra.jojomod.entity.visages.mobs.DiegoNPC;
import net.hydra.jojomod.event.powers.StandPowers;
import net.hydra.jojomod.event.powers.stand.PowersStarPlatinum;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class DiegoModel<T extends DiegoNPC> extends PlayerLikeModel<T> {
    public DiegoModel(ModelPart root) {
        initParts(root);
    }

    @Override
    public boolean getSlim(){
        return false;
    }

    @Override
    public void defaultModifiers(T entity) {
        super.defaultModifiers(entity);
    }
    public static LayerDefinition getTexturedModelData() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition playerlike = partdefinition.addOrReplaceChild("playerlike", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition full_body = playerlike.addOrReplaceChild("full_body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition head_part = full_body.addOrReplaceChild("head_part", CubeListBuilder.create(), PartPose.offset(0.0F, -24.0F, 0.0F));

        PartDefinition head = head_part.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition hat = head_part.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F))
                .texOffs(46, 64).addBox(-4.075F, -8.375F, -4.725F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(46, 74).addBox(1.1F, -8.375F, -4.725F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(46, 79).addBox(1.1F, -4.075F, -4.725F, 3.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r1 = hat.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(46, 69).addBox(-2.5F, -3.0F, -1.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.625F, -5.825F, -3.725F, 0.0F, 0.0F, -0.4276F));

        PartDefinition cube_r2 = hat.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(44, 79).addBox(-4.0F, -3.0F, -2.0F, 10.0F, 1.0F, 3.0F, new CubeDeformation(-0.4F)), PartPose.offsetAndRotation(-0.975F, -2.5F, -4.0F, 0.4451F, 0.0F, 0.0F));

        PartDefinition body_part = full_body.addOrReplaceChild("body_part", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition legs = body_part.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offset(-5.0F, -24.0F, 0.0F));

        PartDefinition right_legs = legs.addOrReplaceChild("right_legs", CubeListBuilder.create(), PartPose.offset(3.0F, 12.0F, 0.0F));

        PartDefinition right_pants = right_legs.addOrReplaceChild("right_pants", CubeListBuilder.create().texOffs(0, 32).addBox(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(-0.1F, 0.0F, 0.0F));

        PartDefinition right_leg = right_legs.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition left_legs = legs.addOrReplaceChild("left_legs", CubeListBuilder.create(), PartPose.offset(7.0F, 12.0F, 0.0F));

        PartDefinition left_leg = left_legs.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(16, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition left_pants = left_legs.addOrReplaceChild("left_pants", CubeListBuilder.create().texOffs(0, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.249F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition upper_body = body_part.addOrReplaceChild("upper_body", CubeListBuilder.create(), PartPose.offset(0.0F, -13.0F, 0.0F));

        PartDefinition right_arms = upper_body.addOrReplaceChild("right_arms", CubeListBuilder.create(), PartPose.offset(-3.5F, -10.0F, 0.0F));

        PartDefinition right_arm = right_arms.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-3.8F, -3.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.7F, 2.0F, 0.0F));

        PartDefinition right_sleeve = right_arms.addOrReplaceChild("right_sleeve", CubeListBuilder.create().texOffs(40, 32).addBox(-3.8F, -3.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(-0.7F, 2.0F, 0.0F));

        PartDefinition left_arms = upper_body.addOrReplaceChild("left_arms", CubeListBuilder.create(), PartPose.offset(3.5F, -10.0F, 0.0F));

        PartDefinition left_arm = left_arms.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(32, 48).addBox(-0.2F, -3.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.7F, 2.0F, 0.0F));

        PartDefinition left_sleeve = left_arms.addOrReplaceChild("left_sleeve", CubeListBuilder.create().texOffs(48, 48).addBox(-0.2F, -3.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.24F)), PartPose.offset(0.7F, 2.0F, 0.0F));

        PartDefinition body = upper_body.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, -11.0F, 0.0F));

        PartDefinition jacket = upper_body.addOrReplaceChild("jacket", CubeListBuilder.create().texOffs(16, 32).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.255F)), PartPose.offset(0.0F, -11.0F, 0.0F));

        PartDefinition cloak = upper_body.addOrReplaceChild("cloak", CubeListBuilder.create().texOffs(78, 15).addBox(-5.0F, 0.0F, 2.5F, 10.0F, 16.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -11.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }
    StandPowers Power = new PowersStarPlatinum(null);

    @Override
    public void setupAnim(T pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        super.setupAnim(pEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);
        defaultModifiers(pEntity);
    }

    @Override
    public ModelPart root() {
        return playerlike;
    }
}
