package com.mygdx.game.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Timer;

import static com.badlogic.gdx.math.MathUtils.atan2;

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
    public float angle;
    float scale;

    /*private fields*/
    private Sound mSound;
    private float stateTime;
    private boolean mFlying;
    private float mXCoeff;
    private float mYCoeff;

    /*constructors*/
    Hawk(int skinNumber, float scale, float xCoeff, float yCoeff, int initDelay, int delay) {
        this.scale = scale;
        mXCoeff = xCoeff;
        mYCoeff = yCoeff;
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();

        angle = atan2(mYCoeff, mXCoeff);
        texture = new Texture(Gdx.files.internal("hawk" + skinNumber + ".png"));
        height = texture.getHeight();
        width = texture.getWidth();
        currentFrame = TextureRegion.split(texture, (int) width, (int) height)[0][0];
        mSound = Gdx.audio.newSound(Gdx.files.internal("hawk.ogg"));

        x = -width / 2;
        y = -height / 2 - 450;
        Timer.schedule(getNewHawkTask(), initDelay, delay);
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
            x += delta * mXCoeff;
            y += delta * mYCoeff;
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
                x = -width;
                y = -height;
            }
        };
    }
}
