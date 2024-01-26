package com.mygdx.pescador.entities.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.pescador.Util.GameUtil;

public class DinamicEntity extends Entity{
    protected Vector2 direction;
    protected Float velocity;

    public DinamicEntity(TextureRegion textureRegion, Vector2 position, int width, int height, Vector2 direction, Float velocity) {
        super(textureRegion, position, width, height);
        this.direction = direction;
        this.velocity = velocity;
    }
    public Vector2 getDirection() {
        return direction;
    }

    public float getVelocity() {
        return velocity;
    }

    public void move(){
        float delta = Gdx.graphics.getDeltaTime();

        direction.nor();
        float xPos = position.x + direction.x * velocity * delta;
        float yPos = position.y + direction.y * velocity * delta;

        if (xPos < 0)
            position.x = 0;
        else if (xPos + width > GameUtil.WIDTH)
            position.x = GameUtil.WIDTH - width;
        else
            position.x = xPos;

        if (yPos < 0)
            position.y = 0;
        else if (yPos + height > GameUtil.HEIGHT)
            position.y = GameUtil.HEIGHT - height;
        else
            position.y = yPos;
    }

}
