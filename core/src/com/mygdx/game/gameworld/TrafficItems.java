package com.mygdx.game.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by hackintosh on 5/16/17.
 */

public class TrafficItems {

    private Texture crossroad;
    private Car sample_car;

    public TrafficItems() {
        crossroad = new Texture(Gdx.files.internal("crossroad2.png"));
        sample_car = new Car(1,4);
        sample_car.turnSignalsRight();
    }

    public void update(float delta) {

        sample_car.update(delta);
        sample_car.move(1);
    }

    public void dispose() {
        crossroad.dispose();
    }

    public Texture getCrossroad() { return this.crossroad; }
    public Car getSample_car() { return this.sample_car; }
}
