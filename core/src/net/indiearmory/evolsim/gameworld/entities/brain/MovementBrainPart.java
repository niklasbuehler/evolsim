package net.indiearmory.evolsim.gameworld.entities.brain;

/**
 * Created by niklas on 02.10.17.
 * Copyright (c) 2017 IndieArmory
 */

import net.indiearmory.evolsim.gameworld.GameModel;
import net.indiearmory.evolsim.gameworld.entities.genetics.DNASequence;

/**
 * This neural network takes sight as input and returns
 * an angle and value for changing the acceleration as output.
 * The input nodes are pairs of distance and color detected.
 */
public class MovementBrainPart extends BrainPart {
    public static int hiddenNodes = 3;
    public static int outputCount = 2;

    public MovementBrainPart(GameModel owner, int sight_outputCount){
        // input nodes are determined by sightsensor
        // 4 hidden nodes
        // 2 output nodes
        //      1 output node which controls angle of acceleration
        //      1 output node which controls force of acceleration
        super(owner, sight_outputCount, hiddenNodes, outputCount);
    }

    public MovementBrainPart(GameModel owner, int sight_outputCount, DNASequence dnaSequence){
        // Recreation from given DNA Sequence
        super(owner, sight_outputCount, hiddenNodes, outputCount, dnaSequence);
    }

}
