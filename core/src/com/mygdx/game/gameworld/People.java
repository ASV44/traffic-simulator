package com.mygdx.game.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by hackintosh on 5/25/17.
 */

public class People {
    float x;
    float y;
    float width;
    float height;
    float angle;

    private Texture skin;
    private Animation<TextureRegion> spriteAnimation;
    private TextureRegion currentFrame;
    private TextureRegion[] frames;

    private float stateTime;
    private float screen_width;
    private float screen_height;

    public People(int type, int intersection, int direction) {
        screen_width = Gdx.graphics.getWidth();
        screen_height = Gdx.graphics.getHeight();
        width = 0.017f * screen_width;
        height = 0.087f * screen_height;
        skin = new Texture(Gdx.files.internal("people/" + type + ".png"));
        frames = new TextureRegion[4];
        TextureRegion[][] temp_frames;
        temp_frames = TextureRegion.split(skin, skin.getWidth() / 4, skin.getHeight());
        for (int i = 0; i < 4; i++) {
            frames[i] = temp_frames[0][i];
        }

        stateTime = 0;


    }

    private void initPosition(int intersection, int direction) {

    }


}
