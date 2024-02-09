package xoanaraujo.slimeit.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import xoanaraujo.slimeit.Core;
import xoanaraujo.slimeit.input.GameInputListener;
import xoanaraujo.slimeit.input.InputManager;

import java.util.Random;

public class GameScreen extends ScreenAbstract implements GameInputListener {
    private static final String TAG = GameScreen.class.getSimpleName();
    private static final Random rd = new Random();
    private final SpriteBatch batch;
    private final Box2DDebugRenderer debugRenderer;

    public GameScreen(Core context) {
        super(context);
        debugRenderer = new Box2DDebugRenderer();
        batch = context.getBatch();
        context.getInputManager().addListener(this);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.5f, 0.8f, 0.8f, 1);
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            context.switchScreen(ScreenType.LOADING);
        }
    }

    @Override
    public void touchDown(InputManager manager, int screenX, int screenY) {
        Gdx.app.debug(TAG, "" + screenX + " " + screenY);
    }
}
