package xoanaraujo.pescadorgdx.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import xoanaraujo.pescadorgdx.util.Assets;
import xoanaraujo.pescadorgdx.util.Const;

public class Pescador extends DinamicEntity{

    private static Pescador instance;

    private Pescador(TextureRegion textureRegion, int width, int height, Vector2 position, Vector2 direction, float velocity) {
        super(textureRegion, width, height, position, direction, velocity);
    }

    public static Pescador getInstance() {
        if (instance == null)
            instance = new Pescador(Assets.pescador,80, 60, new Vector2(166, 128), new Vector2(0, 0),  200f);
        return instance;
    }

    @Override
    public void move() {
        if(movable){
            float delta = Gdx.graphics.getDeltaTime();

            float xPos = position.x + direction.x * velocity * delta;

            if(xPos < 0)
                position.x = 0;
            else if (xPos + width > Const.WORLD_WIDTH)
                position.x = Const.WORLD_WIDTH - width;
            else
                position.x = xPos;
        }
    }
}
