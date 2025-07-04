package net.hydra.jojomod.entity.projectile;

import net.hydra.jojomod.access.NoVibrationEntity;
import net.hydra.jojomod.access.PenetratableWithProjectile;
import net.hydra.jojomod.client.ClientNetworking;
import net.hydra.jojomod.entity.UnburnableProjectile;
import net.hydra.jojomod.event.ModParticles;
import net.hydra.jojomod.sound.ModSounds;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.UUID;

public class SoftAndWetBubbleEntity extends AbstractHurtingProjectile implements UnburnableProjectile, NoVibrationEntity, PenetratableWithProjectile {
    public SoftAndWetBubbleEntity(EntityType<? extends SoftAndWetBubbleEntity> $$0, Level $$1) {
        super($$0, $$1);
    }

    public int lifeSpan = 0;
    public LivingEntity standUser;
    public UUID standUserUUID;
    private static final EntityDataAccessor<Boolean> ACTIVATED = SynchedEntityData.defineId(SoftAndWetBubbleEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> LAUNCHED = SynchedEntityData.defineId(SoftAndWetBubbleEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Float> SPEED = SynchedEntityData.defineId(SoftAndWetBubbleEntity.class, EntityDataSerializers.FLOAT);
    public boolean getActivated() {
        return this.getEntityData().get(ACTIVATED);
    }
    public void setActivated(boolean activ) {
        this.getEntityData().set(ACTIVATED, activ);
    }
    public boolean getLaunched() {
        return this.getEntityData().get(LAUNCHED);
    }
    public void setLaunched(boolean activ) {
        this.getEntityData().set(LAUNCHED, activ);
    }
    public float getSped() {
        return this.getEntityData().get(SPEED);
    }
    public void setSped(float sped) {
        this.getEntityData().set(SPEED, sped);
    }

    @Override
    public boolean hurt(DamageSource $$0, float $$1) {
        if (this.isInvulnerableTo($$0)) {
            return false;
        } else {
            this.popBubble();
        }
        return true;
    }

    @Override
    public boolean dealWithPenetration(Entity proj){
        popBubble();
        return true;
    }
    private static final EntityDataAccessor<Integer> USER_ID = SynchedEntityData.defineId(SoftAndWetBubbleEntity.class, EntityDataSerializers.INT);
    protected SoftAndWetBubbleEntity(EntityType<? extends SoftAndWetBubbleEntity> $$0, double $$1, double $$2, double $$3, Level $$4) {
        this($$0, $$4);
        this.setPos($$1, $$2, $$3);
    }
    public int getUserID() {
        return this.getEntityData().get(USER_ID);
    }
    public void setUserID(int idd) {
        this.getEntityData().set(USER_ID, idd);
        if (this.level().getEntity(this.getUserID()) instanceof LivingEntity LE){
            this.standUser = LE;
            if (!this.level().isClientSide()){
                standUserUUID = LE.getUUID();
            }
        }
    }

    public void tick() {
        if (!this.level().isClientSide()) {
            if (this.getOwner() == null || !this.getOwner().isAlive() || this.getOwner().isRemoved() ||
            this.getOwner().distanceTo(this) > getDistanceUntilPopping() && distancePops()){
                popBubble();
                return;
            }
        }
        super.tick();
    }

    public boolean distancePops(){
        return true;
    }
    public int getDistanceUntilPopping(){
        return ClientNetworking.getAppropriateConfig().softAndWetSettings.maxPlunderBubbleTravelDistanceBeforePopping;
    }

    /**No sculker noises*/
    @Override
    public boolean getVibration(){
        return false;
    }
    public void setUser(LivingEntity User) {
        standUser = User;
        this.getEntityData().set(USER_ID, User.getId());
        if (!this.level().isClientSide()){
            standUserUUID = User.getUUID();
        }
    }

    /**Bubbles Don't alert skulk at all*/
    @Override
    public void gameEvent(GameEvent $$0, @Nullable Entity $$1) {
    }

    @Override
    protected ParticleOptions getTrailParticle() {
        return new BlockParticleOption(ParticleTypes.BLOCK, Blocks.AIR.defaultBlockState());
    }
    public static float eWidth=0.4f;
    public static float eHeight=0.4f;
    @Override
    public EntityDimensions getDimensions(Pose pose) {
        return EntityDimensions.fixed(eWidth, eHeight); // Width, Height
    }
    @Override
    protected float getInertia() {
        return 1F;
    }
    @Override
    public boolean canBeHitByProjectile() {
        return true;
    }
    @Override
    public boolean isPickable() {
        return false;
    }
    @Override
    public boolean isInWater() {
        return false;
    }

    public boolean isEffectivelyInWater() {
        return this.wasTouchingWater;
    }

    @Override
    protected boolean shouldBurn() {
        return false;
    }
    @Override
    protected void onHitBlock(BlockHitResult $$0) {
        popBubble();
    }
    @Override
    protected void onHitEntity(EntityHitResult $$0) {
        popBubble();
    }
    public void popBubble(){
        this.level().playSound(null, this.blockPosition(), ModSounds.BUBBLE_POP_EVENT,
                SoundSource.PLAYERS, 2F, (float)(0.98+(Math.random()*0.04)));
        if (!this.level().isClientSide()){
            ((ServerLevel) this.level()).sendParticles(ModParticles.BUBBLE_POP,
                    this.getX(), this.getY() + this.getBbHeight(), this.getZ(),
                    1, 0, 0,0, 0.015);
        }
        this.discard();
    }
    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ACTIVATED, false);
        this.entityData.define(LAUNCHED, false);
        this.entityData.define(USER_ID, -1);
        this.entityData.define(SPEED, 1F);
    }
    public void shootFromRotationDeltaAgnostic(Entity $$0, float $$1, float $$2, float $$3, float $$4, float $$5) {
        float $$6 = -Mth.sin($$2 * (float) (Math.PI / 180.0)) * Mth.cos($$1 * (float) (Math.PI / 180.0));
        float $$7 = -Mth.sin(($$1 + $$3) * (float) (Math.PI / 180.0));
        float $$8 = Mth.cos($$2 * (float) (Math.PI / 180.0)) * Mth.cos($$1 * (float) (Math.PI / 180.0));
        this.shoot((double)$$6, (double)$$7, (double)$$8, $$4, $$5);
    }
    public void shootFromRotationDeltaAgnostic2(Entity $$0, float $$1, float $$2, float $$3, float $$4) {
        float $$6 = -Mth.sin($$2 * (float) (Math.PI / 180.0)) * Mth.cos($$1 * (float) (Math.PI / 180.0));
        float $$7 = -Mth.sin(($$1 + $$3) * (float) (Math.PI / 180.0));
        float $$8 = Mth.cos($$2 * (float) (Math.PI / 180.0)) * Mth.cos($$1 * (float) (Math.PI / 180.0));
        this.shoot2((double)$$6, (double)$$7, (double)$$8, $$4);
    }
    public void shootFromRotationDeltaAgnostic3(Entity $$0, float $$1, float $$2, float $$3, float $$4) {
        float $$6 = -Mth.sin($$2 * (float) (Math.PI / 180.0)) * Mth.cos($$1 * (float) (Math.PI / 180.0));
        float $$7 = -Mth.sin(($$1 + $$3) * (float) (Math.PI / 180.0));
        float $$8 = Mth.cos($$2 * (float) (Math.PI / 180.0)) * Mth.cos($$1 * (float) (Math.PI / 180.0));
        this.shoot4((double)$$6, (double)$$7, (double)$$8, $$4);
    }
    public void shootFromRotationDeltaAgnosticBackwards(Entity $$0, float $$1, float $$2, float $$3, float $$4) {
        float $$6 = -Mth.sin($$2 * (float) (Math.PI / 180.0)) * Mth.cos($$1 * (float) (Math.PI / 180.0));
        float $$7 = -Mth.sin(($$1 + $$3) * (float) (Math.PI / 180.0));
        float $$8 = Mth.cos($$2 * (float) (Math.PI / 180.0)) * Mth.cos($$1 * (float) (Math.PI / 180.0));
        this.shoot3((double)$$6, (double)$$7, (double)$$8, $$4);
    }

