package net.indiearmory.evolidle.gameworld.blob.brain;

/**
 * Created by niklas on 02.10.17.
 * Copyright (c) 2017 IndieArmory
 */

import java.util.ArrayList;

/**
 * Each brain part is an own neural network, having own input
 * neurons and output data. The parts of the brain work together
 * by passing around data from output to input nodes of the
 * different neural networks.
 */
public abstract class BrainPart {

    private int nr_inputNodes, nr_hiddenNodes, nr_outputNodes;
    private ArrayList<Float> weights_input_hidden, weights_hidden_output;

    public BrainPart(int nr_inputNodes, int nr_hiddenNodes, int nr_outputNodes){
        this.nr_inputNodes = nr_inputNodes;
        this.nr_hiddenNodes = nr_hiddenNodes;
        this.nr_outputNodes = nr_outputNodes;
    }
}
