package net.indiearmory.evolsim.gameworld.entities.sensors;

/**
 * Created by niklas on 02.10.17.
 * Copyright (c) 2017 IndieArmory
 */

import net.indiearmory.evolsim.gameworld.GameModel;

/**
 * The blueprint class for all types of sensors.
 */
public abstract class Sensor {

    GameModel owner;
    protected int outputCount;

    public Sensor(GameModel owner){
        this.owner = owner;
    }

    public abstract float[] query();

    public int getOutputCount(){
        return outputCount;
    }

}
