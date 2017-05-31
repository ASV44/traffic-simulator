package com.mygdx.game.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

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

    static List<Person> NorthPersonList;
    static List<Person> EastPersonList;
    static List<Person> SouthPersonList;
    static List<Person> WestPersonList;
    static List<Person> PersonList;
    private PersonFactory mPersonFactory;
    private Person mPerson;

    private List<Hawk> mHawks;
    private HawkFactory mHawkFactory;

    public PoliceCar policeCar;


    /*constructors*/
    public TrafficItems() {
        mCrossroad = new Texture(Gdx.files.internal("crossroad2.png"));
        mHawkFactory = new HawkFactory();
        mHawks = new ArrayList<Hawk>();
        mHawks.add(mHawkFactory.newHawk(HawkTypes.RedHawk, 3, 40));
        mHawks.add(mHawkFactory.newHawk(HawkTypes.BigBlackHawk, 5, 50));
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

        mCar = mCarFactory.newCar(CarTypes.SimpleCar, 0);
        WestCarQueue.add(mCar);
        mCar = mCarFactory.newCar(CarTypes.SimpleCar, -90);
        NorthCarQueue.add(mCar);
        mCar = mCarFactory.newCar(CarTypes.SimpleCar, -180);
        EastCarQueue.add(mCar);
        mCar = mCarFactory.newCar(CarTypes.SimpleCar, -270);
        SouthCarQueue.add(mCar);


        mPersonFactory = new PersonFactory();
        NorthPersonList = new ArrayList<Person>();
        EastPersonList = new ArrayList<Person>();
        SouthPersonList = new ArrayList<Person>();
        WestPersonList = new ArrayList<Person>();

        mPerson = mPersonFactory.newPerson(PersonTypes.Person1, 0);
        EastPersonList.add(mPerson);
        mPerson = mPersonFactory.newPerson(PersonTypes.Person2,-90);
        SouthPersonList.add(mPerson);
        mPerson = mPersonFactory.newPerson(PersonTypes.Person3, -180);
        WestPersonList.add(mPerson);
        mPerson = mPersonFactory.newPerson(PersonTypes.Person4,-270);
        NorthPersonList.add(mPerson);

        //policeCar = new PoliceCar(0,CarMoveDirection.MoveForward);

    }

    /*public methods*/
    public void update(float delta) {
        updateTrafficLights(delta);
        updateHawks(delta);
        moveCars(NorthCarQueue, delta);
        moveCars(SouthCarQueue, delta);
        moveCars(WestCarQueue, delta);
        moveCars(EastCarQueue, delta);
        moveCars(FreeCarsQueue, delta);
        removeHiddenCars();

        movePersons(NorthPersonList, delta);
        movePersons(EastPersonList, delta);
        movePersons(SouthPersonList, delta);
        movePersons(WestPersonList, delta);
        removeHiddenPersons(NorthPersonList, -270);
        removeHiddenPersons(EastPersonList, 0);
        removeHiddenPersons(SouthPersonList, -90);
        removeHiddenPersons(WestPersonList, -180);

        //policeCar.update(delta);
        //policeCar.move(1);
    }

    private void updateHawks(float delta) {
        for (Hawk hawk : mHawks) hawk.update(delta);
    }

    public void dispose() {
        mCrossroad.dispose();
        for (Hawk hawk : mHawks) {
            hawk.dispose();
        }
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

    public List<Person> getPersons() {
        List<Person> tmp = new ArrayList<Person>();
        tmp.addAll(NorthPersonList);
        tmp.addAll(EastPersonList);
        tmp.addAll(SouthPersonList);
        tmp.addAll(WestPersonList);
        return tmp;
    }

    public TrafficLight[] getTrafficLights() {
        return this.TrafficLights;
    }

    public List<Hawk> getHawks() {
        return this.mHawks;
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
            mCar = mCarFactory.newCar(CarTypes.SimpleCar, -180);
            EastCarQueue.add(mCar);
        }

        if (!WestCarQueue.isEmpty() && WestCarQueue.element().hasLeftScreen) {
            WestCarQueue.remove();
            mCar = mCarFactory.newCar(CarTypes.SimpleCar, 0);
            WestCarQueue.add(mCar);
        }

    }

    private void moveCars(Queue<Car> cars, float delta) {
        for (Car car : cars) updateCarPosition(car, delta);
    }

    private void updateCarPosition(Car car, float delta) {
        if (car.getInIntersection()) {
            Gdx.app.log("inIntersection: ", "true");
        } else {
            Gdx.app.log("inIntersection: ", "false");
        }
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

    private void movePersons(List<Person> persons,float delta) {
        for (Person person : persons) {
            if (!person.hasLeftScreen) {
                person.update(delta);
                if (person.canMove) {
                    person.move(1);
                } else {
                    person.stop();
                }
            }
        }
    }

    private void removeHiddenPersons(List<Person> persons, int angle) {
        List<Integer> indexToRemove = new ArrayList<Integer>();
        Random mRandGen = new Random();
        int rand = mRandGen.nextInt(4);
        for(int i = 0; i < persons.size(); i++) {
            if( persons.get(i).hasLeftScreen) {
                indexToRemove.add(i);
            }
        }
        for(int i: indexToRemove) {
            Gdx.app.log("indextoRemove","" + i);
             persons.remove(i);
            for(int j = 0; j < persons.size(); j++) {
                Gdx.app.log("afterRemoving", "" + persons.get(j));
            }
            switch (rand) {
                case 0:
                    mPerson = mPersonFactory.newPerson(PersonTypes.Person1, angle);
                    break;
                case 1:
                    mPerson = mPersonFactory.newPerson(PersonTypes.Person2, angle);
                    break;
                case 2:
                    mPerson = mPersonFactory.newPerson(PersonTypes.Person3, angle);
                    break;
                case 3:
                    mPerson = mPersonFactory.newPerson(PersonTypes.Person4, angle);
                    break;
                default:
                    mPerson = mPersonFactory.newPerson(PersonTypes.Person4, angle);
            }
            Gdx.app.log("rand","" + rand);
            persons.add(mPerson);
            for(int j = 0; j < persons.size(); j++) {
                Gdx.app.log("afterAdding", "" + persons.get(j).getCurrentFrame());
            }
        }
    }
}
