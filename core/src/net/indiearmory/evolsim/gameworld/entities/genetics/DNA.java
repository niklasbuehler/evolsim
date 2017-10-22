package net.indiearmory.evolsim.gameworld.entities.genetics;

/**
 * Created by niklas on 22.10.17.
 * Copyright (c) 2017 IndieArmory
 */

import com.badlogic.gdx.graphics.Color;

import net.indiearmory.evolsim.ColorUtils;
import net.indiearmory.evolsim.EvolSim;

/**
 * The DNA contains all the weights for all the neural networks in an
 * entities brain. If an entity lives long enough to reproduce, the DNA has a
 * good fitness and will be passed on. Crossover will happen and good combinations
 * will continue to spread, bad combinations will die out.
 * Another change is mutation which will happen randomly.
 */
public class DNA {

    // Chance of a mutation per 1000 weights
    public static int mutationChanceMilli = 10;

    float[] weights_input_hidden, weights_hidden_output;

    // Similar color -> similar DNA
    private Color color;

    public DNA(float[] weights_input_hidden, float[] weights_hidden_output, Color color){
        this.weights_input_hidden = weights_input_hidden;
        this.weights_hidden_output = weights_hidden_output;
        this.color = color;

        mutate();
    }

    private void mutate(){
        // Mutations:
        for(int i=0; i<weights_input_hidden.length; i++){
            if(EvolSim.randInt(1, 1000) <= mutationChanceMilli){
                // mutate
                weights_input_hidden[i] = getRandomWeightValue();
                mutateColor();
            }
        }
        for(int i=0; i<weights_hidden_output.length; i++){
            if(EvolSim.randInt(1, 1000) <= mutationChanceMilli){
                // mutate
                weights_hidden_output[i] = getRandomWeightValue();
                mutateColor();
            }
        }
    }

    // FIXME no visible changes
    private void mutateColor(){
        float hue = ColorUtils.getHue(color);
        if(EvolSim.randInt(0, 1) == 0){
            hue+=.01f;
        }else{
            hue-=.01f;
        }
        hue = Math.max(0, Math.min(1, hue));
        color = ColorUtils.HSBtoColor(hue, 1f, 1f);
    }

    /**
     * Cross this DNA with the given DNA.
     * @return
     */
    public DNA cross(DNA anotherDNA){
        float[] new_weights_input_hidden = new float[weights_input_hidden.length];
        float[] new_weights_hidden_output = new float[weights_hidden_output.length];

        for(int i=0; i<new_weights_input_hidden.length; i++){
            if(EvolSim.randInt(0, 1) == 0){
                new_weights_input_hidden[i] = weights_input_hidden[i];
            }else{
                new_weights_input_hidden[i] = anotherDNA.getWeightsInputHidden()[i];
            }
        }

        for(int i=0; i<new_weights_hidden_output.length; i++){
            if(EvolSim.randInt(0, 1) == 0){
                new_weights_hidden_output[i] = weights_hidden_output[i];
            }else{
                new_weights_hidden_output[i] = anotherDNA.getWeightsHiddenOutput()[i];
            }
        }

        Color crossedColor = getColor().lerp(anotherDNA.getColor(), 0.5f);

        DNA newDNA = new DNA(new_weights_input_hidden, new_weights_hidden_output, crossedColor);

        return newDNA;
    }

    public float[] getWeightsInputHidden(){
        return weights_input_hidden;
    }

    public float[] getWeightsHiddenOutput(){
        return weights_hidden_output;
    }

    public Color getColor(){
        return color.cpy();
    }

    /**
     * Returns random value between -.9f and .9f
     * @return
     */
    private Float getRandomWeightValue(){
        return (float) EvolSim.randInt(-9, 9) / 10f;
    }
}
