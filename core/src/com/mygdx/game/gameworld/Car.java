package com.mygdx.game.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by hackintosh on 5/16/17.
 */

class Car {
    public float turnPoint_y = 0;//0.342f * screen_height;
    float x;
    float y;
    float width;
    float height;
    float angle;
    CarMoveDirection moveDirection;
    boolean hasLeftScreen;
    boolean hasAppearedOnScreen;
    private float speed;
    private Texture skin;
    private Animation<TextureRegion> spriteAnimation;
    private TextureRegion currentFrame;
    private TextureRegion[] frames;
    private TextureRegion[] rightTurnSignal;
    private TextureRegion[] leftTurnSignal;
    private boolean turnSignals;
    private float stateTime;
    private float screen_width;
    public float turnPoint_x = 0.102f * screen_width;
    private float screen_height;
    private float turnRightRadius;
    private float turnLeftRadius;
    private float turningDelta_x;
    private float turningDelta_y;
    private boolean turning;

    Car(int type, int frameNumber, float angle, CarMoveDirection moveDir) {
        hasLeftScreen = false;
        hasAppearedOnScreen = true;
        screen_width = Gdx.graphics.getWidth();
        screen_height = Gdx.graphics.getHeight();
//        x = 0.489f * screen_width;
//        y = 0;
        initPosition(angle);
        width = 0.052f * screen_width;
        height = 0.175f * screen_height;
        this.angle = angle;
        skin = new Texture(Gdx.files.internal("cars/" + type + ".png"));
        frames = new TextureRegion[frameNumber];
        TextureRegion[][] temp_frames;
        temp_frames = TextureRegion.split(skin,skin.getWidth() / frameNumber, skin.getHeight());

        System.arraycopy(temp_frames[0], 0, frames, 0, frameNumber);
        currentFrame = frames[0];

        initTurnRightFrames(frames);
        initTurnLeftFrames(frames);

        turnSignals = false;
        stateTime = 0;

        speed = (float) (0.00462 * screen_height);

        turning = false;

        turnRightRadius = 0.038f * screen_width;
        turnLeftRadius = 0.094f * screen_width;
        turningDelta_x = 0.0f;
        turningDelta_y = 0.0f;

        moveDirection = moveDir;

    }

    private void initPosition(float angle) {
        int direction = (int) angle;

        switch (direction) {
            case 0:
                x = 0.489f * screen_width;
                y = 0 - height;
                break;
            case -90:
                x = 0 - height;
                y = 0.386f * screen_height;
                break;
            case -180:
                x = 0.429f * screen_width;
                y = screen_height + height;
                break;
            case -270:
                x = screen_width + height;
                y = 0.494f * screen_height;
                break;
            default:
                x = 0.489f * screen_width;
                y = 0 - height;
        }
    }

    private void initTurnRightFrames(TextureRegion[] frames) {
        rightTurnSignal = new TextureRegion[2];
        rightTurnSignal[0] = frames[0];
        rightTurnSignal[1] = frames[2];
    }

    private void initTurnLeftFrames(TextureRegion[] frames) {
        leftTurnSignal = new TextureRegion[2];

        leftTurnSignal[0] = frames[0];
        leftTurnSignal[1] = frames[1];
    }

    void update(float delta) {
        stateTime += delta;
        if(turnSignals) {
            currentFrame = spriteAnimation.getKeyFrame(stateTime, true);
        }
    }

