package xoanaraujo.pescadorgdx.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import xoanaraujo.pescadorgdx.interfaces.MyMovable;
import xoanaraujo.pescadorgdx.util.Const;

public class DinamicEntity extends Entity implements MyMovable {

    protected Vector2 direction;
    protected float velocity;

    protected boolean movable;

    public DinamicEntity(TextureRegion textureRegion, int width, int height, Vector2 position, Vector2 direction, float velocity) {
        super(textureRegion, width, height, position);
        this.direction = direction;
        this.velocity = velocity;
        movable = true;
    }

    @Override
    public void move() {
        if (movable){
            float delta = Gdx.graphics.getDeltaTime();

            direction.nor();
            float xPos = position.x + direction.x * velocity * delta;
            float yPos = position.y + direction.y * velocity * delta;

            if(xPos < 0)
                position.x = 0;
            else if (xPos + width > Const.WORLD_WIDTH)
                position.x = Const.WORLD_WIDTH - width;
            else
                position.x = xPos;

            if(yPos < 0)
                position.y = 0;
            else if (yPos + height > Const.WORLD_HEIGHT)
                position.y = Const.WORLD_HEIGHT - width;
            else
                position.y = yPos;
        }
    }

    public void setDirection(Vector2 direction) {
        this.direction = direction;
    }

    public void setMovable(boolean movable) {
        this.movable = movable;
    }

    public Vector2 getDirection() {
        return direction;
    }

    public boolean isMovable() {
        return movable;
    }

}
