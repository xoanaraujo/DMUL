package xoanaraujo.slimeit.screen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import xoanaraujo.slimeit.Core;
import xoanaraujo.slimeit.animations.AnimationSet;
import xoanaraujo.slimeit.entity.Player;
import xoanaraujo.slimeit.input.GameInputListener;
import xoanaraujo.slimeit.input.GameKey;
import xoanaraujo.slimeit.input.InputManager;

import java.util.Random;

import static xoanaraujo.slimeit.util.GameConst.*;

public class GameScreen extends ScreenAbstract implements GameInputListener {
    private static final String TAG = GameScreen.class.getSimpleName();
    private static final Random rd = new Random();
    private final SpriteBatch batch;
    private final ShapeRenderer shapeRenderer;

    private final Player player;

    public GameScreen(Core context) {
        super(context);
        batch = context.getBatch();
        shapeRenderer = context.getShapeRenderer();
        context.getInputManager().setGameListener(this);
        player = new Player(context, new Vector2(WIDTH / 2, HEIGHT / 2), new Vector2(0, 0), PIXELS_PER_UNIT * MOD, PIXELS_PER_UNIT * MOD, 150f, AnimationSet.BLUE_NINJA);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.5f, 0.8f, 0.8f, 1);
        batch.begin();
        shapeRenderer.begin();
        shapeRenderer.set(ShapeRenderer.ShapeType.Line);

        player.render(delta);
        player.draw(batch, shapeRenderer);

        batch.end();
        shapeRenderer.end();
    }

    @Override
    public void touchDown(InputManager manager, int screenX, int screenY) {
    }

    @Override
    public void keyDown(InputManager manager, GameKey gameKey) {
        switch (gameKey) {
            case UP: {
                player.getDirection().y = 1;
                player.setDirectionChanged(true);
            }
            break;
            case DOWN: {
                player.getDirection().y = -1;
                player.setDirectionChanged(true);
            }
            break;
            case RIGHT: {
                player.getDirection().x = 1;
                player.setDirectionChanged(true);
            }
            break;
            case LEFT: {
                player.getDirection().x = -1;
                player.setDirectionChanged(true);
            }
            break;
        }
    }

    @Override
    public void keyUp(InputManager inputManager, GameKey gameKey) {
        switch (gameKey) {
            case UP: {
                player.getDirection().y = inputManager.isKeyPressed(GameKey.DOWN) ? -1 : 0;
                player.setDirectionChanged(true);
            }
            break;
            case DOWN: {
                player.getDirection().y = inputManager.isKeyPressed(GameKey.UP) ? 1 : 0;
                player.setDirectionChanged(true);
            }
            break;
            case RIGHT: {
                player.getDirection().x = inputManager.isKeyPressed(GameKey.LEFT) ? -1 : 0;
                player.setDirectionChanged(true);
            }
            break;
            case LEFT: {
                player.getDirection().x = inputManager.isKeyPressed(GameKey.RIGHT) ? 1 : 0;
                player.setDirectionChanged(true);
            }
            break;
        }
    }
}
