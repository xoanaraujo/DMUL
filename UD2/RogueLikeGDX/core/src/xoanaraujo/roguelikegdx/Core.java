package xoanaraujo.roguelikegdx;

import com.badlogic.gdx.*;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.I18NBundle;
import com.badlogic.gdx.utils.ObjectMap;
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
	private Stage stage;
	private Skin skin;
	private I18NBundle i18NBundle;

	@Override
	public void create () {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		screens = new EnumMap<>(ScreenType.class);

		assetManager = new AssetManager();

		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setColor(Color.RED);
		shapeRenderer.setAutoShapeType(true);


		camera = new OrthographicCamera();
		viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
		initializeSkin();
		stage = new Stage(new FitViewport(WIDTH, HEIGHT), batch);

		// Input manager
		inputManager = new InputManager(this);
		Gdx.input.setInputProcessor(inputManager);

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

	private void initializeSkin() {
		// Setup colors to be used in our font
		Colors.put("debug", new Color(1f, 0f, 0f, 3));

		// Generate ttf bitmaps
		final ObjectMap<String, Object> resources = new ObjectMap<>();

		final FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("ui/font/default.ttf"));
		final FreeTypeFontGenerator.FreeTypeFontParameter fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		fontParameter.minFilter = Texture.TextureFilter.Linear;
		fontParameter.magFilter = Texture.TextureFilter.Linear;
		final int[] sizesToCreate = {16, 20, 26, 32};
		for (int size : sizesToCreate) {
			fontParameter.size = size;
			BitmapFont bitmapFont = fontGenerator.generateFont(fontParameter);
			bitmapFont.getData().markupEnabled = true;

			resources.put("font_" + size, bitmapFont);
			fontGenerator.generateFont(fontParameter);
		}
		fontGenerator.dispose();

		// load skin
		final SkinLoader.SkinParameter skinParameter = new SkinLoader.SkinParameter("ui/hud/hud.atlas", resources);
		assetManager.load("ui/hud/hud.json", Skin.class, skinParameter);
		assetManager.load("ui/strings", I18NBundle.class);
		assetManager.finishLoading();
		skin = assetManager.get("ui/hud/hud.json", Skin.class);
		i18NBundle = assetManager.get("ui/strings", I18NBundle.class);

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

	public Stage getStage() {
		return stage;
	}

	public Skin getSkin() {
		return skin;
	}

	public I18NBundle getI18NBundle() {
		return i18NBundle;
	}
}
