package com.mygdx.game.gameworld;

import com.badlogic.gdx.Gdx;

import java.util.Random;

/**
 * Created by the-french-cat on 19/05/17.
 */

class CarFactory {
    private Random mRandGen;
    CarFactory() {
        mRandGen = new Random();
    }

    Car newCar(CarTypes carType) {
        float initAngle = getInitAngle();
        CarMoveDirection carMoveDirection = getMoveDirection();

        return new Car(carType.type, carType.numSprites, initAngle, carMoveDirection);
    }

    private CarMoveDirection getMoveDirection() {
        int random = mRandGen.nextInt(CarMoveDirection.values().length);
        return (CarMoveDirection.values())[random];
    }

    private float getInitAngle() {
        float angle = 0;
        int rand = mRandGen.nextInt(4);
        Gdx.app.log("Rand ", "" + rand);
        switch (rand) {
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
