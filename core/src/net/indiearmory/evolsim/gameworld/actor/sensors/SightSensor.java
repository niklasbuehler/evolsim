package net.indiearmory.evolsim.gameworld.actor.sensors;

/**
 * Created by niklas on 02.10.17.
 * Copyright (c) 2017 IndieArmory
 */

public class SightSensor extends Sensor {

    private int nr_sightRays = 3;
    private int nr_objectTypes = 1;

    public SightSensor(){
        outputCount = nr_sightRays * nr_objectTypes;
    }

    public float[] query(){
        // TODO create float array consisting of distances towards object types
        return null;
    }
}
