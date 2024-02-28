package examen.plantillaexamen.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import static examen.plantillaexamen.util.Constantes.HEIGHT;
import static examen.plantillaexamen.util.Constantes.WIDTH;

public abstract class AbstractEntity {
    private static final String TAG = AbstractEntity.class.getSimpleName();
    protected Vector2 position, direction;
    protected boolean directionChanged;
    protected int width, height;
    protected float velocity;

    public AbstractEntity(Vector2 position, Vector2 direction, int width, int height, float velocity) {
        this.position = position;
        this.direction = direction;
        this.width = width;
        this.height = height;
        this.velocity = velocity;
        directionChanged = false;
    }

    public void move(float deltaTime) {
        Gdx.app.debug(TAG, "" + width);
        Gdx.app.debug(TAG, "" + height);

    }
    public abstract void draw(ShapeRenderer shapeRenderer);

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getDirection() {
        return direction;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public float getVelocity() {
        return velocity;
    }

    public boolean isDirectionChanged() {
        return directionChanged;
    }

    public void setDirectionChanged(boolean directionChanged) {
        this.directionChanged = directionChanged;
    }
}
