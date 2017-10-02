package net.indiearmory.evolidle.gameworld.blob.sensors;

/**
 * Created by niklas on 02.10.17.
 * Copyright (c) 2017 IndieArmory
 */

/**
 * The blueprint class for all types of sensors.
 */
public abstract class Sensor {

    protected int outputCount;

    public Sensor(){

    }

    public abstract float[] query();

    public int getOutputCount(){
        return outputCount;
    }
}
