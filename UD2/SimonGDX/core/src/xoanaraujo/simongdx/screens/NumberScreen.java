package xoanaraujo.simongdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Random;

import xoanaraujo.simongdx.Core;
import xoanaraujo.simongdx.util.Const;

public class NumberScreen extends ScreenAdapter implements NumberListener{
    private static final String TAG = GameScreen.class.getSimpleName();
    private static final Random rd = new Random();
    private static final Color background = new Color(0.14f, 0.14f,0.14f, 1f);
    private static final float TIME = 1f;
    private final BitmapFont font;
    private float cnt;
    private int number, width, height;

    public NumberScreen(Core context) {
        super(context);
        cnt = 0;
        font = new BitmapFont();
        font.setColor(new Color(0.75f,0.75f,0.75f,1f));
        font.getData().setScale(10f);
    }

    @Override
    public void show() {
        cnt = 0;
        number = rd.nextInt(9);
        GlyphLayout glyphLayout = new GlyphLayout();
        glyphLayout.setText(font, String.valueOf(number));
        width = (int) glyphLayout.width;
        height = (int) glyphLayout.height;
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(background);
        cnt += delta;
        if (cnt > TIME){
            context.switchScreen(ScreenType.GAME);
        }
        batch.begin();
        font.draw(batch, String.valueOf(number), (Const.WIDTH >> 1) - (width >> 1), (Const.HEIGHT >> 1) + (height >> 1));
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
        font.dispose();
    }

    @Override
    public int getNumberListener() {
        return number;
    }
}
