package com.mygdx.game.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by hackintosh on 5/16/17.
 */

public class Car {
    private float x;
    private float y;
    private Texture skin;
    private Animation<TextureRegion> spriteAnimation;
    private TextureRegion currentFrame;
    private TextureRegion[] frames;
    private TextureRegion[] rightTurnSignal;
    private TextureRegion[] leftTurnSignal;
    private boolean turnSignals;
    private float stateTime;

    public Car(int type, int frameNumber) {
        skin = new Texture(Gdx.files.internal("cars/" + type + ".png"));
        frames = new TextureRegion[frameNumber];
        TextureRegion[][] temp_frames;
        temp_frames = TextureRegion.split(skin,skin.getWidth() / frameNumber, skin.getHeight());
        for(int i = 0; i < frameNumber; i++) {
            frames[i] = temp_frames[0][i];
        }
        currentFrame = frames[0];

        initTurnRightFrames(frames);
        initTurnLeftFrames(frames);

        turnSignals = false;
        stateTime = 0;


    }

    public void initTurnRightFrames(TextureRegion[] frames) {
        rightTurnSignal = new TextureRegion[2];

        rightTurnSignal[0] = frames[0];
        rightTurnSignal[1] = frames[2];
    }

    public void initTurnLeftFrames(TextureRegion[] frames) {
        leftTurnSignal = new TextureRegion[2];

        leftTurnSignal[0] = frames[0];
        leftTurnSignal[1] = frames[1];
    }

    public void update(float delta) {
        stateTime += delta;
        if(turnSignals) {
            currentFrame = spriteAnimation.getKeyFrame(stateTime, true);
        }
    }

    public void turnSignalsRight() {
        if(turnSignals) {
            turnSignals = false;
            stateTime = 0;
            currentFrame = frames[0];
        }
        else {
            spriteAnimation = new Animation<TextureRegion>(0.5f, rightTurnSignal);
            turnSignals = true;
        }
    }
    
    public void turnSignalsLeft() {
        if(turnSignals) {
            turnSignals= false;
            stateTime = 0;
            currentFrame = frames[0];
        }
        else {
            spriteAnimation = new Animation<TextureRegion>(0.5f, leftTurnSignal);
            turnSignals = true;
        }
    }

    public TextureRegion getCurrentFrame() { return this.currentFrame; }
}
