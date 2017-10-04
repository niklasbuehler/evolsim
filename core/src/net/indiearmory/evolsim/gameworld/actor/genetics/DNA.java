package net.indiearmory.evolsim.gameworld.actor.genetics;

/**
 * Created by niklas on 04.10.17.
 * Copyright (c) 2017 IndieArmory
 */

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

    ArrayList<float[]> weights;

    public DNA(){
        weights = new ArrayList<float[]>();
    }

    // TODO find way to receive, save and distribute weights when amount of brainparts/sensors is changed

}
