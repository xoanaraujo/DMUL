package xoanaraujo.roguelikegdx.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.I18NBundle;
import xoanaraujo.roguelikegdx.Core;

public abstract class AbstractUI extends Table {
    protected final I18NBundle i18NBundle;

    public AbstractUI(Core context) {
        super(context.getSkin());
        this.i18NBundle = context.getI18NBundle();
    }
}
