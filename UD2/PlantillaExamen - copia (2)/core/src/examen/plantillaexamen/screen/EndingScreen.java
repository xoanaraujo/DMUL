package examen.plantillaexamen.screen;

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
    }

    @Override
    public void render(float deltaTime) {
        ScreenUtils.clear(0, 0, 0, 1f);
        batch.begin();

        bitmapFont.draw(batch, "Hola", 0, 0);

        batch.end();
    }
}
