package net.indiearmory.evolsim.gameworld;

import com.badlogic.gdx.graphics.Color;

/**
 * Created by niklas on 21.10.17.
 * Copyright (c) 2017 IndieArmory
 */

public class Food extends GameModel {

    public Food(GameWorld gameWorld, float x, float y) {
        super(gameWorld, x, y, 1, Color.WHITE);
    }

    @Override
    public void destroy() {
        gameWorld.food.remove(this);
    }
}
