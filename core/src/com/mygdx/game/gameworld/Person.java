package com.mygdx.game.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by hackintosh on 5/25/17.
 */

class Person {
    float x;
    float y;
    float width;
    float height;
    float angle;
    int side;
    //int moveDirection;

    boolean hasLeftScreen;
    boolean hasAppearedOnScreen;

    private float speed;
    private Texture skin;
    private Animation<TextureRegion> spriteAnimation;
    private TextureRegion currentFrame;
    private TextureRegion[] frames;

    private float stateTime;
    private float screen_width;
    private float screen_height;

    Person(int type, float angle, int side) {
        hasLeftScreen = false;
        hasAppearedOnScreen = true;
        screen_width = Gdx.graphics.getWidth();
        screen_height = Gdx.graphics.getHeight();
        initPosition(angle, side);
        this.angle = angle;
        this.side = side;
        width = 0.035f * screen_width;
        height = 0.1f * screen_height;


        skin = new Texture(Gdx.files.internal("people/" + type + ".png"));
        frames = new TextureRegion[4];
        TextureRegion[][] temp_frames;
        temp_frames = TextureRegion.split(skin, skin.getWidth() / 4, skin.getHeight());

        System.arraycopy(temp_frames[0], 0, frames, 0, 4);

        spriteAnimation = new Animation<TextureRegion>(0.2f, frames);

        stateTime = 0;

        speed = (float) (0.0015 * screen_height);

        currentFrame = frames[0];

        //moveDirection = direction;

    }

    private void initPosition(float angle, int side) {
        int direction = (int) angle;

        switch (direction) {
            case 0:
                switch (side){
                    case 0:
                        x = 0.39f * screen_width;
                        y = screen_height + height;
                        break;
                    case 1:
                        x = 0.55f * screen_width;
                        y = screen_height + height;
                        break;
                    default:
                        x = 0.55f * screen_width;
                        y = screen_height + height;
                }
                break;
            case -90:
                switch (side){
                    case 0:
                        x = screen_width + height;
                        y = 0.33f * screen_height;
                        break;
                    case 1:
                        x = screen_width + height;
                        y = 0.62f * screen_height;
                        break;
                    default:
                        x = screen_width + height;
                        y = 0.62f * screen_height;
                }
                break;
            case -180:
                switch (side) {
                    case 0:
                        x = 0.39f * screen_width;
                        y = 0 - height;
                        break;
                    case 1:
                        x = 0.55f * screen_width;
                        y = 0 - height;
                        break;
                    default:
                        x = 0.55f * screen_width;
                        y = 0 - height;
                }
                break;
            case -270:
                switch (side){
                    case 0:
                        x = 0 - height;
                        y = 0.34f * screen_height;
                        break;
                    case 1:
                        x = 0 - height;
                        y = 0.61f * screen_height;
                        break;
                    default:
                        x = 0 - height;
                        y = 0.61f * screen_height;
                }
                break;
            default:
//                switch (side){
//                    case 0:
//                        x = 0.4f * screen_width;
//                        y = screen_height + height;
//                        break;
//                    case 1:
//                        x = 0.55f * screen_width;
//                        y = screen_height + height;
//                        break;
//                    default:
//                        x = 0.55f * screen_width;
//                        y = screen_height + height;
//                }
                break;
        }
    }

    void update(float delta) {
        stateTime += delta;
        currentFrame = spriteAnimation.getKeyFrame(stateTime, true);
    }

    void move(int speed) {
//        int turnDelta = 2;
//        if(!turnSignals && !currentFrame.equals(frames[0])) {
//            currentFrame = frames[0];
//        }

//        switch (moveDirection) {
//            case TurnLeft:
//                turnCarLeft(turnDelta);
//                break;
//            case TurnRight:
//                turnCarRight(turnDelta);
//                break;
//        }

        switch ((int) angle) {
            case 0:
                y -= speed * this.speed;
                if(y < 0 - height) {
                    y = screen_height + height;
                    hasLeftScreen = true;
                }
                break;
            case 90:
            case -270:
                x += speed * this.speed;
                if(x > screen_width) {
                    x = 0 - height;
                    hasLeftScreen = true;
                }

                break;
            case 180:
            case -180:
                y += speed * this.speed;
                if (y > screen_height) {
                    y = 0 - height;
                    hasLeftScreen = true;
                }
                break;
            case 270:
            case -90:
                x -= speed * this.speed;
                if(x < 0 - height) {
                    x = screen_width + height;
                    hasLeftScreen = true;
                }
                break;
        }
    }

    TextureRegion getCurrentFrame() {
        return this.currentFrame;
    }


}
