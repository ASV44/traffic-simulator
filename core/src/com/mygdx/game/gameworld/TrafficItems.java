package com.mygdx.game.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by hackintosh on 5/16/17.
 */

public class TrafficItems {

    /*public and package-private fields*/
    static Queue<Car> CarIntersection;
    static Queue<Car> NorthCarQueue;
    static Queue<Car> EastCarQueue;
    static Queue<Car> SouthCarQueue;
    static Queue<Car> WestCarQueue;
    static TrafficLight[] TrafficLights;
    static List<Car> FreeCarsQueue;

    /*private fields*/
    private Texture mCrossroad;
    private CarFactory mCarFactory;
    private Car mCar;
    private Hawk mHawk;

    /*constructors*/
    public TrafficItems() {
        mCrossroad = new Texture(Gdx.files.internal("crossroad2.png"));
        mHawk = new Hawk();
        TrafficLights = new TrafficLight[4];
        for (int i = 0; i < 4; i++) {
            TrafficLights[i] = new TrafficLight(i);
        }
        TrafficLights[0].red();
        TrafficLights[2].red();
        TrafficLights[1].green();
        TrafficLights[3].green();


        mCarFactory = new CarFactory();
        NorthCarQueue = new LinkedList<Car>();
        SouthCarQueue = new LinkedList<Car>();
        EastCarQueue = new LinkedList<Car>();
        WestCarQueue = new LinkedList<Car>();
        FreeCarsQueue = new ArrayList<Car>();

        mCar = mCarFactory.newCar(CarTypes.SimpleCar, -90);
        NorthCarQueue.add(mCar);
        mCar = mCarFactory.newCar(CarTypes.SimpleCar, -270);
        SouthCarQueue.add(mCar);
    }

    /*public methods*/
    public void update(float delta) {
        updateTrafficLights(delta);
        mHawk.update(delta);
        moveCars(NorthCarQueue, delta);
        moveCars(SouthCarQueue, delta);
        moveCars(WestCarQueue, delta);
        moveCars(EastCarQueue, delta);
        moveCars(FreeCarsQueue, delta);
        removeHiddenCars();
    }

    public void dispose() {
        mCrossroad.dispose();
        mHawk.dispose();
    }

    public Texture getCrossroad() {
        return this.mCrossroad;
    }

    public List<Car> getCars() {
        List<Car> tmp = new LinkedList<Car>();
        tmp.addAll(NorthCarQueue);
        tmp.addAll(SouthCarQueue);
        tmp.addAll(EastCarQueue);
        tmp.addAll(WestCarQueue);
        tmp.addAll(FreeCarsQueue);
        return tmp;
    }

    public TrafficLight[] getTrafficLights() {
        return this.TrafficLights;
    }

    public Hawk getHawk() {
        return this.mHawk;
    }

    /*private methods*/
    private void updateTrafficLights(float delta) {
        for (TrafficLight trafficLight : TrafficLights) {
            trafficLight.update(delta);
        }
    }

    private void removeHiddenCars() {
        if (!NorthCarQueue.isEmpty() && NorthCarQueue.element().hasLeftScreen) {
            NorthCarQueue.remove();
            mCar = mCarFactory.newCar(CarTypes.SimpleCar, -90);
            NorthCarQueue.add(mCar);
        }

        if (!SouthCarQueue.isEmpty() && SouthCarQueue.element().hasLeftScreen) {
            SouthCarQueue.remove();
            mCar = mCarFactory.newCar(CarTypes.SimpleCar, -270);
            SouthCarQueue.add(mCar);
        }

        if (!EastCarQueue.isEmpty() && EastCarQueue.element().hasLeftScreen) {
            EastCarQueue.remove();
            mCar = mCarFactory.newCar(CarTypes.SimpleCar, 0);
            EastCarQueue.add(mCar);
        }

        if (!WestCarQueue.isEmpty() && WestCarQueue.element().hasLeftScreen) {
            WestCarQueue.remove();
            mCar = mCarFactory.newCar(CarTypes.SimpleCar, -180);
            WestCarQueue.add(mCar);
        }

    }

    private void moveCars(Queue<Car> cars, float delta) {
        for (Car car : cars) updateCarPosition(car, delta);
    }

    private void updateCarPosition(Car car, float delta) {
        if (!car.hasLeftScreen) {
            car.update(delta);
            if (car.canMove) {
                car.move(1);
            } else {
                car.stop();
            }
        }
    }

    private void moveCars(List<Car> cars, float delta) {
        for (Car car : cars) updateCarPosition(car, delta);
    }

}
