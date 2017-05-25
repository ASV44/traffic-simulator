package com.mygdx.game.gameworld;

import java.util.Random;

/**
 * Created by the-french-cat on 25/05/17.
 */

public class HawkFactory {
    /*public fields*/

    /*private fields*/
    Random mRandom;

    /*constructors*/
    HawkFactory() {

    }

    /*public methods*/
    Hawk newHawk(HawkTypes hawkType, int initDelay, int delay) {
        float xCoeff = 300;
        float yCoeff = 200;
        return new Hawk(hawkType.skinId, hawkType.scale, xCoeff, yCoeff, initDelay, delay);
    }
    /*private methods*/
}
