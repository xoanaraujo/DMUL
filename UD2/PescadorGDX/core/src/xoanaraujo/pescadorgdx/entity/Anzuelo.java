package xoanaraujo.pescadorgdx.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import xoanaraujo.pescadorgdx.PlayerController;
import xoanaraujo.pescadorgdx.interfaces.PescadorListener;
import xoanaraujo.pescadorgdx.util.Assets;
import xoanaraujo.pescadorgdx.util.Const;

public class Anzuelo extends  DinamicEntity{
    private static Anzuelo instance;
    private float srcYPos;
    private Anzuelo(TextureRegion textureRegion, int width, int height, Vector2 position, Vector2 direction, float velocity) {
        super(textureRegion, width, height, position, direction, velocity);
        srcYPos = position.y;
    }

    public static Anzuelo getInstance() {
        if (instance == null)
            instance = new Anzuelo(Assets.anzuelo, 10, 10, new Vector2(166 + 70, 168), new Vector2(0, 0),200f);
        return instance;
    }

    @Override
    public void move() {
        if(movable){
            float delta = Gdx.graphics.getDeltaTime();

            float yPos = position.y + direction.y * velocity * delta;

            if(yPos < 0)
                position.y = 0;
            else if (yPos > srcYPos){
                position.y = Const.WORLD_HEIGHT - width;
            } else
                position.y = yPos;
        }
    }


}
