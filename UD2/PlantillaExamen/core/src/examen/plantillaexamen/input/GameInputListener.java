package examen.plantillaexamen.input;

public interface GameInputListener {
    void touchDown(InputManager manager, int screenX, int screenY);

    void keyDown(InputManager manager, GameKey gameKey);

    void keyUp(InputManager inputManager, GameKey gameKey);
}
