package com.mygdx.game.gameworld;

import com.badlogic.gdx.Gdx;

import java.util.Random;

/**
 * Created by strongheart on 5/25/17.
 */

class PersonFactory {
    private Random mRandGen;
    private float initAngle;

    PersonFactory() { mRandGen = new Random(); }

    Person newPerson(PersonTypes personType) {
        initAngle = calcInitAngle();
        int initSide = mRandGen.nextInt(2);

        return new Person(personType.type, initAngle, initSide);
    }

    Person newPerson(PersonTypes personType, float initAngle) {
        int initSide = mRandGen.nextInt(2);

        return new Person(personType.type, initAngle, initSide);
    }

    private float calcInitAngle() {
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
                angle = -180f;
                break;
            case 3:
                angle = -270f;
                break;
        }
        return angle;
    }

    public float getInitAngle() {
        return initAngle;
    }
}
