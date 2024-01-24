package com.mygdx.pescador.Util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;


public class Assets {

	public static BitmapFont fuente;
	public static TextureAtlas atlas;
	public static Array<Sprite> sprites;

	public static Sound captura, finDelJuego;
	public static Music musicaDeFondo;
	
	public static void cargarTexturas(){

		captura=Gdx.audio.newSound(Gdx.files.internal("sonidos/captura.mp3"));
		finDelJuego=Gdx.audio.newSound(Gdx.files.internal("sonidos/finDelJuego.mp3"));
		musicaDeFondo=Gdx.audio.newMusic(Gdx.files.internal("sonidos/fondo.mp3"));

		//fuente = new BitmapFont(Gdx.files.internal("fuentes/fuente.fnt"));
		fuente = new BitmapFont();
		fuente.getData().setScale(.5f);
		fuente.setColor(Color.RED);

		atlas = new TextureAtlas(Gdx.files.internal("graficos/atlas.atlas"));
		TextureRegion region = atlas.findRegion("pescador");
		sprites = new Array<>();
		System.out.println(region.getRegionX() + " " + region.getRegionY() + " " + region.getRegionWidth() + " " + region.getRegionHeight());
		sprites.add(new Sprite(region, region.getRegionX(), region.getRegionY(), region.getRegionWidth(), region.getRegionHeight()));
		System.out.println(sprites.get(0).getX() + " " + sprites.get(0).getY() + " " + sprites.get(0).getWidth() + " " + sprites.get(0).getHeight());
	}
	
	public static void liberarTexturas(){
		captura.dispose();
		musicaDeFondo.dispose();
		finDelJuego.dispose();
		// fondo.dispose();
		fuente.dispose();
		atlas.dispose();
	}
}
