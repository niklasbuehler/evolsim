package net.indiearmory.evolsim.gameworld.entities.sensors;

import com.badlogic.gdx.graphics.Color;

import net.indiearmory.evolsim.ColorUtils;
import net.indiearmory.evolsim.gameworld.Food;
import net.indiearmory.evolsim.gameworld.GameModel;
import net.indiearmory.evolsim.gameworld.entities.Entity;

/**
 * Created by niklas on 22.10.17.
 * Copyright (c) 2017 IndieArmory
 */

/**
 * Checks which colors are being overlapped and how big they are.
 */
public class TouchingSensor extends Sensor {

    public TouchingSensor(Entity owner){
        super(owner);

        outputCount = 1*1;
    }

    /**
     * This function checks for objects being overlapped
     * and returns their colors + size.
     * Only works for one object at a time (for now).
     * @return float[] sensed data
     */
    // TODO put sizes into range [0, 1], allow multiple outputs
    public float[] query(){
        float[] output = new float[outputCount];

        food_loop:
        for(int i=0; i<owner.getGameWorld().food.size(); i++){
            Food food = owner.getGameWorld().food.get(i);
            if(owner.overlaps(food)){
                output[0] = ColorUtils.getHue(food.getColor());
                //output[1] = food.getRadius();
                break food_loop;
            }
        }

        // Entities have priority: override
        populations_loop:
        for(int i=0; i<owner.getGameWorld().populations.size(); i++){
            for(int j=0; j<owner.getGameWorld().populations.get(i).entities.size(); j++){
                Entity entity = owner.getGameWorld().populations.get(i).entities.get(j);
                if(owner.overlaps(entity) && owner != entity){
                    output[0] = ColorUtils.getHue(entity.getColor());
                    //output[1] = entity.getRadius();
                    break populations_loop;
                }
            }
        }

        return output;
    }
}
