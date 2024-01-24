package com.mygdx.pescador.entities.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Entity {
    protected Sprite sprite;
    public Entity(Sprite sprite) {
        this.sprite = sprite;
    }
    public void draw(SpriteBatch batch){
        batch.begin();
        batch.draw(sprite.getTexture(), sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
        batch.end();
    }
}
