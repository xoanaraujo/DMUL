package xoanaraujo.slimeit;

import com.badlogic.gdx.*;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.ReflectionException;
import com.badlogic.gdx.utils.viewport.FitViewport;
import xoanaraujo.slimeit.input.InputManager;
import xoanaraujo.slimeit.screen.ScreenAbstract;
import xoanaraujo.slimeit.screen.ScreenType;

import java.util.EnumMap;

import static xoanaraujo.slimeit.util.GameConst.*;

public class Core extends Game {
	private static final String TAG = Core.class.getSimpleName();
	private EnumMap<ScreenType, Screen> screens;
	private AssetManager assetManager;
	private InputManager inputManager;
	private FitViewport viewport;
	private OrthographicCamera camera;
	private SpriteBatch batch;

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

		// GameScreen related
		batch = new SpriteBatch();

		switchScreen(ScreenType.LOADING);
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
	}
	
	@Override
	public void dispose () {
		assetManager.dispose();
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
}
