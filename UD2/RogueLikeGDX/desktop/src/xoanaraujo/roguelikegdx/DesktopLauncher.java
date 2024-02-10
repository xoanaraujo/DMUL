package xoanaraujo.roguelikegdx;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

import static xoanaraujo.roguelikegdx.util.GameConst.*;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setWindowedMode(WIDTH, HEIGHT);
		config.setForegroundFPS(60);
		config.setTitle("SlimeIt");
		new Lwjgl3Application(new Core(), config);
	}
}
