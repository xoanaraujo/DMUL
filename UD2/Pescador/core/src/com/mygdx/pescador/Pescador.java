package com.mygdx.pescador;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.pescador.entity.DinamicEntity;

public class Pescador extends DinamicEntity implements InputProcessor {
    private boolean movable;

    public Pescador(TextureRegion texture, Vector2 position, Integer width, Integer height, Vector2 direction, Float velocity) {
        super(texture, position, width, height, direction, velocity);
        this.movable = true;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.SPACE: {
                movable = false;
                direction = new Vector2(0, 0);
            }
            break;
            case Input.Keys.A: {
                if (movable) {
                    direction.x = -1f;
                }
            }
            break;
            case Input.Keys.D: {
                if (movable) direction.x = 1f;
            }
            break;
        }
        Gdx.app.log("Pescador::keyDown", Input.Keys.toString(keycode));
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.SPACE: {
                movable = true;
                if (Gdx.input.isKeyPressed(Input.Keys.D) && !Gdx.input.isKeyPressed(Input.Keys.A))
                    direction.x = 1f;
                else if (Gdx.input.isKeyPressed(Input.Keys.A) && !Gdx.input.isKeyPressed(Input.Keys.D))
                    direction.x = -1f;
            }
            break;
            case Input.Keys.A:
                if (Gdx.input.isKeyPressed(Input.Keys.D) && movable) direction.x = 1f;
                else direction.x = 0;
                break;
            case Input.Keys.D:
                if (Gdx.input.isKeyPressed(Input.Keys.A) && movable) direction.x = -1f;
                else direction.x = 0;
                break;
        }
        Gdx.app.log("Pescador::keyUp", Input.Keys.toString(keycode));

        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }


}
