package io.github.xoanaraujo.spacerescue;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.utils.ScreenUtils;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public class MyGdxGame extends ApplicationAdapter {
	private static final int SIZE = 64;
	private SpriteBatch batch;
	private Texture earthTexture;
	private boolean up;
	private int x, y, scale;

	@Override
	public void create () {
		batch = new SpriteBatch();

		earthTexture = new Texture(Gdx.files.internal("Terran.png"));
		earthTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		x = y = 0;
		scale = 1;
	}

	@Override
	public void render () {
		ScreenUtils.clear(0.14f, 0.14f, 0.18f, 1);
		if (x == (Gdx.graphics.getWidth() - earthTexture.getWidth()) || x == 0)
			up = !up;
		if (up){
			x++;
			y++;
		}
		if (!up){
			x--;
			y--;
		}
		batch.begin();
		batch.draw(earthTexture, x, y);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
