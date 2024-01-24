package com.mygdx.pescador.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.pescador.entities.entity.DinamicEntity;

public class Anzuelo extends DinamicEntity {
    public Anzuelo(Sprite sprite, Vector2 direction, Float velocity) {
        super(sprite, direction, velocity);
    }
}
