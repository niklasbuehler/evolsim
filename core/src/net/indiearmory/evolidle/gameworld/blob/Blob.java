package net.indiearmory.evolidle.gameworld.blob;

import com.badlogic.gdx.graphics.Color;

import net.indiearmory.evolidle.gameworld.GameModel;
import net.indiearmory.evolidle.gameworld.blob.brain.Brain;

/**
 * Created by niklas on 02.10.17.
 * Copyright (c) 2017 IndieArmory
 */

public class Blob extends GameModel {

    Brain brain;

    public Blob(float x, float y, float radius){
        super(x, y, radius, Color.WHITE);
    }

    public void update(){
        super.update();
        // TODO movement etc
    }

    protected void die(){
        // TODO
    }
}
