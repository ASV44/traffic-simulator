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
        //Gdx.gl.glClearColor(255, 255, 255, 0);
        Gdx.gl.glClearColor(0.36f, 0.45f, 0.043f, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        //batch.draw(items.getCrossroad(), 0, 0, items.getCrossroad().getWidth() * k, screenHeight);
        batch.draw(items.getCrossroad(), 0, 0, screenWidth, screenHeight);
//        batch.draw(items.getCars().get(i).getCurrentFrame(),items.getCars().get(i).x,items.getCars().get(i).y,
//                items.getCars().get(i).width, items.getCars().get(i).height);
        for (int i = 0; i < items.getCars().size(); i++) {
            batch.draw(items.getCars().get(i).getCurrentFrame(), items.getCars().get(i).x, items.getCars().get(i).y,
                    items.getCars().get(i).width / 2, items.getCars().get(i).height / 2,
                    items.getCars().get(i).width, items.getCars().get(i).height, 1, 1, items.getCars().get(i).angle);

            Gdx.app.log("Sample_Car_X", "" + items.getCars().get(i).x);
            Gdx.app.log("Sample_Car_Y", "" + items.getCars().get(i).y);
        }
        for (TrafficLight trafficLight : items.getTrafficLights()) {
            batch.draw(trafficLight.currentFrame, trafficLight.x, trafficLight.y,
                    trafficLight.width / 2, trafficLight.height / 2,
                    trafficLight.width, trafficLight.height, 1, 1, trafficLight.angle);
        }
//        batch.draw(items.getCars().get(i).getCurrentFrame(), items.getCars().get(i).x,items.getCars().get(i).y,
//                items.getCars().get(i).turnPoint_x, items.getCars().get(i).turnPoint_y,
//                items.getCars().get(i).width, items.getCars().get(i).height, 1, 1, items.getCars().get(i).angle);
        batch.end();

    }

    public void dispose() {
        batch.dispose();
    }
}
