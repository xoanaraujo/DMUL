package xoanaraujo.tuttifruttigdx.fruit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.World;
import xoanaraujo.tuttifruttigdx.Core;

import static xoanaraujo.tuttifruttigdx.util.GameConst.*;
import static xoanaraujo.tuttifruttigdx.util.GameConst.FIXTURE_DEF;

public class Fruit{
    private static final String TAG = Fruit.class.getSimpleName();
    private final World world;
    private Body body;
    private Vector2 position;
    private float radius;

    public Fruit(Core context, FruitType type, Vector2 position) {
        this.world = context.getWorld();
        this.position = position;
        this.radius = type.getRadius();
    }

    public void createBody(){
        resetBodyAndFixtureDef();
        BODY_DEF.gravityScale = 1f;
        BODY_DEF.position.set(position.x , position.y); //(WORLD_WIDTH >> 1), WORLD_HEIGHT / 3
        BODY_DEF.fixedRotation = true;
        BODY_DEF.type = BodyDef.BodyType.DynamicBody;
        Body body = world.createBody(BODY_DEF);
        body.setUserData("BALL");
        FIXTURE_DEF.restitution = 0.2f;
        FIXTURE_DEF.density =1;
        FIXTURE_DEF.filter.categoryBits = BIT_APPLE;
        FIXTURE_DEF.filter.maskBits = -1;

        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(radius);
        FIXTURE_DEF.shape = circleShape;

        body.createFixture(FIXTURE_DEF);
        circleShape.dispose();
        Gdx.app.debug(TAG, "" + world.getBodyCount());
    }
}
