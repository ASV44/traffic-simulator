package com.mygdx.game.gameworld;

/**
 * Created by the-french-cat on 19/05/17.
 */

public class CarFactory {
    CarFactory() {

    }

    protected Car newCar(CarTypes carType) {
        return new Car(carType.type, carType.numSprites);
    }
}
