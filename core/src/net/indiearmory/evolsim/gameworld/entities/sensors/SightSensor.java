package net.indiearmory.evolsim.gameworld.entities.sensors;

import com.badlogic.gdx.math.Vector2;

import net.indiearmory.evolsim.gameworld.GameModel;
import net.indiearmory.evolsim.gameworld.entities.Entity;

/**
 * Created by niklas on 02.10.17.
 * Copyright (c) 2017 IndieArmory
 */

public class SightSensor extends Sensor {

    // e.g.: a maxSightAngle of 10° equals a field of vision of 20°
    private float maxSightAngle = 20;
    private float maxSightRange = 200;
    private float sightSensivity = 1f;

    private int nr_sightRays = 5;

    public SightSensor(Entity owner){
        super(owner);

        // Each sight ray feeds two nodes:
        // 1. distance
        // 2. color
        outputCount = nr_sightRays * 2;
    }

    /**
     * This function checks all sight rays of this sensor.
     * It returns a float array, which contains the distance
     * and the color towards/of a visible object.
     * Example:
     * ray1_distance
     * ray1_color
     * ray2_distance
     * ray2_color
     * @return float[] distances to objects, color of objects
     */
    // TODO clean this mess up: multiple functions?
    public float[] query(){
        float[] output = new float[outputCount];
        // Loop through all sight rays
        // -->For each sight ray, loop through it and try to find an object
        for(int sightRayNr=0; sightRayNr<nr_sightRays; sightRayNr++){
            // Angles range from -maxSightAngle to +maxSightAngle
            float rayAngle = -maxSightAngle + sightRayNr*(maxSightAngle*2/(nr_sightRays-1));
            float totalAngle = owner.getAngle() + rayAngle;
            Vector2 sightRay = new Vector2(1, 1).limit(maxSightRange).setAngle(totalAngle);
            // Add the own radius to the sight range, so we don't loose sight when growing
            int ownRadius = (int) Math.ceil(owner.getRadius());
            // Loop through the sight ray to find objects in its way
            single_ray_loop:
            for(int sightDist=1; sightDist<=maxSightRange+1; sightDist++){
                sightRay.setLength(ownRadius+sightDist);
                Vector2 positionToCheck = owner.getPosition().add(sightRay);
                // object at position holds null if out of bounds, 0 if it's empty or an ID of the object founds
                float hueAtPosition = owner.getGameWorld().checkPositionForColor(positionToCheck);
                if(hueAtPosition != 0){
                    // Save distance
                    output[sightRayNr*2] = calculateSightValue(sightDist);
                    // Save color hue
                    output[sightRayNr*2+1] = hueAtPosition;
                    break single_ray_loop;
                }else{
                    // Nothing seen at all for this ray & object type -> return max value
                    output[sightRayNr*2] = calculateSightValue(Float.MAX_VALUE);
                    output[sightRayNr*2+1] = 0;
                }
            }
        }
        return output;
    }

    /**
     * This function calculates the output data depending on the distance between
     * sensor and perceived object.
     * @param distance
     * @return
     */
    private float calculateSightValue(float distance){
        return sightSensivity/distance;
    }
}
