package net.hydra.jojomod.client.models.worn_stand;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.hydra.jojomod.Roundabout;
import net.hydra.jojomod.client.ClientUtil;
import net.hydra.jojomod.client.models.PsuedoHierarchicalModel;
import net.hydra.jojomod.client.models.layers.animations.HeyYaAnimations;
import net.hydra.jojomod.client.models.layers.animations.LayerAnimations;
import net.hydra.jojomod.entity.stand.D4CEntity;
import net.hydra.jojomod.event.powers.StandUser;
import net.hydra.jojomod.event.powers.TimeStop;
import net.hydra.jojomod.stand.powers.PowersHeyYa;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class HeyYaModel extends PsuedoHierarchicalModel {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
   private final ModelPart stand;
    private final ModelPart Root;

    public HeyYaModel() {
        super(RenderType::entityTranslucent);

        this.Root = createBodyLayer().bakeRoot();
        this.stand = Root.getChild("stand");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition stand = partdefinition.addOrReplaceChild("stand", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition stand2 = stand.addOrReplaceChild("stand2", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition head = stand2.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, -20.0F, 1.5F));

        PartDefinition head2 = head.addOrReplaceChild("head2", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0873F, 0.0F, 0.0F));

        PartDefinition jaw = head2.addOrReplaceChild("jaw", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -0.6F, 3.5F, -0.0873F, 0.0F, 0.0F));

        PartDefinition cube_r1 = jaw.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 14).addBox(-3.5F, 0.9914F, -5.5F, 7.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.2439F, -1.7218F, 0.0873F, 0.0F, 0.0F));

        PartDefinition upper_head = head2.addOrReplaceChild("upper_head", CubeListBuilder.create().texOffs(0, 0).addBox(-3.3772F, -7.2F, -6.925F, 7.0F, 7.0F, 7.0F, new CubeDeformation(-0.01F))
                .texOffs(0, 22).addBox(0.1228F, -9.2F, -2.925F, 0.0F, 8.0F, 8.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(-0.1228F, -0.775F, 3.325F, -0.0873F, 0.0F, 0.0F));

        PartDefinition cube_r2 = upper_head.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(28, 0).addBox(0.5F, -6.0F, -0.5F, 0.0F, 8.0F, 8.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(0.0F, -3.2F, -2.2286F, 0.0F, 0.6109F, 0.0F));

        PartDefinition cube_r3 = upper_head.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(16, 22).addBox(0.5F, -6.0F, -0.5F, 0.0F, 8.0F, 8.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(-0.5676F, -3.2F, -2.772F, 0.0F, -0.5672F, 0.0F));

        PartDefinition body = stand2.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, -20.0F, 1.5F));

        PartDefinition body2 = body.addOrReplaceChild("body2", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition torso = body2.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(32, 29).addBox(-1.6617F, -9.0804F, -0.5F, 4.0F, 10.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(32, 23).addBox(-3.1617F, -0.3052F, -1.5F, 7.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(32, 16).addBox(-3.6617F, -8.0804F, -1.5F, 8.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.3383F, 9.0804F, -0.5F));

        PartDefinition left_arm = torso.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(0, 38).addBox(-0.0655F, -1.0F, -0.9128F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.4F, -7.0F, 0.5F, 0.0F, 0.0873F, 0.0F));

        PartDefinition right_arm = torso.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.offset(-3.6F, -7.0F, 0.45F));

        PartDefinition cube_r4 = right_arm.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 38).addBox(-1.0F, -5.0F, -1.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0617F, 4.0F, 0.05F, 0.0F, 3.1416F, 0.0F));

        PartDefinition legs = body2.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offset(0.0F, 20.0F, -1.5F));

        PartDefinition left_leg = legs.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(32, 41).addBox(-1.0317F, -0.5804F, -1.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(2.3617F, -9.9196F, 1.5F));

        PartDefinition right_leg = legs.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.offset(-2.7F, -10.0F, 1.5F));

        PartDefinition cube_r5 = right_leg.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(32, 41).addBox(-1.0F, -1.025F, -1.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.03F, 0.525F, 0.0F, 0.0F, 3.1416F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }
    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        stand.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart root() {
        return Root;
    }

    @Override
    public void setupAnim(Entity var1, float pAgeInTicks) {

    }
    public static ResourceLocation base = new ResourceLocation(Roundabout.MOD_ID,
            "textures/stand/hey_ya/base.png");
    public static ResourceLocation goth = new ResourceLocation(Roundabout.MOD_ID,
            "textures/stand/hey_ya/goth.png");
    public static ResourceLocation volume_2 = new ResourceLocation(Roundabout.MOD_ID,
            "textures/stand/hey_ya/volume_2.png");
    public static ResourceLocation chapter_24 = new ResourceLocation(Roundabout.MOD_ID,
            "textures/stand/hey_ya/chapter_24.png");

    public ResourceLocation getTextureLocation(Entity context, byte skin){
        switch (skin)
        {
            case PowersHeyYa.GOTHIC -> {return goth;}
            case PowersHeyYa.VOLUME_2 -> {return volume_2;}
            case PowersHeyYa.CHAPTER_24 -> {return chapter_24;}
            default -> {return base;}
        }
    }

    public void render(Entity context, PoseStack poseStack, MultiBufferSource bufferSource, int light) {
        VertexConsumer consumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(getTextureLocation(context, (byte)0)));
        root().render(poseStack, consumer, light, OverlayTexture.NO_OVERLAY);
    }
    public void render(Entity context, float partialTicks, PoseStack poseStack, MultiBufferSource bufferSource,
                       int light, float r, float g, float b, float alpha, byte skin) {
        if (context instanceof LivingEntity LE) {
            this.root().getAllParts().forEach(ModelPart::resetPose);
            if (((TimeStop)context.level()).CanTimeStopEntity(context) || ClientUtil.checkIfGamePaused()){
                partialTicks = 0;
            }
            StandUser user = ((StandUser) LE);
            VertexConsumer consumer = bufferSource.getBuffer(RenderType.entityTranslucent(getTextureLocation(context, skin)));
            user.roundabout$getHeyYaAnimation().startIfStopped(context.tickCount);
            this.animate(user.roundabout$getHeyYaAnimation(), HeyYaAnimations.hangin_on, partialTicks, 1f);
            this.animate(user.roundabout$getHeyYaAnimation(), HeyYaAnimations.idle_normal, partialTicks, 1f);
            this.animate(user.roundabout$getHeyYaAnimation2(), HeyYaAnimations.talk, partialTicks, 1f);
            //The number at the end is inversely proportional so 2 is half speed
            root().render(poseStack, consumer, light, OverlayTexture.NO_OVERLAY, r, g, b, alpha);
        }
    }

}

