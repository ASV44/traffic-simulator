package com.mygdx.game.main_game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.mygdx.game.screens.TrafficScreen;

public class Main extends Game {
    private TrafficScreen trafficScreen;
    private Music ambientSound;

    @Override
	public void create () {
        ambientSound = Gdx.audio.newMusic(Gdx.files.internal("ambient-sound.ogg"));
        ambientSound.setLooping(true);
        ambientSound.play();
        Gdx.app.log("Screen_Width","" + Gdx.graphics.getWidth());
		Gdx.app.log("Screen_Height","" + Gdx.graphics.getHeight());
        trafficScreen = new TrafficScreen();
        setScreen(trafficScreen);
	}

	
	@Override
    public void dispose() {
        ambientSound.stop();
        ambientSound.dispose();
        trafficScreen.dispose();
	}
}
