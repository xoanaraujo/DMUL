package examen.plantillaexamen.screen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import examen.plantillaexamen.Core;

public class EndingScreen extends AbstractScreen{
    private final SpriteBatch batch;
    private final BitmapFont bitmapFont;
    public EndingScreen(Core context) {
        super(context);
        batch = context.getBatch();
        bitmapFont = context.getBitmapFont();
        bitmapFont.setColor(Color.BLUE);
    }

    @Override
    public void render(float deltaTime) {
        ScreenUtils.clear(0, 1, 0, 1f);
        batch.begin();

        bitmapFont.draw(batch, "dasdasdasdasdasdadawsdaw", 20, 20);


        batch.end();
    }
}
