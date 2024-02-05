package xoanaraujo.tuttifruttigdx.screens;

import com.badlogic.gdx.Screen;

public enum ScreenType {
    LOADING(LoadingScreen.class),
    GAME(GameScreen.class);
    private Class<? extends Screen> screen;

    ScreenType(Class<? extends Screen> screen) {
        this.screen = screen;
    }

    public Class<? extends Screen> getScreen() {
        return screen;
    }
}
