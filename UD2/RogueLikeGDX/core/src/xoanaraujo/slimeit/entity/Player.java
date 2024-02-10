package xoanaraujo.slimeit.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import xoanaraujo.slimeit.Core;
import xoanaraujo.slimeit.animations.AnimationType;
import xoanaraujo.slimeit.animations.AnimationSet;

import java.util.EnumMap;

public class Player extends Entity{

    public static final String TAG = Player.class.getSimpleName();

    public Player(Core context, Vector2 position, Vector2 direction, int width, int height, float velocity, AnimationSet aniSet) {
        super(context, position, direction, width, height, velocity, aniSet);
    }

    @Override
    public void render(float deltaTime) {
        super.render(deltaTime);
        direction.nor();
        position.x += direction.x * velocity * deltaTime;
        position.y += direction.y * velocity * deltaTime;
    }


}
