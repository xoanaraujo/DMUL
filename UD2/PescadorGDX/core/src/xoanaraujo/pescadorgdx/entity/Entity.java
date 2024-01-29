package xoanaraujo.pescadorgdx.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import xoanaraujo.pescadorgdx.interfaces.MyDrawable;

public class Entity implements MyDrawable {
    private TextureRegion textureRegion;
    protected int width, height;
    protected Vector2 position;

    public Entity(TextureRegion textureRegion, int width, int height, Vector2 position) {
        this.textureRegion = textureRegion;
        this.width = width;
        this.height = height;
        this.position = position;
    }

    @Override
    public void draw(SpriteBatch batch, ShapeRenderer renderer) {
        batch.draw(textureRegion, position.x, position.y, width, height);
    }
}
