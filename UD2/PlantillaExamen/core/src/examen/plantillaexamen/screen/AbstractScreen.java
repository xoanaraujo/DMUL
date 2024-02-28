package examen.plantillaexamen.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.viewport.FitViewport;
import examen.plantillaexamen.Core;

public abstract class AbstractScreen implements Screen {
    protected final Core context;
    protected final FitViewport viewport;
    public AbstractScreen(Core context) {
        this.context = context;
        viewport = context.getViewport();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float deltaTime) {

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
