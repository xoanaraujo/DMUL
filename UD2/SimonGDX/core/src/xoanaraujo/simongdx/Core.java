package xoanaraujo.simongdx;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.ReflectionException;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.EnumMap;

import xoanaraujo.simongdx.screens.GameScreen;
import xoanaraujo.simongdx.screens.NumberScreen;
import xoanaraujo.simongdx.screens.ScreenType;
import xoanaraujo.simongdx.util.Const;

public class Core extends Game {
	private static final String TAG = Core.class.getSimpleName();
	private FitViewport viewport;
	private EnumMap<ScreenType, Screen> screens;

	private float time;
	private int points;

	@Override
	public void create () {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);

		viewport = new FitViewport(Const.WIDTH, Const.HEIGHT);
		screens = new EnumMap<>(ScreenType.class);
		time = 1.5f;
		points = 0;
		switchScreen(ScreenType.NUMBER);
	}

	/**
	 * @param screenType Enum that contains a reference to a Screen interface implementation
	 */
	public void switchScreen(final ScreenType screenType) {
		final Screen screen = screens.get(screenType);
		if (screen == null) { // Screen is not created yet
			try {
				Gdx.app.debug(TAG, "Creating a new " + screenType + " screen");
				final Screen newScreen = (Screen) ClassReflection.getConstructor(screenType.getScreenClass(), Core.class).newInstance(this);
				screens.put(screenType, newScreen);
				setScreen(newScreen);
			} catch (ReflectionException e) {
				throw new GdxRuntimeException("Screen " + screenType + " could not be created" + e);
			}
		} else {
			Gdx.app.debug(TAG, "Switching to " + screenType + " screen");
			setScreen(screen);
		}

	}
	public EnumMap<ScreenType, Screen> getScreens() {
		return screens;
	}

	public FitViewport getViewport() {
		return viewport;
	}

	public float getTime() {
		return time;
	}
	public void setTime(float time) {
		this.time = time;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
}
