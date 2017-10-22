package net.indiearmory.evolsim.gameworld.entities.sensors;

import com.badlogic.gdx.graphics.Color;

import net.indiearmory.evolsim.ColorUtils;
import net.indiearmory.evolsim.gameworld.GameModel;
import net.indiearmory.evolsim.gameworld.entities.Entity;

/**
 * Created by niklas on 21.10.17.
 * Copyright (c) 2017 IndieArmory
 */

public class VitalSensor extends Sensor {

    public VitalSensor(Entity owner){
        super(owner);

        outputCount = 2;
    }

    /**
     * This function checks for several internal values,
     * like radius, etc.
     * @return float[] vital data
     */
    public float[] query(){
        float[] output = new float[outputCount];
        output[0] = owner.getRadius();
        output[1] = ColorUtils.getHue(owner.getColor());

        // TODO collect and provide more internal data
        
        return output;
    }
}
