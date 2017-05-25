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
    static Queue<Car> mCarIntersection;
    static Queue<Car> mNorthCarQueue;
    static Queue<Car> mEastCarQueue;
    static Queue<Car> mSouthCarQueue;
    static Queue<Car> mWestCarQueue;
    static TrafficLight[] trafficLights;
    private Texture mCrossroad;
    private CarFactory mCarFactory;
    private Car mCar;

    public TrafficItems() {
        mCrossroad = new Texture(Gdx.files.internal("crossroad2.png"));
        trafficLights = new TrafficLight[4];
        for (int i = 0; i < 4; i++) {
            trafficLights[i] = new TrafficLight(i);
        }
        trafficLights[0].red();
        trafficLights[2].red();
        trafficLights[1].red();
        trafficLights[3].red();

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
        for (TrafficLight trafficLight : trafficLights) {
            trafficLight.update(delta);
        }
        moveCars(mNorthCarQueue, delta);
        moveCars(mSouthCarQueue, delta);
        moveCars(mWestCarQueue, delta);
        moveCars(mEastCarQueue, delta);
        removeHiddenCars();
    }

    private void removeHiddenCars() {
        if (!mNorthCarQueue.isEmpty() && mNorthCarQueue.element().hasLeftScreen) {
            mNorthCarQueue.remove();
            mCar = mCarFactory.newCar(CarTypes.SimpleCar, -90);
            mNorthCarQueue.add(mCar);
        }

        if (!mSouthCarQueue.isEmpty() && mSouthCarQueue.element().hasLeftScreen) {
            mSouthCarQueue.remove();
            mCar = mCarFactory.newCar(CarTypes.SimpleCar, -270);
            mSouthCarQueue.add(mCar);
        }

        if (!mEastCarQueue.isEmpty() && mEastCarQueue.element().hasLeftScreen) {
            mEastCarQueue.remove();
            mCar = mCarFactory.newCar(CarTypes.SimpleCar, 0);
            mEastCarQueue.add(mCar);
        }

        if (!mWestCarQueue.isEmpty() && mWestCarQueue.element().hasLeftScreen) {
            mWestCarQueue.remove();
            mCar = mCarFactory.newCar(CarTypes.SimpleCar, -180);
            mWestCarQueue.add(mCar);
        }

    }

    private void moveCars(Queue<Car> cars, float delta) {
        for (Car car : cars) {
            if (!car.hasLeftScreen) {
                car.update(delta);
                if (car.canMove) {
                    car.move(1);
                } else {
                    car.stop();
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

    public TrafficLight[] getTrafficLights() {
        return this.trafficLights;
    }
}
