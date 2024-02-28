package examen.plantillaexamen;

import com.badlogic.gdx.*;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.ReflectionException;
import com.badlogic.gdx.utils.viewport.FitViewport;
import examen.plantillaexamen.input.InputManager;
import examen.plantillaexamen.screen.AbstractScreen;
import examen.plantillaexamen.screen.ScreenType;
import examen.plantillaexamen.util.Constantes;

import java.util.EnumMap;

public class Core extends Game {

	public static final String TAG = Core.class.getSimpleName();
	private EnumMap<ScreenType, Screen> screenCache;
	//========== MANAGERS ==========
	private AssetManager assetManager;
	private InputManager inputManager;
	//==============================
	private OrthographicCamera camera;
	private FitViewport viewport;
	private SpriteBatch batch;
	private BitmapFont bitmapFont;

	@Override
	public void create () {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);

		screenCache = new EnumMap<>(ScreenType.class);

		assetManager = new AssetManager();
		inputManager = new InputManager();
		Gdx.input.setInputProcessor(inputManager);

		camera = new OrthographicCamera();
		viewport = new FitViewport(Constantes.WIDTH, Constantes.HEIGHT, camera);

		batch = new SpriteBatch();
		bitmapFont = new BitmapFont();

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
		bitmapFont.dispose();
	}

	public void switchScreen(ScreenType screenType){
		final Screen screen = screenCache.get(screenType);
		if (screen == null){
			try {
				Gdx.app.debug(TAG, "Creating a [" +screenType + "] instance");
				final Screen newScreen = (Screen) ClassReflection.getConstructor(screenType.getScreenClass(), Core.class).newInstance(this);
				screenCache.put(screenType, newScreen);
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

	public EnumMap<ScreenType, Screen> getScreenCache() {
		return screenCache;
	}

	public AssetManager getAssetManager() {
		return assetManager;
	}

	public InputManager getInputManager() {
		return inputManager;
	}

	public FitViewport getViewport() {
		return viewport;
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public BitmapFont getBitmapFont() {
		return bitmapFont;
	}
}
