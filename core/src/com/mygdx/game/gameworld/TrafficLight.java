package com.mygdx.game.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by hackintosh on 5/23/17.
 */

public class TrafficLight {
    public float x;
    public float y;
    public float width;
    public float height;
    public float angle;
    private Texture skin;
    private Animation<TextureRegion> spriteAnimation;
    public TextureRegion currentFrame;
    private TextureRegion[] frames;
    private float stateTime;

    private TextureRegion[] flashingGreenFrames;
    private TextureRegion[] flashingYellowFrames;

    public String state;


    public TrafficLight(int position) {
        float screen_width = Gdx.graphics.getWidth();
        float screen_height = Gdx.graphics.getHeight();
        initPosition(position,screen_width,screen_height);
//        this.x = x;
//        this.y = y;
//        this.angle = angle;
        width = 0.032f * screen_width;
        height = 0.113f * screen_height;
        skin = new Texture(Gdx.files.internal("traffic_light.png"));
        frames = new TextureRegion[4];
        TextureRegion[][] temp_frames;
        temp_frames = TextureRegion.split(skin,skin.getWidth() / 4, skin.getHeight());
        for(int i = 0; i < 4; i++) {
            frames[i] = temp_frames[0][i];
        }

        flashingGreenFrames = new TextureRegion[2];
        flashingYellowFrames =  new TextureRegion[2];

        flashingGreenFrames[0] = frames[0];
        flashingGreenFrames[1] = frames[1];

        flashingYellowFrames[0] = frames[0];
        flashingYellowFrames[1] = frames[2];

        stateTime = 0;
    }

    private void initPosition(int position, float width, float height) {
        switch (position) {
            case 0:
                x = 0.592f * width;
                y = 0.522f * height;
                angle = 0;
                break;
            case 1 :
                x = 0.498f * width;
                y = 0.24f * height;
                angle = -90f;
                break;
            case 2 :
                x = 0.34f * width;
                y = 0.415f * height;
                angle = -180f;
                break;
            case 3 :
                x = 0.44f * width;
                y = 0.7f * height;
                angle = -270f;
                break;
        }
    }

    public void green() {

        currentFrame = frames[1];
        state = "green";
    }

    public void yellow() {

        currentFrame = frames[2];
        state = "yellow";
    }

    public void red() {

        currentFrame = frames[3];
        state = "red";
    }

    public void flashingGreen() {
        spriteAnimation = new Animation<TextureRegion>(0.5f,flashingGreenFrames);
        state = "flashingGreen";
    }

    public void flashingYellow() {
        spriteAnimation = new Animation<TextureRegion>(0.5f,flashingYellowFrames);
        state = "flashingYellow";
    }

    public void update(float delta) {
        stateTime += delta;
        if(state.equals("flashingGreen") || state.equals("flashingYellow")) {
            currentFrame = spriteAnimation.getKeyFrame(stateTime, true);
        }
    }

}
