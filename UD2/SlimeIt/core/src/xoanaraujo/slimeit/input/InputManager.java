package xoanaraujo.slimeit.input;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;
import xoanaraujo.slimeit.Core;

public class InputManager extends InputAdapter {
    private static final String TAG = InputManager.class.getSimpleName();
    private GameInputListener listener;

    public InputManager(Core context) {

    }

    public void addListener(GameInputListener listener){
        this.listener = listener;
    }

    public void notifyTouchDown(int screenX, int screenY){
        if (listener != null){
            listener.touchDown(this, screenX, screenY);
        }
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        notifyTouchDown(screenX, screenY);
        return true;
    }
}
