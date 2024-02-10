package xoanaraujo.roguelikegdx.entity.collisions;

import com.badlogic.gdx.math.Rectangle;
import xoanaraujo.roguelikegdx.entity.CollisionArea;
import xoanaraujo.roguelikegdx.util.GameConst;

import static xoanaraujo.roguelikegdx.util.GameConst.*;

public enum CollisionType {
    PLAYER (new CollisionArea(new Rectangle(0, 0,MOD * PIXELS_PER_UNIT * 0.6f, MOD * PIXELS_PER_UNIT * 0.6f), false)),
    CHASER (new CollisionArea(new Rectangle(0, 0,MOD * PIXELS_PER_UNIT * 0.6f, MOD * PIXELS_PER_UNIT * 0.6f), false)),
    WANDERER (new CollisionArea(new Rectangle(0, 0,MOD * PIXELS_PER_UNIT * 0.6f, MOD * PIXELS_PER_UNIT * 0.6f), false));
    private final CollisionArea collisionArea;

    CollisionType(CollisionArea collisionArea) {
        this.collisionArea = collisionArea;
    }

    public CollisionArea getCollisionArea() {
        return collisionArea;
    }
}
