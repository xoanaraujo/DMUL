package xoanaraujo.roguelikegdx.input;

import static com.badlogic.gdx.Input.Keys;

public enum GameKey {

    UP(Keys.W, Keys.UP),
    DOWN(Keys.S, Keys.DOWN),
    RIGHT(Keys.D, Keys.RIGHT),
    LEFT(Keys.A, Keys.LEFT),
    ATACK(Keys.N),
    SPECIAL(Keys.M),
    SELECT(Keys.SPACE);
    final int[] keyCodes;

    GameKey(int... keyCodes) {
        this.keyCodes = keyCodes;
    }
}
