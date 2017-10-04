package net.indiearmory.evolsim.gameworld.entities.sensors;

import net.indiearmory.evolsim.gameworld.GameModel;

/**
 * Created by niklas on 21.10.17.
 * Copyright (c) 2017 IndieArmory
 */

public class VitalSensor extends Sensor {

    public VitalSensor(GameModel owner){
        super(owner);

        outputCount = 1;
    }

    /**
     * This function checks for several internal values,
     * like radius, etc.
     * @return float[] vital data
     */
    public float[] query(){
        float[] output = new float[outputCount];
        output[0] = owner.getRadius();
        // TODO collect more data
        return output;
    }
}
