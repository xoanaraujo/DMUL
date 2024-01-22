package com.mygdx.pescador;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.pescador.entity.DinamicEntity;

public class Anzuelo extends DinamicEntity {
    public Anzuelo(TextureRegion texture, Vector2 position, Integer width, Integer height, Vector2 direction, Float velocity) {
        super(texture, position, width, height, direction, velocity);
    }
}