    void move(int speed) {
        int turnDelta = 2;
        if(!turnSignals && !currentFrame.equals(frames[0])) {
            currentFrame = frames[0];
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

    private void turnCarLeft(int turnDelta) {
        if ((y >= 0.421f * screen_height - turnDelta && y <= 0.421f * screen_height + turnDelta && angle == 0f)
                || (x >= 0.45f * screen_width - turnDelta && x <= 0.45f * screen_width + turnDelta && angle == -90f)
                || (y <= 0.445f * screen_height + turnDelta && y >= 0.445f * screen_height - turnDelta && angle == -180f)
                || (x <= 0.469f * screen_width + turnDelta && x >= 0.469f * screen_width - turnDelta && angle == -270f)
                || turning) {
            if (!turning) {
                turning = true;
                turningDelta_x = 0.0f;
                turningDelta_y = 0.0f;
            }
            turnLeft();
        }
    }

    private void turnCarRight(int turnDelta) {
        if ((y >= 0.319f * screen_height - turnDelta && y <= 0.319f * screen_height + turnDelta && angle == 0f)
                || (x >= 0.394f * screen_width - turnDelta && x <= 0.394f * screen_width + turnDelta && angle == -90f)
                || (y <= 0.556f * screen_height + turnDelta && y >= 0.556f * screen_height - turnDelta && angle == -180f)
                || (x <= 0.53f * screen_width + turnDelta && x >= 0.53f * screen_width - turnDelta && angle == -270f)
                || turning) {
            if (!turning) {
                turning = true;
                turningDelta_x = 0.0f;
                turningDelta_y = 0.0f;
            }
            turnRight();
        }
    }

    private void turnRight() {
        if(angle <= 0f && angle > -90f) {
            angle -= 3;
            if (angle <= -90f) {
                angle = -90f;
                turning = false;
            }
            x -= turningDelta_x;
            y -= turningDelta_y;
            turningDelta_x = turnRightRadius - turnRightRadius * (float) Math.cos(Math.toRadians(-angle));
            turningDelta_y = turnRightRadius * (float) Math.sin(Math.toRadians(-angle));
            x += turningDelta_x;
            y += turningDelta_y;
        }
        else if(angle <= -90f && angle > -180f) {
            angle -= 3;
            if(angle <= -180f) {
                angle = -180f;
                turning = false;
            }
            x -= turningDelta_x;
            y += turningDelta_y;
            turningDelta_x = turnRightRadius - turnRightRadius * (float) Math.cos(Math.toRadians(-angle - 90));
            turningDelta_y = turnRightRadius * (float) Math.sin(Math.toRadians(-angle - 90));
            x += turningDelta_x;
            y -= turningDelta_y;
        }
        else if(angle <= -180f && angle > -270f) {
            angle -= 3;
            if(angle <= -270f) {
                angle = -270f;
                turning = false;
            }
            x += turningDelta_x;
            y += turningDelta_y;
            turningDelta_x = turnRightRadius - turnRightRadius * (float) Math.cos(Math.toRadians(-angle - 180));
            turningDelta_y = turnRightRadius * (float) Math.sin(Math.toRadians(-angle - 180));
            x -= turningDelta_x;
            y -= turningDelta_y;
        }
        else if(angle <= -270f && angle > -360f) {
            angle -= 3;
            if(angle <= -360f) {
                angle = 0f;
                turning = false;
            }
            x += turningDelta_x;
            y -= turningDelta_y;
            turningDelta_x = turnRightRadius - turnRightRadius * (float) Math.cos(Math.toRadians(-angle - 270));
            turningDelta_y = turnRightRadius * (float) Math.sin(Math.toRadians(-angle - 270));
            x -= turningDelta_x;
            y += turningDelta_y;
        }
        Gdx.app.log("Angle","" + angle);
    }

    private void turnLeft() {
        if(angle >= 0f && angle < 90f) {
            angle += 3;
            if (angle >= 90f) {
                angle = -270f;
                turning = false;
            }
            x += turningDelta_x;
            y -= turningDelta_y;
            turningDelta_x = turnLeftRadius - turnLeftRadius * (float) Math.cos(Math.toRadians(angle));
            turningDelta_y = turnRightRadius * (float) Math.sin(Math.toRadians(angle));
            x -= turningDelta_x;
            y += turningDelta_y;
        }
        else if(angle >= -270f && angle < -180f) {
            angle += 3;
            if(angle >= -180f) {
                angle = -180f;
                turning = false;
            }
            x += turningDelta_x;
            y += turningDelta_y;
            turningDelta_x = turnRightRadius - turnRightRadius * (float) Math.cos(Math.toRadians(270f + angle));
            turningDelta_y = turnRightRadius * (float) Math.sin(Math.toRadians(270 + angle));
            x -= turningDelta_x;
            y -= turningDelta_y;
        }
        else if(angle >= -180f && angle < -90f) {
            angle += 3;
            if(angle >= -90f) {
                angle = -90f;
                turning = false;
            }
            x -= turningDelta_x;
            y += turningDelta_y;
            turningDelta_x = turnRightRadius - turnRightRadius * (float) Math.cos(Math.toRadians(180 + angle));
            turningDelta_y = turnRightRadius * (float) Math.sin(Math.toRadians(180 + angle));
            x += turningDelta_x;
            y -= turningDelta_y;
        }
        else if(angle >= -90f && angle < 0f) {
            angle += 3;
            if(angle >= 0f) {
                angle = 0f;
                turning = false;
            }
            x -= turningDelta_x;
            y -= turningDelta_y;
            turningDelta_x = turnRightRadius - turnRightRadius * (float) Math.cos(Math.toRadians(90 + angle));
            turningDelta_y = turnRightRadius * (float) Math.sin(Math.toRadians(90 + angle));
            x += turningDelta_x;
            y += turningDelta_y;
        }
        Gdx.app.log("Angle","" + angle);
    }

    void stop() {
        if(!turnSignals) {
            currentFrame = frames[3];
        }
    }

    void turnSignalsRight() {
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

    void turnSignalsLeft() {
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

    TextureRegion getCurrentFrame() {
        return this.currentFrame;
    }

    boolean canMove() {
        return true;
    }
}
