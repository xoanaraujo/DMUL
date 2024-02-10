package xoanaraujo.slimeit.util;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;

public class GameConst {
    public static final int WORLD_WIDTH = 320, WORLD_HEIGHT = 180;
    public static final int MOD = 4;
    public static final int WIDTH = WORLD_WIDTH * MOD, HEIGHT = WORLD_HEIGHT * MOD;
    public static final float FPS = 60;
    public static final int PIXELS_PER_UNIT = 16;
}
