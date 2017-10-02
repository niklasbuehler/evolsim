package net.indiearmory.evolsim.gameworld.actor.motors;

/**
 * Created by niklas on 02.10.17.
 * Copyright (c) 2017 IndieArmory
 */

public abstract class Motor {

    protected int inputCount;

    public Motor(){

    }

    /**
     * Called every tick. No changes are applied here.
     */
    public abstract void update();

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
