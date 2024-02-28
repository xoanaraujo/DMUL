package examen.plantillaexamen.entity;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;

import static examen.plantillaexamen.util.Constantes.HEIGHT;
import static examen.plantillaexamen.util.Constantes.WIDTH;

public class PlayerEntity extends AbstractEntity{
    private Circle collision;
    public PlayerEntity(Vector2 position, Vector2 direction, int width, int height, float velocity) {
        super(position, direction, width, height, velocity);
        collision = new Circle();
        collision.setRadius(width);
    }

    @Override
    public void move(float deltaTime) {
        direction.nor();
        position.x += direction.x * velocity * deltaTime;
        position.y += direction.y * velocity * deltaTime;

        if (position.x < width) {
            position.x = width;
        } else if (position.x + width > WIDTH){
            position.x = WIDTH - width;
        }

        if (position.y < height) {
            position.y = height;
        } else if (position.y + height > HEIGHT){
            position.y = HEIGHT - height;
        }

        //TODO super.move(deltaTime);
    }

    @Override
    public void draw(ShapeRenderer shapeRenderer) {
        shapeRenderer.circle(position.x, position.y, collision.radius);
    }
}
