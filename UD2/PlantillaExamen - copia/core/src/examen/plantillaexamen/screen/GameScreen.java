package examen.plantillaexamen.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import examen.plantillaexamen.Core;
import examen.plantillaexamen.entity.EnemyEntity;
import examen.plantillaexamen.entity.PlayerEntity;
import examen.plantillaexamen.input.GameInputListener;
import examen.plantillaexamen.input.GameKey;
import examen.plantillaexamen.input.InputManager;
import examen.plantillaexamen.util.Constantes;

import static examen.plantillaexamen.util.Constantes.HEIGHT;

public class GameScreen extends AbstractScreen implements GameInputListener {
    private static final String TAG = GameScreen.class.getSimpleName();
    private static final float TIME = 10f;
    private float currentTime;
    private final SpriteBatch batch;
    private final BitmapFont bitmapFont;
    private final ShapeRenderer shapeRenderer;
    private Texture texture;
    private PlayerEntity player;
    private Array<EnemyEntity> enemyEntities;
    public GameScreen(Core context) {
        super(context);
        context.getInputManager().setListener(this);
        batch = new SpriteBatch();
        bitmapFont = context.getBitmapFont();
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setColor(Color.BLUE);
        shapeRenderer.setAutoShapeType(true);
        texture = context.getAssetManager().get("badlogic.jpg", Texture.class);

        player = new PlayerEntity(new Vector2(Constantes.WIDTH / 2, HEIGHT / 2), new Vector2(), 4, 4, 100);
        enemyEntities = new Array<>();

    }

    @Override
    public void show() {
        currentTime = TIME;
    }

    @Override
    public void hide() {
        //TODO hide juego
    }

    @Override
    public void render(float deltaTime) {
        ScreenUtils.clear(1, 0, 0, 1);

        currentTime -= deltaTime;

        batch.begin();
        shapeRenderer.begin();

        bitmapFont.draw(batch, (int) currentTime + "s", 20, 20);

        shapeRenderer.set(ShapeRenderer.ShapeType.Filled);
        manageEntities(deltaTime);


        batch.end();
        shapeRenderer.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        shapeRenderer.dispose();
    }

    private void manageEntities(float deltaTime){
        player.move(deltaTime);
        player.draw(shapeRenderer);
        for (EnemyEntity enemyEntity : enemyEntities) {
            enemyEntity.move(deltaTime);
            enemyEntity.draw(shapeRenderer);
        }
    }

    @Override
    public void touchDown(InputManager manager, int screenX, int screenY) {
        Gdx.app.debug(TAG, screenX + " " + screenY);
        player.getPosition().x = screenX;
        player.getPosition().y = screenY;
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
