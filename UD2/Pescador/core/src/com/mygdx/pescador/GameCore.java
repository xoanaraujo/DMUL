package com.mygdx.pescador;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.pescador.Util.GameUtil;
import com.mygdx.pescador.entities.entity.Entity;
import com.mygdx.pescador.entities.Pescador;

public class GameCore extends ApplicationAdapter {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private TextureAtlas atlas;
	private Pescador pescador;
	private Entity background;

	@Override
	public void create () {
		Gdx.graphics.setTitle("Atlas");

		camera = new OrthographicCamera();

		batch = new SpriteBatch();
		atlas = new TextureAtlas("graficos/atlas.atlas");

		background = new Entity(new Sprite(new Texture(Gdx.files.internal("graficos/fondo.jpg")), 0, 0, GameUtil.WIDTH, GameUtil.HEIGHT));

		Sprite sprite = atlas.createSprite("pescador");
		sprite.setX(50);
		sprite.setY(128);
		sprite.setRegionWidth(80);
		sprite.setRegionHeight(60);
		pescador = new Pescador(sprite, new Vector2(0 , 0), 100f);


		Gdx.input.setInputProcessor(pescador);
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);

		pescador.move();

		background.draw(batch);
		pescador.draw(batch);
	}

	@Override
	public void resize(int width, int height) {
		camera.setToOrtho(false, GameUtil.WIDTH, GameUtil.HEIGHT);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		super.resize(width, height);
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}
