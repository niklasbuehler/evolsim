package net.indiearmory.evolsim.gameworld.actor.brain;

/**
 * Created by niklas on 02.10.17.
 * Copyright (c) 2017 IndieArmory
 */

/**
 * This neural network takes sight as input and returns
 * an angle for changing the velocity as output.
 * The input value is determined by the distance towards a specific
 * type of object.
 * The input node is determined by the type of object detected and
 * by the direction where it is detected.
 */
public class MovementBrainPart extends BrainPart {

    public MovementBrainPart(int sight_outputCount){
        // input nodes are determined by sightsensor
        // 6 hidden nodes
        // 1 output node which controlls angle of acceleration
        super(sight_outputCount, 6, 1);
    }

}
