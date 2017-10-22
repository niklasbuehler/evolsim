package net.indiearmory.evolsim.gameworld.entities.motors;

import net.indiearmory.evolsim.gameworld.entities.Entity;

/**
 * Created by niklas on 21.10.17.
 * Copyright (c) 2017 IndieArmory
 */

public class InteractionMotor extends Motor {

    public InteractionMotor(Entity owner){
        super(owner);
        inputCount = 2;
    }

    public void control(float[] interactionData){
        // element #0 handles eating
        owner.setWillToEat(interactionData[0] >= .5f);

        // element #1 handles reproduction
        int willToReproduce;
        if(interactionData[1] < .33f){
            willToReproduce = 0;
        }else if(interactionData[1] < .67f){
            willToReproduce = 1;
        }else{
            willToReproduce = 2;
        }
        owner.setWillToReproduce(willToReproduce);
    }
}