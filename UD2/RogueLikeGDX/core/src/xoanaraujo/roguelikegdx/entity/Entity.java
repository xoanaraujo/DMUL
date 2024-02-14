package xoanaraujo.roguelikegdx.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import xoanaraujo.roguelikegdx.Core;
import xoanaraujo.roguelikegdx.animations.AnimationSet;
import xoanaraujo.roguelikegdx.animations.AnimationType;
import xoanaraujo.roguelikegdx.entity.collisions.CollisionType;

import java.util.EnumMap;

public abstract class Entity {
    public static final String TAG = Entity.class.getSimpleName();
    protected Vector2 position, direction;
    protected int width, height;
    private final CollisionType collisionType;
    private final CollisionArea collisionArea;
    protected float velocity, updateTime;
    protected EnumMap<AnimationType, Animation<Sprite>> animationCache;
    protected Animation<Sprite> currentAnimation;
    protected boolean directionChanged;

    public Entity(Core context, Vector2 position, Vector2 direction, int width, int height, AnimationSet aniSet) {
        this.position = position;
        this.direction = direction;
        this.width = width;
        this.height = height;
        updateTime = 0;
        directionChanged = false;
        animationCache = new EnumMap<>(AnimationType.class);
        collisionType = getCollisionType();
        collisionArea = collisionType.getCollisionArea();
        velocity = getVelocity();
        final TextureAtlas.AtlasRegion region = context.getAssetManager().get(aniSet.getPath(), TextureAtlas.class).findRegion(aniSet.getAtlasKey());
        final TextureRegion[][] textureRegions = region.split(16, 16);
        for (AnimationType animationType : aniSet.getAnimationTypes()) {
            animationCache.put(
                    animationType,
                    new Animation<>(
                            animationType.getTimePerFrame(),
                            getKeyFrames(textureRegions, animationType.getRows(), animationType.getColumns()),
                            animationType.getPlayMode()
                    )
            );
        }
        currentAnimation = animationCache.get(AnimationType.IDLE_DOWN);
    }

    public void move(float deltaTime){
        updateTime += deltaTime;
        if (direction.equals(Vector2.Zero) ){ // && !currentAnimation.equals(animationCache.get(AnimationType.IDLE_DOWN))
            currentAnimation = animationCache.get(AnimationType.IDLE_DOWN);
            directionChanged = false;
        } else if (direction.x > 0 ){ //&& !currentAnimation.equals(animationCache.get(AnimationType.WALK_RIGHT))
            currentAnimation = animationCache.get(AnimationType.WALK_RIGHT);
            directionChanged = false;
        } else if (direction.x < 0 ){ // && !currentAnimation.equals(animationCache.get(AnimationType.WALK_LEFT))
            currentAnimation = animationCache.get(AnimationType.WALK_LEFT);
            directionChanged = false;
        } else if (direction.y > 0 ){ // && !currentAnimation.equals(animationCache.get(AnimationType.WALK_UP))
            currentAnimation = animationCache.get(AnimationType.WALK_UP);
            directionChanged = false;
        } else if(direction.y <= 0 ){ // && !currentAnimation.equals(animationCache.get(AnimationType.WALK_DOWN))
            currentAnimation = animationCache.get(AnimationType.WALK_DOWN);
            directionChanged = false;
        }
    }

    public void draw(SpriteBatch batch, ShapeRenderer shapeRenderer){
        final Sprite keyFrame = currentAnimation.getKeyFrame(updateTime);
        keyFrame.setBounds(position.x, position.y, width, height);
        collisionArea.collision.setPosition(position.x + 12f, position.y + 4);
        keyFrame.draw(batch);
        // shapeRenderer.rect(collisionArea.collision.x, collisionArea.collision.y, collisionArea.collision.width, collisionArea.collision.height);
    }

    private Array<Sprite> getKeyFrames(TextureRegion[][] textureRegions, int[] rows, int[] cols) {
        Array<Sprite> keyFrames = new Array<>();
        for (int i = 0; i < rows.length; i++) {
            final Sprite sprite = new Sprite(textureRegions[rows[i]][cols[i]]);
            sprite.setOriginCenter();
            keyFrames.add(sprite);
        }
        return keyFrames;
    }


    public abstract float getVelocity();
    public abstract CollisionType getCollisionType();

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getDirection() {
        return direction;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public CollisionArea getCollisionArea() {
        return collisionArea;
    }

    public float getUpdateTime() {
        return updateTime;
    }

    public EnumMap<AnimationType, Animation<Sprite>> getAnimationCache() {
        return animationCache;
    }

    public Animation<Sprite> getCurrentAnimation() {
        return currentAnimation;
    }

    public boolean isDirectionChanged() {
        return directionChanged;
    }

    public void setDirectionChanged(boolean directionChanged) {
        this.directionChanged = directionChanged;
    }

}
