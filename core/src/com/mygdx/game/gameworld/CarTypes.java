package com.mygdx.game.gameworld;

/**
 * Created by the-french-cat on 19/05/17.
 */

enum CarTypes {
    PoliceCar(0, 8, 2),
    SimpleCar(1, 4, 1),
    Bus(2, 4, 1);


    public int type;
    public int numSprites;
    public int speed;

    CarTypes(int t, int n, int s) {
        type = t;
        numSprites = n;
        speed = s;
    }
}
