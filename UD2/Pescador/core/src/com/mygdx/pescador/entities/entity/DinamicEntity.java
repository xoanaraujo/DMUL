package com.mygdx.pescador.entities.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.pescador.Util.GameUtil;

public class DinamicEntity extends Entity{
    protected Vector2 direction;
    protected Float velocity;

    public DinamicEntity(Sprite sprite, Vector2 direction, Float velocity) {
        super(sprite);
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
        float xPos = sprite.getX() + direction.x * velocity * delta;
        float yPos = sprite.getY() + direction.y * velocity * delta;

        if (xPos < 0)
            sprite.setX(0);
        else if (xPos + sprite.getWidth() > GameUtil.WIDTH)
            sprite.setX(GameUtil.WIDTH - sprite.getWidth());
        else
            sprite.setX(xPos);

        if (yPos < 0)
            sprite.setY(0);
        else if (yPos + sprite.getHeight() > GameUtil.HEIGHT)
            sprite.setY(GameUtil.HEIGHT - sprite.getHeight());
        else
            sprite.setY(yPos);
    }

}
