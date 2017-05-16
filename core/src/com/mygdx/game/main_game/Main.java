package com.mygdx.game.main_game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.screens.TrafficScreen;

public class Main extends Game {
    private TrafficScreen trafficScreen;
	
	@Override
	public void create () {
        trafficScreen = new TrafficScreen();
        setScreen(trafficScreen);
	}

	
	@Override
	public void dispose () {
        trafficScreen.dispose();
	}
}
