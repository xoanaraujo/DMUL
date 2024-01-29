package xoanaraujo.simongdx.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;

import xoanaraujo.simongdx.Core;

public abstract class ScreenAdapter implements Screen {
    protected final Core context;
    protected final FitViewport viewport;
    protected final SpriteBatch batch;

    public ScreenAdapter(Core context) {
        this.context = context;
        viewport = context.getViewport();
        batch = new SpriteBatch();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }
}
