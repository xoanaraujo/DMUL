package xoanaraujo.tuttifruttigdx.util;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;

public class GameConst {
    public static final int WORLD_WIDTH = 20, WORLD_HEIGHT = 40;
    public static final int WIDTH = WORLD_WIDTH * 20, HEIGHT = WORLD_HEIGHT * 20;
    public static final BodyDef BODY_DEF = new BodyDef();
    public static final FixtureDef FIXTURE_DEF = new FixtureDef();
    public static final float FPS = 60;
    public static final Short BIT_WALL = 1 << 0;
    public static final Short BIT_APPLE = 1 << 1;
    public static final Short BIT_ORANGE = 1 << 2;
    public static void resetBodyAndFixtureDef(){

        BODY_DEF.position.set(0, 0);
        BODY_DEF.gravityScale = 0f;
        BODY_DEF.type = BodyDef.BodyType.StaticBody;
        BODY_DEF.fixedRotation = true;
        BODY_DEF.allowSleep = true;

        FIXTURE_DEF.restitution = 0;
        FIXTURE_DEF.density = 0;
        FIXTURE_DEF.friction = 0.2f;
        FIXTURE_DEF.filter.categoryBits = BIT_WALL;
        FIXTURE_DEF.filter.maskBits = -1;
    }
}
