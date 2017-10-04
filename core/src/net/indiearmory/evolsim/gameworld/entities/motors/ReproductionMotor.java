package net.indiearmory.evolsim.gameworld.entities.motors;

import net.indiearmory.evolsim.gameworld.entities.Entity;
import net.indiearmory.evolsim.gameworld.entities.brain.ReproductionBrainPart;

/**
 * Created by niklas on 21.10.17.
 * Copyright (c) 2017 IndieArmory
 */

public class ReproductionMotor extends Motor {

    public ReproductionMotor(Entity owner){
        super(owner);
        inputCount = ReproductionBrainPart.outputCount;
    }

    public void control(float[] reproductionData){
        int willToReproduce;
        if(reproductionData[0] < .25f){
            willToReproduce = 0;
        }else if(reproductionData[0] < .75f){
            willToReproduce = 1;
        }else{
            willToReproduce = 2;
        }
        owner.setWillToReproduce(willToReproduce);
    }
}