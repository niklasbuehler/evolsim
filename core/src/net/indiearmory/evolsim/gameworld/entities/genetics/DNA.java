package net.indiearmory.evolsim.gameworld.entities.genetics;

/**
 * Created by niklas on 04.10.17.
 * Copyright (c) 2017 IndieArmory
 */

import net.indiearmory.evolsim.EvolSim;

import java.util.ArrayList;

/**
 * The DNA contains all the weights for all the neural networks in an
 * entities brain. After death, the fitness of each entity will be calculated.
 * When adding DNA to the genepool, the amount added is determined by the DNAs fitness.
 * By doing this, it's possible to pair good DNA with good DNA and therefore increase
 * the populations fitness in the longterm. Another factor is mutation which will happen
 * randomly. Maybe mutation should be balanced by the fitness; the higher the fitness, the
 * lower the mutation chance.
 */
public class DNA {

    ArrayList<DNASequence> sequences;

    public DNA(){
        sequences = new ArrayList<DNASequence>();
    }

    public void addSequence(DNASequence dnaSequence){
        sequences.add(dnaSequence);
    }

    public DNASequence getSequence(int nr){
        return sequences.get(nr);
    }

    /**
     * Cross this DNA with the given DNA.
     * @return
     */
    public DNA cross(DNA anotherDNA){
        DNA newDNA = new DNA();
        for(int i=0; i<sequences.size(); i++){
            DNASequence newSequence = new DNASequence();
            for(int j=0; j<sequences.get(i).weights.size(); j++){
                float[] newWeights = new float[sequences.get(i).weights.get(j).length];
                for(int k=0; k<sequences.get(i).weights.get(j).length; k++){
                    if(EvolSim.randInt(0, 1) == 0){
                        newWeights[k] = sequences.get(i).weights.get(j)[k];
                    }else{
                        newWeights[k] = anotherDNA.sequences.get(i).weights.get(j)[k];
                    }
                }
                newSequence.addWeights(newWeights);
            }
            newDNA.addSequence(newSequence);
        }
        // TODO also mix colors of entities for visualization of spreading of one DNA
        return newDNA;
    }

}
