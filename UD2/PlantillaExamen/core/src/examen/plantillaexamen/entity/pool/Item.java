package examen.plantillaexamen.entity.pool;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import examen.plantillaexamen.entity.AbstractEntity;

public class Item extends AbstractEntity {

    public Item(Vector2 position, Vector2 direction, int width, int height, float velocity) {
        super(position, direction, width, height, velocity);
    }

    @Override
    public void draw(ShapeRenderer shapeRenderer) {

    }
}
