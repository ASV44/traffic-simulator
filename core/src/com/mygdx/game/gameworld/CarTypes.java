package com.mygdx.game.gameworld;

/**
 * Created by the-french-cat on 19/05/17.
 */

enum CarTypes {
    SimpleCar(1, 4, 1),
    PoliceCar(2, 8, 2);

    public int type;
    public int numSprites;
    public int speed;

    CarTypes(int t, int n, int s) {
        type = t;
        numSprites = n;
        speed = s;
    }
}
