package examen.plantillaexamen.input;

import com.badlogic.gdx.Input;

public enum GameKey {
    UP(Input.Keys.W, Input.Keys.UP),
    DOWN(Input.Keys.S, Input.Keys.DOWN),
    RIGHT(Input.Keys.D, Input.Keys.RIGHT),
    LEFT(Input.Keys.A, Input.Keys.LEFT);
    final int[] keyCodes;

    GameKey(int... keyCodes) {
        this.keyCodes = keyCodes;
    }
}
