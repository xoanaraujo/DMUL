package xoanaraujo.slimeit.screen;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.ScreenUtils;
import xoanaraujo.slimeit.Core;
import xoanaraujo.slimeit.animations.AnimationSet;
import xoanaraujo.slimeit.input.GameInputListener;
import xoanaraujo.slimeit.input.GameKey;
import xoanaraujo.slimeit.input.InputManager;

public class LoadingScreen extends ScreenAbstract implements GameInputListener {
    private final AssetManager assetManager;
    public LoadingScreen(Core context) {
        super(context);
        context.getInputManager().setGameListener(this);
        assetManager = context.getAssetManager();
        for (AnimationSet animationSet : AnimationSet.values()) {
            assetManager.load(animationSet.getPath(), TextureAtlas.class);
        }
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1, 0, 0, 1);
        if (assetManager.update())
            context.switchScreen(ScreenType.GAME);
    }

    @Override
    public void touchDown(InputManager manager, int screenX, int screenY) {

    }

    @Override
    public void keyDown(InputManager manager, GameKey gameKey) {
        if (gameKey.equals(GameKey.SELECT) && assetManager.isFinished()){
            context.switchScreen(ScreenType.GAME);
        }
    }

    @Override
    public void keyUp(InputManager inputManager, GameKey gameKey) {

    }
}
