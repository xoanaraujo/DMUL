package com.mygdx.pescador.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Entity {
    protected TextureRegion texture;
    protected Vector2 position;
    protected Integer width, height;

    public Entity(TextureRegion texture, Vector2 position, Integer width, Integer height) {
        this.texture = texture;
        this.position = position;
        this.width = width;
        this.height = height;
    }

    public TextureRegion getTexture() {
        return texture;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void draw(SpriteBatch batch){
        batch.begin();
        batch.draw(texture, position.x, position.y, width, height);
        batch.end();
    }
}
