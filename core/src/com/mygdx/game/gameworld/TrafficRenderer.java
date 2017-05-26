package com.mygdx.game.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.List;

/**
 * Created by hackintosh on 5/16/17.
 */

public class TrafficRenderer {

    /*public fields*/

    /*private fields*/
    private TrafficItems items;
    private SpriteBatch batch;
    private float screenWidth;
    private float screenHeight;

    /*constructors*/
    public TrafficRenderer(TrafficItems items) {
        this.items = items;
        batch = new SpriteBatch();
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
    }

    /*private methods*/
    public void render() {
        Hawk hawk = items.getHawk();
        List<Car> cars = items.getCars();
        Gdx.gl.glClearColor(0.36f, 0.45f, 0.043f, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(items.getCrossroad(), 0, 0, screenWidth, screenHeight);
        for (int i = 0; i < cars.size(); i++) {
            batch.draw(cars.get(i).getCurrentFrame(), cars.get(i).x, cars.get(i).y,
                    cars.get(i).width / 2, cars.get(i).height / 2, cars.get(i).width,
                    cars.get(i).height, 1, 1, cars.get(i).angle);
        }
        batch.draw(items.policeCar.getCurrentFrame(), items.policeCar.x, items.policeCar.y,
                items.policeCar.width / 2, items.policeCar.height / 2, items.policeCar.width,
                items.policeCar.height, 1, 1, items.policeCar.angle);
        for (TrafficLight tl : items.getTrafficLights()) {
            batch.draw(tl.currentFrame, tl.x, tl.y, tl.width / 2, tl.height / 2, tl.width,
                    tl.height, 1, 1, tl.angle);
        }
        batch.draw(hawk.texture, hawk.x, hawk.y);
        batch.end();

    }

    public void dispose() {
        batch.dispose();
    }
    /*private methods*/
}
