package net.indiearmory.evolsim.gameworld.entities.brain;

import net.indiearmory.evolsim.EvolSim;
import net.indiearmory.evolsim.gameworld.entities.Entity;
import net.indiearmory.evolsim.gameworld.entities.genetics.DNA;
import net.indiearmory.evolsim.gameworld.entities.motors.Motor;
import net.indiearmory.evolsim.gameworld.entities.motors.MovementMotor;
import net.indiearmory.evolsim.gameworld.entities.motors.InteractionMotor;
import net.indiearmory.evolsim.gameworld.entities.sensors.Sensor;
import net.indiearmory.evolsim.gameworld.entities.sensors.SightSensor;
import net.indiearmory.evolsim.gameworld.entities.sensors.TouchingSensor;
import net.indiearmory.evolsim.gameworld.entities.sensors.VitalSensor;

import java.util.ArrayList;

/**
 * Created by niklas on 22.10.17.
 * Copyright (c) 2017 IndieArmory
 */

/**
 * The brain is a neural network, having various input
 * neurons and outputting data to control the entity.
 *
 * A learning process by backpropagation is not included,
 * since a genetic algorithm will be used for that.
 */
public class Brain {

    private Entity owner;

    private int nr_inputNodes, nr_hiddenNodes, nr_outputNodes;

    private float[] weights_input_hidden, weights_hidden_output;

    ArrayList<Sensor> sensors;
    ArrayList<Motor> motors;

    public Brain(Entity owner){
        this.owner = owner;

        // The sensors provide all the input data and thus determine the amount of input nodes
        sensors = new ArrayList<Sensor>();
        // The motors process the output data into actions and thus determine the amount of output nodes
        motors = new ArrayList<Motor>();

        sensors.add(new VitalSensor(owner));
        sensors.add(new SightSensor(owner));
        sensors.add(new TouchingSensor(owner));

        motors.add(new MovementMotor(owner));
        motors.add(new InteractionMotor(owner));

        // Calculate amount of nodes
        nr_inputNodes = 0;
        for(int i=0; i<sensors.size(); i++){
            nr_inputNodes += sensors.get(i).getOutputCount();
        }

        nr_outputNodes = 0;
        for(int i=0; i<motors.size(); i++){
            nr_outputNodes += motors.get(i).getInputCount();
        }

        // TODO find a way to select the perfect amount of hidden nodes
        // randomize?
        // calculate from input nodes and output nodes?
        nr_hiddenNodes = 8;

        // Set up input->hidden weights randomly
        weights_input_hidden = new float[nr_inputNodes * nr_hiddenNodes];
        for (int i = 0; i < nr_inputNodes * nr_hiddenNodes; i++){
            weights_input_hidden[i] = getRandomWeightValue();
        }
        // Set up hidden->output weights randomly
        weights_hidden_output = new float[nr_hiddenNodes * nr_outputNodes];
        for (int i = 0; i < nr_hiddenNodes * nr_outputNodes; i++){
            weights_hidden_output[i] = getRandomWeightValue();
        }
    }

    public Brain(Entity owner, DNA dna){
        this(owner);

        // Load up input->hidden weights from dna
        weights_input_hidden = dna.getWeightsInputHidden();
        // Load up hidden->output weights from dna
        weights_hidden_output = dna.getWeightsHiddenOutput();
    }

    public void update() {
        // Getting input data
        float[] inputData = new float[nr_inputNodes];
        int currentIndex = 0;
        for(int i=0; i<sensors.size(); i++){
            float[] sensorData = sensors.get(i).query();
            for(int j=0; j<sensorData.length; j++){
                inputData[currentIndex+j] = sensorData[j];
            }
            currentIndex += sensorData.length;
        }

        // Feeding input data into neural network and getting output data.
        float[] outputData = query(inputData);

        // Forwarding outputData to motors
        currentIndex = 0;
        for(int i=0; i<motors.size(); i++){
            float[] motorInput = new float[motors.get(i).getInputCount()];
            for(int j=0; j<motorInput.length; j++){
                motorInput[j] = outputData[currentIndex+j];
            }
            motors.get(i).control(motorInput);
            currentIndex += motorInput.length;
        }
    }

    public DNA getDNA(){
        return new DNA(weights_input_hidden, weights_hidden_output, owner.getColor());
    }

    /**
     * This is where the magic happens. Read a book about neural networks
     * if you want further information.
     * @param inputs
     * @return
     */
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
        return (float) EvolSim.randInt(-9, 9) / 10f;
    }

}
