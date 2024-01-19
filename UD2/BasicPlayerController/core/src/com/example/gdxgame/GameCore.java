package com.example.gdxgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameCore extends ApplicationAdapter {
	private SpriteBatch batch;
	private Player player;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		Texture texture = new Texture(Gdx.files.internal("player.png"));
		player = new Player(
				texture,
				new Vector2((Gdx.graphics.getWidth() >> 1) - (texture.getWidth() >> 1), (Gdx.graphics.getHeight() >> 1)  - (texture.getHeight() >> 1)),
				200f
		);
		Gdx.input.setInputProcessor(player);
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		player.movePlayer();
		player.drawPlayer(batch);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		player.getPlayerTexture().dispose();
	}
}
