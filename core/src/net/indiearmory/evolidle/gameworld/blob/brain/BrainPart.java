package net.indiearmory.evolidle.gameworld.blob.brain;

/**
 * Created by niklas on 02.10.17.
 * Copyright (c) 2017 IndieArmory
 */

import net.indiearmory.evolidle.EvolutionIdle;

import java.util.ArrayList;

/**
 * Each brain part is an own neural network, having own input
 * neurons and output data. The parts of the brain work together
 * by passing around data from output to input nodes of the
 * different neural networks.
 *
 * The learning process by backpropagation is not included, since
 * a genetic algorithm will be used for that.
 */
// TODO option to add nodes
public abstract class BrainPart {

    private int nr_inputNodes, nr_hiddenNodes, nr_outputNodes;

    private float[] weights_input_hidden, weights_hidden_output;

    public BrainPart(int nr_inputNodes, int nr_hiddenNodes, int nr_outputNodes){
        this.nr_inputNodes = nr_inputNodes;
        this.nr_hiddenNodes = nr_hiddenNodes;
        this.nr_outputNodes = nr_outputNodes;

        // Set up input->hidden weights
        weights_input_hidden = new float[nr_inputNodes * nr_hiddenNodes];
        for(int i=0; i<nr_inputNodes*nr_hiddenNodes; i++){
            weights_input_hidden[i] = getRandomWeightValue();
        }
        // Set up hidden->output weights
        weights_hidden_output = new float[nr_hiddenNodes * nr_outputNodes];
        for(int i=0; i<nr_hiddenNodes*nr_outputNodes; i++){
            weights_hidden_output[i] = getRandomWeightValue();
        }
    }

    public float[] query(float[] inputs){
        // signals into hidden layer
        float[] hidden_inputs = matrix_mult(inputs, weights_input_hidden, nr_hiddenNodes);
        // signals emerging from hidden layer
        float[] hidden_outputs = new float[nr_hiddenNodes];
        // apply activation function
        for(int i=0; i<nr_hiddenNodes; i++){
            hidden_outputs[i] = activation_function(hidden_inputs[i]);
        }

        // signals into output layer
        float[] output_inputs = matrix_mult(hidden_outputs, weights_hidden_output, nr_outputNodes);
        // signals emerging from output layer
        float[] output_outputs = new float[nr_outputNodes];
        // apply activation function
        for(int i=0; i<nr_outputNodes; i++){
            output_outputs[i] = activation_function(output_inputs[i]);
        }

        return output_outputs;
    }

    /**
     * Multiplies inputs with corresponding weights.
     * Returns the processed array.
     * @param inputs
     * @param weights
     * @param output_size
     * @return
     */
    private float[] matrix_mult(float[] inputs, float[] weights, int output_size){
        float[] output = new float[output_size];

        for(int i=0; i<output_size; i++){
            float sum = 0;
            for(int j=0; j<inputs.length; j++){
                float weight = weights[i+j*output_size];
                sum += inputs[j] * weight;
            }
            output[i] = sum;
        }
        return output;
    }

    /**
     * Sigmoid function for mapping values between 0 and 1.
     * @param x
     * @return
     */
    private float activation_function(float x){
        return (float) (1f / (1f+Math.pow(Math.E, -x)));
    }

    /**
     * Returns random value between -.9f and .9f
     * @return
     */
    private Float getRandomWeightValue(){
        return (float) EvolutionIdle.randInt(-9, 9) / 10f;
    }
}
