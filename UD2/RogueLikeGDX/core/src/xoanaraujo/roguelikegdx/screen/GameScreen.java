package xoanaraujo.roguelikegdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import xoanaraujo.roguelikegdx.Core;
import xoanaraujo.roguelikegdx.animations.AnimationSet;
import xoanaraujo.roguelikegdx.entity.Entity;
import xoanaraujo.roguelikegdx.entity.Player;
import xoanaraujo.roguelikegdx.entity.enemy.ChaserEntity;
import xoanaraujo.roguelikegdx.entity.enemy.WandererEntity;
import xoanaraujo.roguelikegdx.input.GameInputListener;
import xoanaraujo.roguelikegdx.input.GameKey;
import xoanaraujo.roguelikegdx.input.InputManager;

import java.util.Random;

import static xoanaraujo.roguelikegdx.util.GameConst.*;

public class GameScreen extends ScreenAbstract implements GameInputListener {
    private static final String TAG = GameScreen.class.getSimpleName();
    private static final Random rd = new Random();
    private final SpriteBatch batch;
    private final ShapeRenderer shapeRenderer;
    private final Player player;
    private Array<Entity> entities;

    public GameScreen(Core context) {
        super(context);
        batch = context.getBatch();
        shapeRenderer = context.getShapeRenderer();
        context.getInputManager().setGameListener(this);
        entities = new Array<>();

        player = new Player(context, new Vector2((WIDTH - PIXELS_PER_UNIT * MOD) >> 1, (HEIGHT - PIXELS_PER_UNIT * MOD) >> 1), new Vector2(0, 0), PIXELS_PER_UNIT * MOD, PIXELS_PER_UNIT * MOD, 150f, AnimationSet.GREEN_NINJA);
        entities.add(new ChaserEntity(context, new Vector2(0, HEIGHT - PIXELS_PER_UNIT * MOD), new Vector2(0, 0), PIXELS_PER_UNIT * MOD, PIXELS_PER_UNIT * MOD, AnimationSet.BLUE_NINJA, player));
        entities.add(new ChaserEntity(context, new Vector2(0, 0), new Vector2(0, 0), PIXELS_PER_UNIT * MOD, PIXELS_PER_UNIT * MOD, AnimationSet.BLUE_NINJA, player));
        entities.add(new ChaserEntity(context, new Vector2(WIDTH - PIXELS_PER_UNIT * MOD, 0), new Vector2(0, 0), PIXELS_PER_UNIT * MOD, PIXELS_PER_UNIT * MOD, AnimationSet.BLUE_NINJA, player));
        entities.add(new ChaserEntity(context, new Vector2(WIDTH - PIXELS_PER_UNIT * MOD, HEIGHT -PIXELS_PER_UNIT * MOD), new Vector2(0, 0), PIXELS_PER_UNIT * MOD, PIXELS_PER_UNIT * MOD, AnimationSet.BLUE_NINJA, player));
        entities.add(new WandererEntity(context, new Vector2(800, 150), new Vector2(0, 0), PIXELS_PER_UNIT * MOD, PIXELS_PER_UNIT * MOD, AnimationSet.GRAY_NINJA));
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.5f, 0.8f, 0.8f, 1);
        batch.begin();
        // shapeRenderer.begin();
        // shapeRenderer.set(ShapeRenderer.ShapeType.Line);

        // calculations
        player.move(delta);
        moveEntities(delta);
        checkCollisions(delta);

        // Draw result
        player.draw(batch, shapeRenderer);
        drawEntities(delta);

        batch.end();
        shapeRenderer.end();
    }

    private void checkCollisions(float delta) {
        for (Entity entity : entities) {
            if (player.getCollisionArea().collision.overlaps(entity.getCollisionArea().collision)){
                Gdx.app.debug(TAG, "Collision");
            }
        }
    }

    private void moveEntities(float deltaTime){
        for (Entity entity : entities) {
            entity.move(deltaTime);
        }
    }

    private void drawEntities(float deltaTime){
        for (Entity entity : entities) {
            entity.draw(batch, shapeRenderer);
        }
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
