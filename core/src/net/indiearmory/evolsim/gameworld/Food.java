package net.indiearmory.evolsim.gameworld;

import com.badlogic.gdx.graphics.Color;

import net.indiearmory.evolsim.ColorUtils;
import net.indiearmory.evolsim.EvolSim;

/**
 * Created by niklas on 21.10.17.
 * Copyright (c) 2017 IndieArmory
 */

public class Food extends GameModel {

    public Food(GameWorld gameWorld, float x, float y) {
        super(gameWorld, x, y, EvolSim.randInt(1, 3), Color.FOREST);
    }

    @Override
    public void destroy() {
        gameWorld.food.remove(this);
    }
}
