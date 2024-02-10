package xoanaraujo.roguelikegdx.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import xoanaraujo.roguelikegdx.Core;

public abstract class ScreenAbstract implements Screen {
    protected final Core context;
    protected final FitViewport viewport;
    protected final OrthographicCamera camera;
    protected final AssetManager assetManager;
    public ScreenAbstract(Core context) {
        this.context = context;
        viewport = context.getViewport();
        camera = (OrthographicCamera) viewport.getCamera();
        assetManager = context.getAssetManager();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
