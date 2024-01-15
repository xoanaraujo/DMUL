package com.mygdx.colores;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Random;

public class GameCore extends ApplicationAdapter {
	private static Sound sound;
	private static final Random rd = new Random();
	private static final String KEY = "record", PREFERENCES = "ColoresPreferences";
	private SpriteBatch batch;
	private BitmapFont pointsBmf;
	private Color[] colors = new Color[]{Color.RED, Color.GREEN, Color.BLACK, Color.BLUE};
	private Color bgColor;
	private int lastIndex, winIndex, points, record;
	private float time, marginTime, minMarginTime;
	private boolean pressed;
	@Override
	public void create () {
		sound = Gdx.audio.newSound(Gdx.files.internal("sound.mp3"));
		batch = new SpriteBatch();
		pointsBmf = new BitmapFont();
		bgColor = new Color(colors[(lastIndex = rd.nextInt(colors.length))]);
		winIndex = 0;
		points = 0;
		time = 0;
		marginTime = 1.5f;
		minMarginTime = 0.4f;
		record = Gdx.app.getPreferences(PREFERENCES).getInteger(KEY);
	}

	@Override
	public void render () {
		ScreenUtils.clear(bgColor);
		float deltatime = Gdx.graphics.getDeltaTime();
		time += deltatime;
		if (time >= marginTime){
			sound.play();
			if(lastIndex == winIndex && !pressed || lastIndex != winIndex && pressed)
				Gdx.app.exit();
			Gdx.app.log("Time", String.valueOf(time));
			time -= marginTime;
			if (marginTime > minMarginTime)
				marginTime -= 0.1f;
			int index =rd.nextInt(colors.length);
			if (index == lastIndex)
				if (index < colors.length - 1)
					index++;
				else
					index--;
			bgColor = colors[index];
			lastIndex = index;
			pressed = false;
		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
			if(lastIndex == winIndex && !pressed){
				points++;
				pressed = true;
			}
			else
				Gdx.app.exit();
		if(record < points)
			record = points;
		batch.begin();
		pointsBmf.draw(batch, String.valueOf(points), 10, Gdx.graphics.getHeight() - 10);
		pointsBmf.draw(batch, String.valueOf(record), 50, Gdx.graphics.getHeight() - 10);
		batch.end();

	}
	
	@Override
	public void dispose () {
		Preferences preferences = Gdx.app.getPreferences(PREFERENCES);

		preferences.putInteger(KEY, record);

		preferences.flush();
		batch.dispose();
	}
}
