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
    private TrafficLight[] trafficLights;

    public TrafficItems() {
        mCrossroad = new Texture(Gdx.files.internal("crossroad2.png"));
        trafficLights = new TrafficLight[4];
        for(int i = 0; i < 4 ; i++) {
            trafficLights[i] = new TrafficLight(i);
            trafficLights[i].flashingYellow();
        }
        mCarFactory = new CarFactory();
        mCars = new ArrayList<Car>();
        mCars.add(mCarFactory.newCar(CarTypes.SimpleCar));
        mCars.get(0).turnSignalsLeft();
    }

    public void update(float delta) {
        for(TrafficLight trafficLight : trafficLights) {
            trafficLight.update(delta);
        }
        for (Car car : mCars) {
            car.update(delta);
            car.move(1);
        }
    }

    public void dispose() {
        mCrossroad.dispose();
    }

    public Texture getCrossroad() {
        return this.mCrossroad;
    }

    public Car getSample_car() {
        return this.mCars.get(0);
    }

    public List<Car> getCars() {
        return this.mCars;
    }

    public  TrafficLight[] getTrafficLights() {return this.trafficLights; }
}
