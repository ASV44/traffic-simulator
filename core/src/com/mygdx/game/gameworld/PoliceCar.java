package com.mygdx.game.gameworld;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by hackintosh on 5/25/17.
 */

public class PoliceCar extends Car {
    private TextureRegion[] forwardMove;

    PoliceCar(float angle, CarMoveDirection moveDir) {
        super(0, 8, angle, moveDir);
        initForwardMoveFrames(frames);
        initTurnRightFrames(frames);
        initTurnLeftFrames(frames);

        spriteAnimation = new Animation<TextureRegion>(0.5f, forwardMove);

    }

    public void initForwardMoveFrames(TextureRegion[] frames) {
        forwardMove = new TextureRegion[2];
        forwardMove[0] = frames[0];
        forwardMove[1] = frames[1];
    }

    @Override
    protected void initTurnRightFrames(TextureRegion[] frames) {
        rightTurnSignal = new TextureRegion[2];
        rightTurnSignal[0] = frames[4];
        rightTurnSignal[1] = frames[5];
    }

    @Override
    protected void initTurnLeftFrames(TextureRegion[] frames) {
        leftTurnSignal = new TextureRegion[2];

        leftTurnSignal[0] = frames[2];
        leftTurnSignal[1] = frames[3];
    }

    protected void turnSignalsRight() {
        if (turnSignals) {
            turnSignals = false;
            stateTime = 0;
            spriteAnimation = new Animation<TextureRegion>(0.5f, forwardMove);
        } else {
            spriteAnimation = new Animation<TextureRegion>(0.5f, rightTurnSignal);
            turnSignals = true;
        }
    }

    protected void turnSignalsLeft() {
        if (turnSignals) {
            turnSignals = false;
            stateTime = 0;
            spriteAnimation = new Animation<TextureRegion>(0.5f, forwardMove);
        } else {
            spriteAnimation = new Animation<TextureRegion>(0.5f, leftTurnSignal);
            turnSignals = true;
        }
    }

    @Override
    void move(int speed) {
        int turnDelta = 2;
        if(!turnSignals) {
            currentFrame = spriteAnimation.getKeyFrame(stateTime, true);
        }

        switch (moveDirection) {
            case TurnLeft:
                turnCarLeft(turnDelta);
                break;
            case TurnRight:
                turnCarRight(turnDelta);
                break;
        }

        switch ((int) angle) {
            case 0:
                y += speed * this.speed;
                if (y > screen_height) {
                    y = 0 - height;
                    hasLeftScreen = true;
                }
                break;
            case 90:
            case -270:
                x -= speed * this.speed;
                if(x < 0 - height) {
                    x = screen_width + height;
                    hasLeftScreen = true;
                }
                break;
            case 180:
            case -180:
                y -= speed * this.speed;
                if(y < 0 - height) {
                    y = screen_height + height;
                    hasLeftScreen = true;
                }
                break;
            case 270:
            case -90:
                x += speed * this.speed;
                if(x > screen_width) {
                    x = 0 - height;
                    hasLeftScreen = true;
                }
                break;
        }
    }

}
