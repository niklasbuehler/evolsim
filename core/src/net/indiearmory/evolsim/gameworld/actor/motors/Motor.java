package net.indiearmory.evolsim.gameworld.actor.motors;

import net.indiearmory.evolsim.gameworld.GameModel;

/**
 * Created by niklas on 02.10.17.
 * Copyright (c) 2017 IndieArmory
 */

public abstract class Motor {

    GameModel owner;
    protected int inputCount;

    public Motor(GameModel owner){
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
