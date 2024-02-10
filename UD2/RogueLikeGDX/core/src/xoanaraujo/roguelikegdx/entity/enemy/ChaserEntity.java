package xoanaraujo.roguelikegdx.entity.enemy;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import xoanaraujo.roguelikegdx.Core;
import xoanaraujo.roguelikegdx.animations.AnimationSet;
import xoanaraujo.roguelikegdx.entity.Entity;
import xoanaraujo.roguelikegdx.entity.VelocityType;
import xoanaraujo.roguelikegdx.entity.collisions.CollisionType;

public class ChaserEntity extends Entity {
    private final Entity chasedEntity;
    public ChaserEntity(Core context, Vector2 position, Vector2 direction, int width, int height, AnimationSet aniSet, Entity chasedEntity) {
        super(context, position, direction, width, height, aniSet);
        this.chasedEntity = chasedEntity;
    }

    @Override
    public float getVelocity() {
        return VelocityType.SLOW.getVelocity();
    }

    @Override
    public CollisionType getCollisionType() {
        return CollisionType.CHASER;
    }

    @Override
    public void move(float deltaTime) {
        direction.set(chasedEntity.getPosition().x - position.x, chasedEntity.getPosition().y - position.y).nor();
        position.x += direction.x * velocity * deltaTime;
        position.y += direction.y * velocity * deltaTime;
        super.move(deltaTime);
    }

    @Override
    public void draw(SpriteBatch batch, ShapeRenderer shapeRenderer) {
        super.draw(batch, shapeRenderer);
    }
}
