package xoanaraujo.tuttifruttigdx.screens;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.utils.ScreenUtils;

import xoanaraujo.tuttifruttigdx.Core;

public class LoadingScreen extends ScreenAbstract{
    private final AssetManager assetManager;
    public LoadingScreen(Core context) {
        super(context);
        assetManager = context.getAssetManager();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1, 0, 0, 1);
        if (assetManager.update())
            context.switchScreen(ScreenType.GAME);

    }
}
