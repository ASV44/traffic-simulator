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
        //Gdx.gl.glClearColor(255, 255, 255, 0);
        Gdx.gl.glClearColor(0.36f, 0.45f, 0.043f, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        //batch.draw(items.getCrossroad(), 0, 0, items.getCrossroad().getWidth() * k, screenHeight);
        batch.draw(items.getCrossroad(), 0, 0, screenWidth, screenHeight);
//        batch.draw(items.getSample_car().getCurrentFrame(),items.getSample_car().x,items.getSample_car().y,
//                items.getSample_car().width, items.getSample_car().height);
        batch.draw(items.getSample_car().getCurrentFrame(), items.getSample_car().x,items.getSample_car().y,
                items.getSample_car().width / 2, items.getSample_car().height / 2,
                items.getSample_car().width, items.getSample_car().height, 1, 1, items.getSample_car().angle);
        Gdx.app.log("Sample_Car_X","" + items.getSample_car().x);
        Gdx.app.log("Sample_Car_Y","" + items.getSample_car().y);
//        batch.draw(items.getSample_car().getCurrentFrame(), items.getSample_car().x,items.getSample_car().y,
//                items.getSample_car().turnPoint_x, items.getSample_car().turnPoint_y,
//                items.getSample_car().width, items.getSample_car().height, 1, 1, items.getSample_car().angle);
        batch.end();

    }

    public void dispose() {
        batch.dispose();
    }
}
