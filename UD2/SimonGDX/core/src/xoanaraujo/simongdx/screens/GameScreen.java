package xoanaraujo.simongdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Objects;

import xoanaraujo.simongdx.Core;

public class GameScreen extends ScreenAdapter implements InputProcessor {
    private static final String TAG = GameScreen.class.getSimpleName();
    private static final Color background = new Color(0.5f, 0.5f,0.5f, 1f);
    private NumberListener listener;
    private static final float TIME = 1f;
    private float cnt;
    private int number, inputNumber;
    public GameScreen(Core context) {
        super(context);
        Gdx.input.setInputProcessor((InputProcessor) this);
        listener = (NumberListener) context.getScreens().get(ScreenType.NUMBER);
    }

    @Override
    public void show() {
        number = listener.getNumberListener();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(background);
        cnt += delta;
        if (cnt > TIME){
            // if (inputNumber == 0)
            //     Gdx.app.exit();
            cnt = 0;
            context.switchScreen(ScreenType.NUMBER);
        }
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

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        Gdx.app.debug(TAG, String.valueOf(character == (char) number));
        return true;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
