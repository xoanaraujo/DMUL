package xoanaraujo.tuttifruttigdx.input;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;

import xoanaraujo.tuttifruttigdx.Core;

public class InputManager extends InputAdapter {
    private static final String TAG = InputManager.class.getSimpleName();
    private Array<GameInputListener> listeners;

    public InputManager(Core context) {
        listeners = new Array<>();
    }

    public void addListener(GameInputListener listener){
        if (listeners.contains(listener, true)){
            throw  new GdxRuntimeException("InputListener " + listener + " already in listeners array");
        }
        listeners.add(listener);
    }

    public void notifyTouchDown(int screenX, int screenY){
        for (final GameInputListener listener :
                listeners) {
            listener.touchDown(this, screenX, screenY);
        }
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        notifyTouchDown(screenX, screenY);
        return true;
    }
}
