package xoanaraujo.tuttifruttigdx;

import static xoanaraujo.tuttifruttigdx.util.GameConst.FPS;
import static xoanaraujo.tuttifruttigdx.util.GameConst.HEIGHT;
import static xoanaraujo.tuttifruttigdx.util.GameConst.WIDTH;
import static xoanaraujo.tuttifruttigdx.util.GameConst.WORLD_HEIGHT;
import static xoanaraujo.tuttifruttigdx.util.GameConst.WORLD_WIDTH;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.ReflectionException;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.EnumMap;

import xoanaraujo.tuttifruttigdx.fruit.FruitManager;
import xoanaraujo.tuttifruttigdx.input.InputManager;
import xoanaraujo.tuttifruttigdx.screens.GameScreen;
import xoanaraujo.tuttifruttigdx.screens.ScreenAbstract;
import xoanaraujo.tuttifruttigdx.screens.ScreenType;

public class Core extends Game {
	private static final String TAG = Core.class.getSimpleName();
	private EnumMap<ScreenType, Screen> screens;
	private AssetManager assetManager;
	private InputManager inputManager;
	private World world;
	private FruitManager fruitManager;
	private WorldContactAdapter worldContactAdapter;
	private OrthographicCamera camera;
	private FitViewport fitViewport;
	private SpriteBatch batch;
	private Box2DDebugRenderer debugRenderer;
	private float updateTime;

	@Override
	public void create () {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);

		screens = new EnumMap<>(ScreenType.class);
		camera = new OrthographicCamera();
		fitViewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);

		assetManager = new AssetManager();

		inputManager = new InputManager(this);
		Gdx.input.setInputProcessor(inputManager);

		Box2D.init();
		world = new World(new Vector2(0, -10), true);
		fruitManager = new FruitManager(this);
		worldContactAdapter = new WorldContactAdapter(this);
		world.setContactListener(worldContactAdapter);

		batch = new SpriteBatch();
		debugRenderer = new Box2DDebugRenderer();
		updateTime = 0f;

		switchScreen(ScreenType.LOADING);
	}

	@Override
	public void render() {
		super.render();
		fitViewport.apply();
		final float deltaTime = Gdx.graphics.getDeltaTime();

		updateTime += deltaTime;
		while (updateTime >= 1 / FPS){
			updateTime -= 1 / FPS;
			world.step(1 / FPS, 6, 2);
		}
		fruitManager.destroyFruits();
		fruitManager.createFruits();

	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		fitViewport.update(width, height);
	}

	@Override
	public void dispose () {
		assetManager.dispose();
	}

	public AssetManager getAssetManager() {
		return assetManager;
	}

	public void switchScreen(ScreenType screenType){
		final Screen screen = screens.get(screenType);
		if (screen == null){
			try {
				Gdx.app.debug(TAG, "Creating a [" +screenType + "] instance");
				final Screen newScreen = (Screen) ClassReflection.getConstructor(screenType.getScreen(), Core.class).newInstance(this);
				screens.put(screenType, newScreen);
				setScreen(newScreen);
			} catch (ReflectionException e) {
				throw new GdxRuntimeException("[Screen " + screenType + " could not be created]\n" + e);
			}
		} else {
			Gdx.app.debug(TAG, "Switching to the [" + screenType.getClass().getSimpleName() + "] instance");
			setScreen(screen);
		}
	}

	public World getWorld() {
		return world;
	}

	public InputManager getInputManager() {
		return inputManager;
	}

	public FitViewport getFitViewport() {
		return fitViewport;
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public Box2DDebugRenderer getDebugRenderer() {
		return debugRenderer;
	}

	public FruitManager getFruitManager() {
		return fruitManager;
	}
}
