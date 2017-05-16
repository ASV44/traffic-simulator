package com.mygdx.game.screens;

import com.badlogic.gdx.Screen;
import com.mygdx.game.gameworld.TrafficItems;
import com.mygdx.game.gameworld.TrafficRenderer;

/**
 * Created by hackintosh on 5/16/17.
 */

public class TrafficScreen implements Screen {
    private TrafficItems items;
    private TrafficRenderer renderer;

    public TrafficScreen() {
        items = new TrafficItems();
        renderer = new TrafficRenderer(items);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        items.update(delta);
        renderer.render();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
