package xoanaraujo.tuttifruttigdx.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;

import xoanaraujo.tuttifruttigdx.Core;

public abstract class ScreenAbstract implements Screen {
    protected final Core context;
    protected final FitViewport viewport;
    protected final OrthographicCamera camera;
    public ScreenAbstract(Core context) {
        this.context = context;
        viewport = context.getFitViewport();
        camera = (OrthographicCamera) viewport.getCamera();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {
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
