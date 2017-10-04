package net.indiearmory.evolsim.gameworld.entities.motors;

import net.indiearmory.evolsim.gameworld.entities.Entity;

/**
 * Created by niklas on 02.10.17.
 * Copyright (c) 2017 IndieArmory
 */

public abstract class Motor {

    Entity owner;
    protected int inputCount;

    public Motor(Entity owner){
        this.owner = owner;
    }

    /**
     * Called by brain to change parameters and influence
     * the running system.
     * @param inputData
     */
    public abstract void control(float[] inputData);

    public int getInputCount(){
        return inputCount;
    }
}