    public void shoot2(double $$0, double $$1, double $$2, float $$3) {
        Vec3 $$5 = (new Vec3($$0, $$1, $$2)).normalize();
        $$5 = $$5.add(this.getDeltaMovement()).normalize();
        $$5 = $$5.add(this.getDeltaMovement()).normalize();
        $$5 = $$5.add(this.getDeltaMovement()).normalize();
        $$5 = $$5.add(this.getDeltaMovement()).normalize();
        $$5 = $$5.scale($$3);
        this.setDeltaMovement($$5);
        double $$6 = $$5.horizontalDistance();
        if (!this.level().isClientSide()) {
            this.setYRot((float) (Mth.atan2($$5.x, $$5.z) * 180.0F / (float) Math.PI));
            this.setXRot((float) (Mth.atan2($$5.y, $$6) * 180.0F / (float) Math.PI));
            this.yRotO = this.getYRot();
            this.xRotO = this.getXRot();
        }
    }
    public void shoot3(double $$0, double $$1, double $$2, float $$3) {
        Vec3 $$5 = (new Vec3($$0, $$1, $$2)).reverse().normalize();
        $$5 = $$5.add(this.getDeltaMovement()).normalize();
        $$5 = $$5.add(this.getDeltaMovement()).normalize();
        $$5 = $$5.add(this.getDeltaMovement()).normalize();
        $$5 = $$5.add(this.getDeltaMovement()).normalize();
        $$5 = $$5.scale($$3);
        this.setDeltaMovement($$5);
        double $$6 = $$5.horizontalDistance();
        if (!this.level().isClientSide()) {
            this.setYRot((float) (Mth.atan2($$5.x, $$5.z) * 180.0F / (float) Math.PI));
            this.setXRot((float) (Mth.atan2($$5.y, $$6) * 180.0F / (float) Math.PI));
            this.yRotO = this.getYRot();
            this.xRotO = this.getXRot();
        }
    }

    public void shoot4(double $$0, double $$1, double $$2, float $$3) {
        Vec3 $$5 = (new Vec3($$0, $$1, $$2)).normalize();
        $$5 = $$5.scale($$3);
        this.setDeltaMovement($$5);
        double $$6 = $$5.horizontalDistance();
        if (!this.level().isClientSide()) {
            this.setYRot((float) (Mth.atan2($$5.x, $$5.z) * 180.0F / (float) Math.PI));
            this.setXRot((float) (Mth.atan2($$5.y, $$6) * 180.0F / (float) Math.PI));
            this.yRotO = this.getYRot();
            this.xRotO = this.getXRot();
        }
    }
}
