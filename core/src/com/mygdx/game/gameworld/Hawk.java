package com.mygdx.game.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Timer;

/**
 * Created by the-french-cat on 25/05/17.
 */

public class Hawk {

    /*public fields*/
    public float x;
    public float y;
    public float width;
    public float height;
    public Texture texture;
    public TextureRegion currentFrame;

    /*private fields*/
    private Sound mSound;
    private float stateTime;
    private boolean mFlying;

    /*constructors*/
    Hawk() {
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();

        texture = new Texture(Gdx.files.internal("hawk.png"));
        mSound = Gdx.audio.newSound(Gdx.files.internal("hawk.ogg"));
        x = -50f;
        y = -50f;
        /*currentFrame = new TextureRegion();
        currentFrame = TextureRegion.split(texture, texture.getWidth(), texture.getHeight())[0][0];
        */
        Timer.schedule(getNewHawkTask(), 5, 15);
    }

    /*public methods*/
    void dispose() {
        texture.dispose();
        mSound.dispose();
    }

    void update(float delta) {
        stateTime += delta;

        Gdx.app.log("hawk", "" + x + " " + y);
        if (mFlying) {
            x += delta * 400;
            y += delta * 150;
        }

        if (x > Gdx.graphics.getWidth() || y > Gdx.graphics.getHeight()) {
            mFlying = false;
        }
    }

    /*private methods*/
    private Timer.Task getNewHawkTask() {
        return new Timer.Task() {
            @Override
            public void run() {
                mFlying = true;
                mSound.play();
                x = -50f;
                y = -50f;
            }
        };
    }
}
