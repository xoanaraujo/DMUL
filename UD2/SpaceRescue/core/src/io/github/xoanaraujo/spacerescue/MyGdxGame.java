package io.github.xoanaraujo.spacerescue;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
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

public class MyGdxGame extends ApplicationAdapter{
	private static int WIDTH;
	private static int HEIGHT;
	private static final Random rd = new Random();
	private static Color bgColor;
	private static final int SIZE = 64;
	private SpriteBatch batch;
	private Texture earthTexture;
	private float time;
	private float velocity;
	private Vector2 direction, position;
	private boolean launched;

	@Override
	public void create () {
		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();
		InputMultiplexer inputMultiplexer = new InputMultiplexer();
		inputMultiplexer.addProcessor(new PlanetInputAdapter());
		inputMultiplexer.addProcessor(new InputAdapter());
		Gdx.input.setInputProcessor(inputMultiplexer);

		batch = new SpriteBatch();
		bgColor = new Color(0.14f,0.14f,0.18f, 1);

		earthTexture = new Texture(Gdx.files.internal("Terran.png"));
		earthTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		time = 0;
		velocity = 1f;
		launched = false;

	}

	@Override
	public void render() {
		ScreenUtils.clear(bgColor);
		if (launched){
			float deltatime = Gdx.graphics.getDeltaTime();

			if(position.x >= WIDTH - earthTexture.getWidth()){
				position.x = WIDTH - earthTexture.getWidth();
				direction.x *= -1;
			} else if (position.x <= 0 ){
				position.x = 0;
				direction.x *= -1;
			}
			if(position.y >= HEIGHT - earthTexture.getHeight()){
				position.y = HEIGHT - earthTexture.getHeight();
				direction.y *= -1;
			} else if (position.y <= 0 ){
				position.y = 0;
				direction.y *= -1;
			}


			position.x 	= (position.x + velocity * direction.x * deltatime);
			position.y = (position.y + velocity * direction.y * deltatime);

			batch.begin();
			batch.draw(earthTexture, position.x, position.y);
			batch.end();
		}

	}

	
	@Override
	public void dispose () {
		batch.dispose();
	}
	private class PlanetInputAdapter extends InputAdapter{
		@Override
		public boolean touchDown(int screenX, int screenY, int pointer, int button) {
			if (!launched){
				position = new Vector2(screenX , HEIGHT - screenY );
			}
			return super.touchDown(screenX, screenY, pointer, button);
		}

		@Override
		public boolean touchUp(int screenX, int screenY, int pointer, int button) {
			if (!launched){
				direction = new Vector2(screenX - position.x, HEIGHT - screenY - position.y);
				Gdx.app.log("Position", position.toString());
				Gdx.app.log("Direction", direction.toString());
				launched = true;
			} else
				launched = false;
			return super.touchUp(screenX, screenY, pointer, button);
		}
	}
}
