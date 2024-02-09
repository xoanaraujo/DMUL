package xoanaraujo.tuttifruttigdx.screens;

import static xoanaraujo.tuttifruttigdx.util.GameConst.BIT_APPLE;
import static xoanaraujo.tuttifruttigdx.util.GameConst.BIT_WALL;
import static xoanaraujo.tuttifruttigdx.util.GameConst.BODY_DEF;
import static xoanaraujo.tuttifruttigdx.util.GameConst.FIXTURE_DEF;
import static xoanaraujo.tuttifruttigdx.util.GameConst.WORLD_HEIGHT;
import static xoanaraujo.tuttifruttigdx.util.GameConst.WORLD_WIDTH;
import static xoanaraujo.tuttifruttigdx.util.GameConst.resetBodyAndFixtureDef;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;

import xoanaraujo.tuttifruttigdx.Core;
import xoanaraujo.tuttifruttigdx.fruit.Fruit;
import xoanaraujo.tuttifruttigdx.fruit.FruitManager;
import xoanaraujo.tuttifruttigdx.fruit.FruitType;
import xoanaraujo.tuttifruttigdx.input.GameInputListener;
import xoanaraujo.tuttifruttigdx.input.InputManager;

import java.util.Random;

public class GameScreen extends ScreenAbstract implements GameInputListener {
    private static final String TAG = GameScreen.class.getSimpleName();
    private static final Random rd = new Random();
    private final World world;
    private final SpriteBatch batch;
    private final Box2DDebugRenderer debugRenderer;
    private final FruitManager fruitManager;

    public GameScreen(Core context) {
        super(context);
        world = context.getWorld();
        batch = context.getBatch();
        debugRenderer = new Box2DDebugRenderer();
        fruitManager = context.getFruitManager();
        context.getInputManager().addListener(this);
        generateWalls();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.2f, 0.2f, 0.2f, 1);
        debugRenderer.render(world, camera.combined);
    }

    @Override
    public void touchDown(InputManager manager, int screenX, int screenY) {
        Gdx.app.debug(TAG, "" + screenX + " " + screenY);
        fruitManager.addFruit(new Fruit(world, FruitType.APPLE, new Vector2(screenX/2 ,screenY/ 2)));
    }

    private void generateWalls(){
        resetBodyAndFixtureDef();

        // GROUND
        BODY_DEF.position.set(0, - WORLD_HEIGHT / 2);
        Body body = world.createBody(BODY_DEF);
        body.setUserData("WALL");
        FIXTURE_DEF.filter.categoryBits = BIT_WALL;
        FIXTURE_DEF.filter.maskBits = -1;

        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(WORLD_WIDTH, 4);
        FIXTURE_DEF.shape = polygonShape;

        body.createFixture(FIXTURE_DEF);
        polygonShape.dispose();

        // LEFT WALL
        BODY_DEF.position.set(- WORLD_WIDTH / 2, WORLD_HEIGHT / 2);
        body = world.createBody(BODY_DEF);
        body.setUserData("WALL");
        FIXTURE_DEF.filter.categoryBits = BIT_WALL;
        FIXTURE_DEF.filter.maskBits = -1;

        polygonShape = new PolygonShape();
        polygonShape.setAsBox(2, WORLD_HEIGHT);
        FIXTURE_DEF.shape = polygonShape;

        body.createFixture(FIXTURE_DEF);
        polygonShape.dispose();

        // LEFT WALL
        BODY_DEF.position.set( WORLD_WIDTH / 2, WORLD_HEIGHT / 2);
        body = world.createBody(BODY_DEF);
        body.setUserData("WALL");
        FIXTURE_DEF.filter.categoryBits = BIT_WALL;
        FIXTURE_DEF.filter.maskBits = -1;

        polygonShape = new PolygonShape();
        polygonShape.setAsBox(2, WORLD_HEIGHT);
        FIXTURE_DEF.shape = polygonShape;

        body.createFixture(FIXTURE_DEF);
        polygonShape.dispose();

    }
}
