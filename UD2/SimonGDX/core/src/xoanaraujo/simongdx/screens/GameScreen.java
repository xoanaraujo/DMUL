package xoanaraujo.simongdx.screens;

import static xoanaraujo.simongdx.util.Const.HEIGHT;
import static xoanaraujo.simongdx.util.Const.MIN_TIME;
import static xoanaraujo.simongdx.util.Const.SUB_TIME;
import static xoanaraujo.simongdx.util.Const.WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Objects;

import xoanaraujo.simongdx.Core;
import xoanaraujo.simongdx.util.Const;

public class GameScreen extends ScreenAdapter implements InputProcessor {
    private static final String TAG = GameScreen.class.getSimpleName();
    private static final Color background = new Color(0.5f, 0.5f,0.5f, 1f);
    private NumberListener listener;
    private float time = 1f;
    private final BitmapFont font;
    private String text;
    private float cnt;
    private int number, width, height, points;
    private boolean success, guessed;
    public GameScreen(Core context) {
        super(context);
        Gdx.input.setInputProcessor((InputProcessor) this);
        listener = (NumberListener) context.getScreens().get(ScreenType.NUMBER);
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(4f);
    }

    @Override
    public void show() {
        number = listener.getNumberListener();
        time = context.getTime();
        points = context.getPoints();
        success = false;
        guessed = false;
        setResultText("???");

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(background);
        cnt += delta;
        if (cnt > time){
            if (!success)
                Gdx.app.exit();
            if (time > MIN_TIME)
                context.setTime(time - SUB_TIME);
            cnt = 0;
            context.switchScreen(ScreenType.NUMBER);
        }
        batch.begin();
        if (guessed){
            font.draw(batch, text, (WIDTH >> 1) - (width >> 1), (HEIGHT >> 1) + (height >> 1));
        }
        font.draw(batch, String.valueOf(points), WIDTH - 50, HEIGHT - 50);
        batch.end();

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
        Gdx.app.debug(TAG, String.valueOf(guessed));
        if (!guessed){
            if (String.valueOf(character).equals(String.valueOf(number))){
                success = true;
                context.setPoints(++points);
                setResultText("Success!");
            } else
                setResultText("Failed :(");
        }
        return true;
    }

    private void setResultText(String text) {
        guessed = true;
        this.text = text;
        if (font != null){
            GlyphLayout glyphLayout = new GlyphLayout();
            glyphLayout.setText(font, text);
            width = (int) glyphLayout.width;
            height = (int) glyphLayout.height;
        }
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
