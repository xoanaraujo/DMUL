package xoanaraujo.slimeit.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import xoanaraujo.slimeit.Core;

public class LoadingScreen extends ScreenAbstract{
    private final AssetManager assetManager;
    public LoadingScreen(Core context) {
        super(context);
        assetManager = context.getAssetManager();
        // assetManager.load("badlogic.jpg", TextureRegion.class);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1, 0, 0, 1);
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            context.switchScreen(ScreenType.GAME);
        }
    }
}
