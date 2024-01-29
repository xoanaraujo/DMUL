package xoanaraujo.pescadorgdx;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

import xoanaraujo.pescadorgdx.entity.Anzuelo;
import xoanaraujo.pescadorgdx.entity.DinamicEntity;
import xoanaraujo.pescadorgdx.entity.Pescador;
import xoanaraujo.pescadorgdx.util.Assets;
import xoanaraujo.pescadorgdx.util.Const;

public class Core extends ApplicationAdapter {
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private Texture background;
	private DinamicEntity pescador, anzuelo;


	@Override
	public void create () {
		Assets.cargarTexturas();
		background = Assets.fondo;
		pescador = Pescador.getInstance();
		anzuelo = Anzuelo.getInstance();

		batch = new SpriteBatch();
		camera = new OrthographicCamera();

		Gdx.input.setInputProcessor(PlayerController.getInstance());
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);

		pescador.move();
		anzuelo.move();

		batch.begin();
		batch.draw(background, 0, 0, Const.WORLD_WIDTH, Const.WORLD_HEIGHT);
		pescador.draw(batch, null);
		anzuelo.draw(batch, null);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		camera.setToOrtho(false, Const.WORLD_WIDTH, Const.WORLD_HEIGHT);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}
