package com.mygdx.pescador;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.cdm.pescador.Assets;
import com.mygdx.pescador.Util.GameUtil;
import com.mygdx.pescador.entities.Anzuelo;
import com.mygdx.pescador.entities.entity.Entity;
import com.mygdx.pescador.entities.Pescador;

public class GameCore extends ApplicationAdapter {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private TextureAtlas atlas;
	private Pescador pescador;
	private Anzuelo anzuelo;
	private Texture background;

	@Override
	public void create () {
		Gdx.graphics.setTitle("Atlas");
		Assets.cargarTexturas();


		camera = new OrthographicCamera();

		batch = new SpriteBatch();
		atlas = new TextureAtlas("graficos/atlas.atlas");

		background = Assets.fondo;
		pescador = new Pescador(Assets.pescador, new Vector2(166, 128), 80, 60,new Vector2(0, 0),  200f);
		anzuelo = new Anzuelo(Assets.anzuelo, new Vector2(166 + 70, 168), 10, 10, new Vector2(0, 0),200f, pescador);

		InputMultiplexer inputMultiplexer = new InputMultiplexer(pescador, anzuelo);
		Gdx.input.setInputProcessor(inputMultiplexer);
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);

		pescador.move();
		anzuelo.move();
		batch.begin();
		batch.draw(background, 0, 0, GameUtil.WIDTH, GameUtil.HEIGHT);
		pescador.draw(batch);
		anzuelo.draw(batch);
		batch.end();
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
