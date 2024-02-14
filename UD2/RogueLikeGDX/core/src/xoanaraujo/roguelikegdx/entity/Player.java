package xoanaraujo.roguelikegdx.entity;

import com.badlogic.gdx.math.Vector2;
import xoanaraujo.roguelikegdx.Core;
import xoanaraujo.roguelikegdx.animations.AnimationSet;
import xoanaraujo.roguelikegdx.entity.collisions.CollisionType;

public class Player extends Entity{

    public static final String TAG = Player.class.getSimpleName();

    public Player(Core context, Vector2 position, Vector2 direction, int width, int height, float velocity, AnimationSet aniSet) {
        super(context, position, direction, width, height, aniSet);
    }

    @Override
    public float getVelocity() {
        return VelocityType.NORMAL.getVelocity();
    }

    @Override
    public CollisionType getCollisionType() {
        return CollisionType.PLAYER;
    }

    @Override
    public void move(float deltaTime) {
        super.move(deltaTime);
        direction.nor();
        position.x += direction.x * velocity * deltaTime;
        position.y += direction.y * velocity * deltaTime;
    }
}
