package com.mygdx.pescador.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.pescador.entities.entity.DinamicEntity;
import com.mygdx.pescador.entities.entity.Entity;

public class Anzuelo extends DinamicEntity{
    private Pescador pescador;
    private float srcY;
    private boolean movable;

    public Anzuelo(TextureRegion textureRegion, Vector2 position, int width, int height, Vector2 direction, float velocity, Pescador pescador) {
        super(textureRegion, position, width, height, direction, velocity);
        this.pescador = pescador;
        this.srcY = position.y;
        this.movable = true;
    }

    @Override
    public void move() {
        position.x = pescador.getPosition().x + 70;
        position.y += direction.y * velocity * Gdx.graphics.getDeltaTime();
        if (position.y > srcY)
            position.y = srcY;
        else if (position.y < 0)
            position.y = 0;

    }
}
