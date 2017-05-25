package com.mygdx.game.gameworld;

/**
 * Created by strongheart on 5/25/17.
 */

public enum PersonTypes {
    Person1 (1,1),
    Person2 (2,1),
    Person3 (3,1),
    Person4 (4,1);

    public int type;
    public int speed;

    PersonTypes(int t, int s) {
        type = t;
        speed = s;
    }
}
