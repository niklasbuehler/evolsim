package net.indiearmory.evolidle.gameworld.blob;

import com.badlogic.gdx.graphics.Color;

import net.indiearmory.evolidle.gameworld.GameModel;
import net.indiearmory.evolidle.gameworld.blob.brain.Brain;

/**
 * Created by niklas on 02.10.17.
 * Copyright (c) 2017 IndieArmory
 */

/**
 * This is the "player" object, although it is not controlled by
 * the player directly. The player will influence it indirectly by affecting
 * its sensors and brains.
 */
public class Blob extends GameModel {

    Brain brain;

    public Blob(float x, float y, float radius){
        super(x, y, radius, Color.WHITE);
    }

    public void update(){
        super.update();
        brain.update();
    }

    protected void die(){
        // TODO genetics, respawning, etc
    }
}
