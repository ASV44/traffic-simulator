package com.mygdx.game.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by hackintosh on 5/16/17.
 */

public class TrafficRenderer {

    private TrafficItems items;
    private SpriteBatch batch;
    private float screenWidth;
    private float screenHeight;

    float sample_y = 0;

    public TrafficRenderer(TrafficItems items) {
        this.items = items;
        batch = new SpriteBatch();
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
    }

    public void render() {

//        Gdx.app.log("ScreenWidth","" + screenWidth);
//        Gdx.app.log("ScreenHeight","" + screenHeight);
        float k = screenHeight / items.getCrossroad().getHeight();
        Gdx.gl.glClearColor(255, 255, 255, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(items.getCrossroad(), 0, 0, items.getCrossroad().getWidth() * k, screenHeight);
        batch.draw(items.getSample_car().getCurrentFrame(),(float)(items.getCrossroad().getWidth() * k * 0.522),sample_y,
                (float)(items.getCrossroad().getWidth() * 0.14),
                (float)(items.getCrossroad().getHeight() * 0.3));
        batch.end();

        sample_y += 5;
        if(sample_y > screenHeight) {
            sample_y = 0;
        }
    }

    public void dispose() {
        batch.dispose();
    }
}
