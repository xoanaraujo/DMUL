package com.mygdx.pescador.entities.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Entity {
    protected TextureRegion textureRegion;
    protected Vector2 position;
    protected int width, height;
    public Entity(TextureRegion textureRegion, Vector2 position, int width, int height) {
        this.textureRegion = textureRegion;
        this.position = position;
        this.width = width;
        this.height = height;
    }
    public void draw(SpriteBatch batch){
        batch.draw(textureRegion, position.x, position.y, width, height);
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Vector2 getPosition() {
        return position;
    }
}
