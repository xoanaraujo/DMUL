package io.github.xoanaraujo.spacerescue;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
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
	private static final int SIZE = 64;
	private SpriteBatch batch;
	private Texture earthTexture;
	private int scale;
	private float velocity;
	private Vector2 direction, position;

	@Override
	public void create () {
		batch = new SpriteBatch();

		earthTexture = new Texture(Gdx.files.internal("Terran.png"));
		earthTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		position = new Vector2(1, 1);
		scale = 1;
		velocity = 5f;
		Random rd = new Random();

		direction = new Vector2( rd.nextFloat(0, 1), rd.nextFloat(0,1)); //
	}

	@Override
	public void render () {
		ScreenUtils.clear(0.14f, 0.14f, 0.18f, 1);
		if (position.x >= (Gdx.graphics.getWidth() - earthTexture.getWidth()) || ((position.x <= 0))){
			direction.x = - direction.x;
			Gdx.app.log("Direccion X", " Cambia de direccion X");
		}
		if ((position.y >= (Gdx.graphics.getHeight() - earthTexture.getHeight()) || (position.y <= 0))){
			direction.y = - direction.y;
			Gdx.app.log("Direccion Y", " Cambia de direccion Y");
		}
		position.x = (position.x + velocity * direction.x);
		position.y = (position.y + velocity * direction.y);

		batch.begin();
		batch.draw(earthTexture, position.x, position.y);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
