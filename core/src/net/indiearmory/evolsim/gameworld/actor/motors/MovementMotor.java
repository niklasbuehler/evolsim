package net.indiearmory.evolsim.gameworld.actor.motors;

/**
 * Created by niklas on 02.10.17.
 * Copyright (c) 2017 IndieArmory
 */

public class MovementMotor extends Motor {

    public MovementMotor(){
        inputCount = 1;
    }

    public void update(){
        // TODO apply acceleration
    }

    public void control(float[] movementData){
        // TODO change acceleration by angle at movementData[0]
    }
}