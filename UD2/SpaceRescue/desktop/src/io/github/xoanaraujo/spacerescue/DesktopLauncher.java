package io.github.xoanaraujo.spacerescue;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import io.github.xoanaraujo.spacerescue.MyGdxGame;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {

	public static final int SIZE = 200;

	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("SpaceRescue");

		config.setWindowedMode(SIZE, SIZE);
		config.setResizable(false);
		config.useVsync(false);

		new Lwjgl3Application(new MyGdxGame(), config);
	}
}
