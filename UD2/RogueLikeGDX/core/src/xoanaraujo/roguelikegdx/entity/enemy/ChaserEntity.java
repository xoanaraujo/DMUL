package xoanaraujo.roguelikegdx.entity.enemy;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import xoanaraujo.roguelikegdx.Core;
import xoanaraujo.roguelikegdx.animations.AnimationSet;
import xoanaraujo.roguelikegdx.entity.Entity;
import xoanaraujo.roguelikegdx.entity.VelocityType;
import xoanaraujo.roguelikegdx.entity.collisions.CollisionType;
import xoanaraujo.roguelikegdx.util.GameConst;
import xoanaraujo.roguelikegdx.util.Utils;

public class ChaserEntity extends Entity {
    private final Entity chasedEntity;
    private Float resetChaseTime;
    public ChaserEntity(Core context, Vector2 position, Vector2 direction, int width, int height, AnimationSet aniSet, Entity chasedEntity) {
        super(context, position, direction, width, height, aniSet);
        this.chasedEntity = chasedEntity;
        resetChaseTime = 0f;
    }

    @Override
    public float getVelocity() {
        return VelocityType.NORMAL.getVelocity();
    }

    @Override
    public CollisionType getCollisionType() {
        return CollisionType.ENEMY;
    }

    @Override
    public void move(float deltaTime) {
        if (updateTime > resetChaseTime){
            resetChaseTime = Utils.rd.nextFloat(0.8f, 1.4f);
            updateTime = 0;
            direction.set((chasedEntity.getPosition().x - position.x) * Utils.rd.nextFloat(1f, 5), (chasedEntity.getPosition().y - position.y)* Utils.rd.nextFloat(0.5f, 1.5f)).nor();
        }
        position.x += direction.x * velocity * deltaTime;
        position.y += direction.y * velocity * deltaTime;

        if (position.x + width > GameConst.WIDTH || position.x < 0){
            direction.x *= -1f;
        }
        if (position.y + height > GameConst.HEIGHT || position.y < 0){
            direction.y *= -1f;
        }
        super.move(deltaTime);
    }

    @Override
    public void draw(SpriteBatch batch, ShapeRenderer shapeRenderer) {
        super.draw(batch, shapeRenderer);
    }
}
