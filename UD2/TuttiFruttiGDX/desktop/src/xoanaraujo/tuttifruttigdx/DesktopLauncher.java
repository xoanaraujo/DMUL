package xoanaraujo.tuttifruttigdx;

import static xoanaraujo.tuttifruttigdx.util.GameConst.HEIGHT;
import static xoanaraujo.tuttifruttigdx.util.GameConst.WIDTH;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import xoanaraujo.tuttifruttigdx.Core;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("TuttiFruttiGDX");
		config.setWindowedMode(WIDTH, HEIGHT);
		new Lwjgl3Application(new Core(), config);
	}
}
