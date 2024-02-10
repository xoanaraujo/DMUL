package xoanaraujo.roguelikegdx.entity;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Shape2D;

public class CollisionArea{
    public final Rectangle collision;
    public final boolean isSensor;

    public CollisionArea(Rectangle collision, boolean isSensor) {
        this.collision = collision;
        this.isSensor = isSensor;
    }
}
