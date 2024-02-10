package xoanaraujo.slimeit.input;

import static com.badlogic.gdx.Input.Keys;

public enum GameKeys {

    UP(Keys.W, Keys.UP),
    DOWN(Keys.S, Keys.DOWN),
    RIGHT(Keys.D, Keys.RIGHT),
    LEFT(Keys.A, Keys.LEFT),
    ATACK(Keys.N),
    SPECIAL(Keys.M),
    SELECT(Keys.SPACE);
    final int[] keyCodes;

    GameKeys(int... keyCodes) {
        this.keyCodes = keyCodes;
    }
}