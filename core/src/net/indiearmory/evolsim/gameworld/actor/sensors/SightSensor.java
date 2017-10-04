package net.indiearmory.evolsim.gameworld.actor.sensors;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.collision.Ray;

import net.indiearmory.evolsim.EvolSim;
import net.indiearmory.evolsim.gameworld.GameModel;

import java.util.ArrayList;

/**
 * Created by niklas on 02.10.17.
 * Copyright (c) 2017 IndieArmory
 */

public class SightSensor extends Sensor {

    // e.g.: a maxSightAngle of 10° equals a field of view of 20°
    private float maxSightAngle = 20;
    private float maxSightRange = 10;
    private ArrayList<Integer> perceptibleObjectTypes;

    private int nr_sightRays = 3;

    public SightSensor(GameModel owner){
        super(owner);
        perceptibleObjectTypes = new ArrayList<Integer>();
        perceptibleObjectTypes.add(-1);
        outputCount = nr_sightRays * perceptibleObjectTypes.size();
    }

    /**
     * This function checks all sight rays for all types of objects which are
     * perceptible by this sensor. It returns a float array, which is ordered by
     * object types first, then by sight rays checked.
     * Example:
     * obj1
     *  ray1 (index 0)
     *  ray2 (index 1)
     * obj2
     *  ray1 (index 2)
     *  ray2 (index 3)
     * The values contained in the array symbolize the distance towards an
     * object of the specific type on the specific ray.
     * @return float[] distances to objects (for each object type / ray)
     */
    public float[] query(){
        float[] output = new float[outputCount];
        // Loop through all types of objects which are perceptible by this sensor
        //      For each type, check all sight rays
        //          For each sight ray, loop through it and try to find an object of the currently checked type which blocks its way
        for(int typeNr=0; typeNr<perceptibleObjectTypes.size(); typeNr++){
            for(int sightRayNr=0; sightRayNr<nr_sightRays; sightRayNr++){
                // Angles range from -maxSightAngle to +maxSightAngle
                float rayAngle = -maxSightAngle + sightRayNr*(maxSightAngle*2/(nr_sightRays-1));
                float totalAngle = owner.getAngle() + rayAngle;
                Vector2 sightRay = new Vector2(1, 1).limit(maxSightRange).setAngle(totalAngle);
                // Loop through the sight ray to find objects in its way
                single_ray_loop:
                for(int sightDist=0; sightDist<maxSightRange; sightDist++){
                    sightRay.limit(sightDist);
                    Vector2 positionToCheck = owner.getPosition().add(sightRay);
                    // object at position holds null if out of bounds, 0 if it's empty or an ID of the object founds
                    Integer objectTypeAtPosition = owner.getGameWorld().checkPositionForObjectType(positionToCheck);
                    if(objectTypeAtPosition == perceptibleObjectTypes.get(typeNr)){
                        output[typeNr * nr_sightRays + sightRayNr] = 1f/sightDist;
                        break single_ray_loop;
                    }
                }
            }
        }

        return output;
    }
}
