package xoanaraujo.roguelikegdx;

import com.badlogic.gdx.*;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.ReflectionException;
import com.badlogic.gdx.utils.viewport.FitViewport;
import xoanaraujo.roguelikegdx.input.InputManager;
import xoanaraujo.roguelikegdx.screen.ScreenType;

import java.util.EnumMap;

import static xoanaraujo.roguelikegdx.util.GameConst.*;

public class Core extends Game {
	private static final String TAG = Core.class.getSimpleName();
	private EnumMap<ScreenType, Screen> screens;
	private AssetManager assetManager;
	private InputManager inputManager;
	private FitViewport viewport;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private ShapeRenderer shapeRenderer;

	@Override
	public void create () {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		screens = new EnumMap<>(ScreenType.class);
		assetManager = new AssetManager();
		camera = new OrthographicCamera();
		viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);

		// Input manager
		inputManager = new InputManager(this);
		Gdx.input.setInputProcessor(inputManager);

		// Graphics render related
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setColor(Color.RED);
		shapeRenderer.setAutoShapeType(true);
		switchScreen(ScreenType.LOADING);
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		assetManager.dispose();
		batch.dispose();
		shapeRenderer.dispose();
	}

	public void switchScreen(ScreenType screenType){
		final Screen screen = screens.get(screenType);
		if (screen == null){
			try {
				Gdx.app.debug(TAG, "Creating a [" +screenType + "] instance");
				final Screen newScreen = (Screen) ClassReflection.getConstructor(screenType.getScreenClass(), Core.class).newInstance(this);
				screens.put(screenType, newScreen);
				setScreen(newScreen);
			} catch (ReflectionException e) {
				throw new GdxRuntimeException("[Screen " + screenType + " could not be created]\n" + e);
			}
		} else {
			Gdx.app.debug(TAG, "Switching to the [" + screenType.getClass().getSimpleName() + "] instance");
			setScreen(screen);
			Gdx.app.debug(TAG, "");
		}
	}

	public OrthographicCamera getCamera() {
		return camera;
	}

	public FitViewport getViewport() {
		return viewport;
	}

	public AssetManager getAssetManager() {
		return assetManager;
	}

	public InputManager getInputManager() {
		return inputManager;
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public ShapeRenderer getShapeRenderer() {
		return shapeRenderer;
	}
}
