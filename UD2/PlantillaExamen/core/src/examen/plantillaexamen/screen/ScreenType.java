package examen.plantillaexamen.screen;

import com.badlogic.gdx.Screen;

public enum ScreenType {

    LOADING(LoadingScreen.class),
    GAME(GameScreen.class),
    ENDING(EndingScreen.class);

    private final Class <? extends Screen> screenClass;

    ScreenType(Class<? extends Screen> screenClass) {
        this.screenClass = screenClass;
    }

    public Class<? extends Screen> getScreenClass() {
        return screenClass;
    }
}
