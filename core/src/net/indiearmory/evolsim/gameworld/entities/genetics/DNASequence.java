package net.indiearmory.evolsim.gameworld.entities.genetics;

/**
 * Created by niklas on 04.10.17.
 * Copyright (c) 2017 IndieArmory
 */

import net.indiearmory.evolsim.EvolSim;

import java.util.ArrayList;

/**
 * Objects of this class contain DNA for one sensor, motor or brainpart.
 * They're contained in an DNA object.
 */
public class DNASequence {
    // Chance of a mutation in 1000 weights
    public static int mutationChanceMilli = 10;

    ArrayList<float[]> weights;

    public DNASequence(){
        weights = new ArrayList<float[]>();
    }

    public void addWeights(float[] weights){
        for(int i=0; i<weights.length; i++){
            if(EvolSim.randInt(1, 1000) <= mutationChanceMilli){
                weights[i] = getRandomWeightValue();
            }
        }
        this.weights.add(weights);
    }

    public float[] getWeights(int nr){
        return weights.get(nr);
    }

    /**
     * Returns random value between -.9f and .9f
     * @return
     */
    private Float getRandomWeightValue(){
        return (float) EvolSim.randInt(-9, 9) / 10f;
    }
}
