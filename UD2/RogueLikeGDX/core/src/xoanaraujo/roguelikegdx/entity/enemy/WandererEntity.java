package xoanaraujo.roguelikegdx.entity.enemy;

import com.badlogic.gdx.math.Vector2;
import xoanaraujo.roguelikegdx.Core;
import xoanaraujo.roguelikegdx.animations.AnimationSet;
import xoanaraujo.roguelikegdx.entity.Entity;
import xoanaraujo.roguelikegdx.entity.VelocityType;
import xoanaraujo.roguelikegdx.entity.collisions.CollisionType;
import xoanaraujo.roguelikegdx.util.GameConst;
import xoanaraujo.roguelikegdx.util.Utils;

public class WandererEntity extends Entity {
    private final float directionChangeTime;
    public WandererEntity(Core context, Vector2 position, Vector2 direction, int width, int height, AnimationSet aniSet) {
        super(context, position, direction, width, height, aniSet);
        directionChangeTime = 5f;
        direction.x = 1;
    }

    @Override
    public float getVelocity() {
        return VelocityType.FASTEST.getVelocity();
    }

    @Override
    public CollisionType getCollisionType() {
        return CollisionType.WANDERER;
    }

    @Override
    public void move(float deltaTime) {
        if (Utils.rd.nextInt(1000) > 990){
            updateTime = 0;
            direction.x = Utils.rd.nextFloat(-1, 1);
            direction.y = Utils.rd.nextFloat(-1, 1);
            direction.nor();
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
}
