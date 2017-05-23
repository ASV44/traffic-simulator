package com.mygdx.game.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hackintosh on 5/16/17.
 */

public class TrafficItems {

    private Texture mCrossroad;
    private List<Car> mCars;
    private CarFactory mCarFactory;

    public TrafficItems() {

        mCrossroad = new Texture(Gdx.files.internal("crossroad2.png"));
        mCarFactory = new CarFactory();
        mCars = new ArrayList<Car>();
        mCars.add(mCarFactory.newCar(CarTypes.SimpleCar));
        mCars.add(mCarFactory.newCar(CarTypes.SimpleCar));
        mCars.add(mCarFactory.newCar(CarTypes.SimpleCar));
    }

    public void update(float delta) {
        for (Car car : mCars) {
            car.update(delta);
            if (car.canMove()) {
                car.move(1);
            }
        }
    }

    public void dispose() {
        mCrossroad.dispose();
    }

    Texture getCrossroad() {
        return this.mCrossroad;
    }

    List<Car> getCars() {
        return this.mCars;
    }
}
