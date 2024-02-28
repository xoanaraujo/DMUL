package examen.plantillaexamen.screen;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import examen.plantillaexamen.Core;

public class LoadingScreen extends AbstractScreen{

    private final AssetManager assetManager;

    public LoadingScreen(Core context) {
        super(context);
        assetManager = context.getAssetManager();
        assetManager.load("badlogic.jpg", Texture.class);
        //TODO AQUI CARGAR TODOS LOS ASSETS DEL JUEGO
    }

    @Override
    public void render(float deltaTime) {
        super.render(deltaTime);

        if (assetManager.update()){
            context.switchScreen(ScreenType.GAME);
        }
    }
}
