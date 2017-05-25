package com.mygdx.game.gameworld;

/**
 * Created by the-french-cat on 25/05/17.
 */

public enum HawkTypes {
    SmallBlackHawk(1, 0.25f),
    BlackHawk(1, 0.5f),
    BigBlackHawk(1, 1f),
    SmallRedHawk(2, 0.25f),
    RedHawk(2, 0.5f),
    BigRedHawk(2, 1f);

    int skinId;
    float scale;

    HawkTypes(int i, float s) {
        skinId = i;
        scale = s;
    }
}
