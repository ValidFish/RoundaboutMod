package net.hydra.jojomod.client.models.visages;

import net.hydra.jojomod.entity.visages.mobs.AvdolNPC;
import net.hydra.jojomod.event.powers.StandPowers;
import net.hydra.jojomod.event.powers.stand.PowersStarPlatinum;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class AvdolModel<T extends AvdolNPC> extends PlayerLikeModel<T> {
    public AvdolModel(ModelPart root) {
        initParts(root);
    }

    @Override
    public boolean getSlim(){
        return false;
    }

    @Override
    public void defaultModifiers(T entity) {
        this.head.yScale *= 0.95F;
        this.head.xScale *= 0.95F;
        this.head.zScale *= 0.95F;
        this.hat.yScale *= 0.95F;
        this.hat.xScale *= 0.95F;
        this.hat.zScale *= 0.95F;
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
                .texOffs(56, 20).addBox(-3.0F, -9.375F, -3.225F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.2F))
                .texOffs(56, 20).addBox(-3.0F, -9.45F, 1.05F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.2F))
                .texOffs(56, 20).addBox(1.0F, -9.35F, -3.25F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.2F))
                .texOffs(56, 20).addBox(1.0F, -9.475F, 1.15F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.2F))
                .texOffs(56, 20).addBox(-1.0F, -9.475F, -1.275F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.2F))
                .texOffs(57, 24).addBox(-1.0F, -0.975F, 3.075F, 2.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r1 = hat.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(56, 20).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(2.95F, -7.3F, 0.0F, 0.0F, 0.0F, 0.1963F));

        PartDefinition cube_r2 = hat.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(56, 20).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(-3.0F, -7.3F, -0.075F, 0.0F, 0.0F, -0.2182F));

        PartDefinition cube_r3 = hat.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(56, 20).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(0.0F, -7.3F, -2.95F, 0.1833F, 0.0F, 0.0F));

        PartDefinition cube_r4 = hat.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(56, 20).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(0.0F, -7.3F, 2.75F, -0.2138F, 0.0F, 0.0F));

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

        PartDefinition jacket_r1 = jacket.addOrReplaceChild("jacket_r1", CubeListBuilder.create().texOffs(28, 64).addBox(-7.0F, -12.0F, -1.0F, 8.0F, 20.0F, 4.0F, new CubeDeformation(0.255F)), PartPose.offsetAndRotation(3.0F, 11.775F, -1.2F, 0.1745F, 0.0F, 0.0F));

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
