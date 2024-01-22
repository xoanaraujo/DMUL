package com.example.gdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Player extends InputAdapter {
    private TextureRegion textureRegion;
    private Texture playerTexture;
    private Vector2 position, direction;
    private float velocity;

    public Player(Texture playerTexture, Vector2 position, float velocity) {
        this.playerTexture = playerTexture;
        this.position = position;
        direction = new Vector2(0, 0);
        this.velocity = velocity;
    }

    public Texture getPlayerTexture() {
        return playerTexture;
    }

    public Vector2 getPosition() {
        return position;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.W:
                direction.y = 1f;
                break;
            case Input.Keys.S:
                direction.y = -1f;
                break;
            case Input.Keys.A:
                direction.x = -1f;
                break;
            case Input.Keys.D:
                direction.x = 1f;
                break;
            default:
                Gdx.app.log("Player::keyDown()", keycode + " not mapped");
        }
        direction.nor();
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.W: {
                if (Gdx.input.isKeyPressed(Input.Keys.S)) direction.y = -1f;
                else direction.y = 0;
            }
            break;
            case Input.Keys.S: {
                if (Gdx.input.isKeyPressed(Input.Keys.W)) direction.y = 1f;
                else direction.y = 0;
            }
            break;
            case Input.Keys.A: {
                if (Gdx.input.isKeyPressed(Input.Keys.D)) direction.x = 1f;
                else direction.x = 0;
            }
            break;
            case Input.Keys.D: {
                if (Gdx.input.isKeyPressed(Input.Keys.A)) direction.x = -1f;
                else direction.x = 0;
            }
            break;
            default:
                Gdx.app.log("Player::keyUp()", keycode + " not mapped");
        }
        direction.nor();
        return true;
    }

    public void movePlayer() {
        float deltatime = Gdx.graphics.getDeltaTime();

        position.x += direction.x * deltatime * velocity;
        position.y += direction.y * deltatime * velocity;

        Gdx.app.log("Velocity", String.valueOf(Math.sqrt(Math.pow(direction.x, 2) + Math.pow(direction.y, 2))));
    }

    public void drawPlayer(SpriteBatch batch) {
        batch.begin();
        batch.draw(playerTexture, position.x, position.y);
        batch.end();
    }
}
