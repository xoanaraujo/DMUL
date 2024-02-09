package xoanaraujo.slimeit;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import xoanaraujo.slimeit.Core;

import static xoanaraujo.slimeit.util.GameConst.*;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("SlimeIt");
		config.setWindowedMode(WIDTH, HEIGHT);
		new Lwjgl3Application(new Core(), config);
	}
}
