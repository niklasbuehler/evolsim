package net.indiearmory.evolidle.gameworld.blob.brain;

import net.indiearmory.evolidle.gameworld.blob.sensors.Sensor;
import net.indiearmory.evolidle.gameworld.blob.sensors.SightSensor;

/**
 * Created by niklas on 02.10.17.
 * Copyright (c) 2017 IndieArmory
 */

/**
 * The Brain class contains sensors and several neural networks
 * and acts as interface between those.
 * It handles input/output, as well as networking between
 * the different networks.
 */
public class Brain {

    SightSensor sightSensor;
    MovementBrainPart movementBrainPart;
    // TODO Movement motor

    public Brain(){
        sightSensor = new SightSensor();
        movementBrainPart = new MovementBrainPart(sightSensor.getOutputCount());
    }

    public void update(){
        float[] sightData = sightSensor.query();
        float[] movementOutput = movementBrainPart.query(sightData);
        // TODO send movementOutput -> blob.motor
    }
}
