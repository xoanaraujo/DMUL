package io.github.xoanaraujo.spacerescue;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.Random;

public class MyGdxGame extends ApplicationAdapter {
	private static final Random rd = new Random();
	private static Color bgColor;
	private static final int SIZE = 64;
	private SpriteBatch batch;
	private Texture earthTexture;
	private float time;
	private float velocity;
	private Vector2 direction, position;
	private boolean bouncedX, bouncedY;

	@Override
	public void create () {
		batch = new SpriteBatch();
		bgColor = new Color(0.14f,0.14f,0.18f, 1);

		earthTexture = new Texture(Gdx.files.internal("Terran.png"));
		earthTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		position = new Vector2(1, 1);
		time = 0;
		velocity = 1000f;

		direction = new Vector2( rd.nextFloat(0, 1), rd.nextFloat(0,1)); //
	}

	@Override
	public void render() {
		ScreenUtils.clear(bgColor);
		float deltatime = Gdx.graphics.getDeltaTime();
		time += deltatime;
		if (time > 1){
			time-= 1;
			bgColor =new Color(rd.nextFloat(1), rd.nextFloat(1), rd.nextFloat(1), 1);
		}
		if (position.x >= (Gdx.graphics.getWidth() - earthTexture.getWidth())){
			position.x = Gdx.graphics.getWidth() - earthTexture.getWidth();
			direction.x = - direction.x;
		}
		if ((position.x <= 0)){
			position.x = 0;
			direction.x = - direction.x;
		}
		if (position.y >= (Gdx.graphics.getHeight() - earthTexture.getHeight())){
			position.y = Gdx.graphics.getHeight() - earthTexture.getHeight();
			direction.y = - direction.y;
		}
		if ((position.y <= 0)){
			position.y = 0;
			direction.y = - direction.y;
		}
		position.x = (position.x + velocity * direction.x * deltatime);
		position.y = (position.y + velocity * direction.y * deltatime);

		batch.begin();
		batch.draw(earthTexture, position.x, position.y);
		batch.end();
	}

	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
