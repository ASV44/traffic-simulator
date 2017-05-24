package com.mygdx.game.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by hackintosh on 5/16/17.
 */

public class TrafficItems {
    private Texture mCrossroad;
    private CarFactory mCarFactory;

    private Queue<Car> mCarIntersection;
    private Queue<Car> mNorthCarQueue;
    private Queue<Car> mEastCarQueue;
    private Queue<Car> mSouthCarQueue;
    private Queue<Car> mWestCarQueue;
    private Car mCar;
    private TrafficLight[] trafficLights;

    public TrafficItems() {
        mCrossroad = new Texture(Gdx.files.internal("crossroad2.png"));
        trafficLights = new TrafficLight[4];
        for(int i = 0; i < 4 ; i++) {
            trafficLights[i] = new TrafficLight(i);
            trafficLights[i].flashingYellow();
        }
        mCarFactory = new CarFactory();
        mNorthCarQueue = new LinkedList<Car>();
        mSouthCarQueue = new LinkedList<Car>();
        mEastCarQueue = new LinkedList<Car>();
        mWestCarQueue = new LinkedList<Car>();

        mCar = mCarFactory.newCar(CarTypes.SimpleCar, -90);
        mNorthCarQueue.add(mCar);
        mCar = mCarFactory.newCar(CarTypes.SimpleCar, -270);
        mSouthCarQueue.add(mCar);

    }

    public void update(float delta) {

        moveCars(mNorthCarQueue, delta);
        moveCars(mSouthCarQueue, delta);
        removeHiddenCars(mNorthCarQueue);
        removeHiddenCars(mSouthCarQueue);
        for(TrafficLight trafficLight : trafficLights) {
            trafficLight.update(delta);
        }
    }

    private void removeHiddenCars(Queue<Car> cars) {
        if (!cars.isEmpty() && cars.element().hasLeftScreen)
            cars.remove();
    }

    private void moveCars(Queue<Car> cars, float delta) {
        for (Car car : cars) {
            if (!car.hasLeftScreen) {
                car.update(delta);
                if (car.canMove()) {
                    car.move(1);
                }
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
        List<Car> tmp = new LinkedList<Car>();
        tmp.addAll(mNorthCarQueue);
        tmp.addAll(mSouthCarQueue);
        tmp.addAll(mEastCarQueue);
        tmp.addAll(mWestCarQueue);
        return tmp;
    }

    public  TrafficLight[] getTrafficLights() {return this.trafficLights; }
}
