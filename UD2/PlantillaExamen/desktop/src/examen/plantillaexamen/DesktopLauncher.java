package examen.plantillaexamen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import examen.plantillaexamen.Core;
import examen.plantillaexamen.util.Constantes;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("PlantillaExamen");
		config.setWindowedMode(Constantes.WIDTH, Constantes.HEIGHT);
		new Lwjgl3Application(new Core(), config);
	}
}
