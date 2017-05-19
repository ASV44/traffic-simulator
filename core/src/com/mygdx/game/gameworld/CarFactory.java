package com.mygdx.game.gameworld;

import java.util.Random;

/**
 * Created by the-french-cat on 19/05/17.
 */

public class CarFactory {
    private Random mRandGen;
    CarFactory() {
        mRandGen = new Random();
    }

    protected Car newCar(CarTypes carType) {
        float initAngle = getInitAngle();
        CarMoveDirection carMoveDirection = getMoveDirection();

        return new Car(carType.type, carType.numSprites);
    }

    private CarMoveDirection getMoveDirection() {
        int random = mRandGen.nextInt(CarMoveDirection.values().length);
        return (CarMoveDirection.values())[random];
    }

    private float getInitAngle() {
        float angle = 0;
        switch (mRandGen.nextInt(4)) {
            case 0:
                angle = 0f;
                break;
            case 1:
                angle = -90f;
                break;
            case 2:
                angle = -180;
                break;
            case 3:
                angle = -270;
                break;
        }
        return angle;
    }
}
