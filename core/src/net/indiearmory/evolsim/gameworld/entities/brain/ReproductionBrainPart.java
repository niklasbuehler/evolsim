package net.indiearmory.evolsim.gameworld.entities.brain;

/**
 * Created by niklas on 21.10.17.
 * Copyright (c) 2017 IndieArmory
 */

import net.indiearmory.evolsim.gameworld.GameModel;
import net.indiearmory.evolsim.gameworld.entities.genetics.DNASequence;

/**
 * This neural network takes various internal data, as well as
 * data about possible mating partners, as input and handles
 * reproduction.
 * Outputs < .25f           -> Not available for reproduction
 * .25f < Outputs < .75f    -> Available for reproduction
 * Outputs > .75f           -> Tries reproducing actively
 */
public class ReproductionBrainPart extends BrainPart {

    public static int hiddenNodes = 3;
    public static int outputCount = 1;

    public ReproductionBrainPart(GameModel owner, int vital_outputCount){
        super(owner, vital_outputCount, hiddenNodes, outputCount);
    }

    public ReproductionBrainPart(GameModel owner, int vital_outputCount, DNASequence dnaSequence){
        // Recreation from given DNA Sequence
        super(owner, vital_outputCount, hiddenNodes, outputCount, dnaSequence);
    }

}
