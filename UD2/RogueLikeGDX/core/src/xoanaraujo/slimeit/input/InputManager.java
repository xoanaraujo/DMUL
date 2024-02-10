package xoanaraujo.slimeit.input;

import com.badlogic.gdx.InputAdapter;
import xoanaraujo.slimeit.Core;

public class InputManager extends InputAdapter {
    private static final String TAG = InputManager.class.getSimpleName();
    private final GameKey[] keyMapping;
    private boolean[] keyState;
    private GameInputListener listener;

    public InputManager(Core context) {
        this.keyMapping = new GameKey[256];
        for (GameKey gameKey : GameKey.values()) {
            for (int keyCode : gameKey.keyCodes) {
                    keyMapping[keyCode] = gameKey;
            }
        }
        keyState = new boolean[keyMapping.length];
    }

    public void setGameListener(GameInputListener listener){
        this.listener = listener;
    }

    public void notifyTouchDown(int screenX, int screenY){
        if (listener != null){
            listener.touchDown(this, screenX, screenY);
        }
    }

    public void notifyKeydown(final GameKey gameKey){
        if (listener != null){
            keyState[gameKey.ordinal()] = true;
            listener.keyDown(this, gameKey);
        }
    }

    private void notifyKeyUp(GameKey gameKey) {
        if (listener != null){
            keyState[gameKey.ordinal()] = false;
            listener.keyUp(this, gameKey);
        }
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        notifyTouchDown(screenX, screenY);
        return true;
    }

    public boolean isKeyPressed (GameKey gameKey){
        return keyState[gameKey.ordinal()];
    }

    @Override
    public boolean keyDown(int keycode) {
        final GameKey gameKey = keyMapping[keycode];
        if (gameKey == null)
            return false;
        notifyKeydown(gameKey);
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        final GameKey gameKey = keyMapping[keycode];
        if (gameKey == null)
            return false;
        notifyKeyUp(gameKey);
        return true;
    }
}
