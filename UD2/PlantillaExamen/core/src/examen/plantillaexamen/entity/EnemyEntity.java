package examen.plantillaexamen.entity;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class EnemyEntity extends AbstractEntity{
    public EnemyEntity(Vector2 position, Vector2 direction, int width, int height, float velocity) {
        super(position, direction, width, height, velocity);
    }

    @Override
    public void move(float deltaTime) {

    }

    @Override
    public void draw(ShapeRenderer shapeRenderer) {

    }
}
