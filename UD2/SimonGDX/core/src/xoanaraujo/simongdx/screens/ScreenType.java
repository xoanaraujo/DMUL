package xoanaraujo.simongdx.screens;

import com.badlogic.gdx.Screen;

public enum ScreenType {
    NUMBER(NumberScreen.class),
    GAME(GameScreen.class);
    private final Class<? extends Screen> screenClass;

    ScreenType(Class<? extends Screen> screenClass) {
        this.screenClass = screenClass;
    }

    public Class<? extends Screen> getScreenClass() {
        return screenClass;
    }
}
