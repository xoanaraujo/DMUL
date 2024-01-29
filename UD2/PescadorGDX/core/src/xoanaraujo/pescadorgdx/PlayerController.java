package xoanaraujo.pescadorgdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;

import xoanaraujo.pescadorgdx.entity.Anzuelo;
import xoanaraujo.pescadorgdx.entity.Pescador;
import xoanaraujo.pescadorgdx.interfaces.PescadorListener;

public class PlayerController extends InputAdapter implements PescadorListener {
    private static PlayerController instance;
    private Pescador pescador;
    private Anzuelo anzuelo;

    private boolean movable;

    private PlayerController(Pescador pescador, Anzuelo anzuelo) {
        this.pescador = pescador;
        this.anzuelo = anzuelo;
        movable = true;
    }

    public static PlayerController getInstance() {
        if (instance == null)
            instance = new PlayerController(Pescador.getInstance(), Anzuelo.getInstance());
        return instance;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (movable){
            switch (keycode){
                case Input.Keys.SPACE:{
                    setMovable(false);
                    pescador.getDirection().x = 0;
                    anzuelo.getDirection().y = -1f;
                } break;
                case Input.Keys.A:{
                    pescador.getDirection().x = -1f;
                } break;
                case Input.Keys.D:{
                    pescador.getDirection().x = 1f;
                } break;
            }
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (movable){
            switch (keycode) {
                case Input.Keys.SPACE: {
                    if (Gdx.input.isKeyPressed(Input.Keys.D) && !Gdx.input.isKeyPressed(Input.Keys.A))
                        pescador.getDirection().x = 1f;
                    else if (Gdx.input.isKeyPressed(Input.Keys.A) && !Gdx.input.isKeyPressed(Input.Keys.D))
                        pescador.getDirection().x = -1f;
                }
                break;
                case Input.Keys.A:
                    if (Gdx.input.isKeyPressed(Input.Keys.D) && movable) pescador.getDirection().x = 1f;
                    else pescador.getDirection().x = 0;
                    break;
                case Input.Keys.D:
                    if (Gdx.input.isKeyPressed(Input.Keys.A) && movable) pescador.getDirection().x = -1f;
                    else pescador.getDirection().x = 0;
                    break;
            }
        }
        return true;
    }

    private void setMovable(boolean movable){
        pescador.setMovable(movable);
        anzuelo.setMovable(movable);
    }

    @Override
    public void ready() {
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            movable = false;
            pescador.getDirection().x = 0;
            anzuelo.getDirection().y = -1f;
        }else if (Gdx.input.isKeyPressed(Input.Keys.D)){
            pescador.getDirection().x = 1f;
        }else if (Gdx.input.isKeyPressed(Input.Keys.A)){
            pescador.getDirection().x = -1f;
        }
    }
}
