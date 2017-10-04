package net.indiearmory.evolsim.gameworld.actor.genetics;

/**
 * Created by niklas on 04.10.17.
 * Copyright (c) 2017 IndieArmory
 */

import net.indiearmory.evolsim.gameworld.GameModel;

import java.util.ArrayList;

/**
 * This class holds all the DNA of a population of species. It handles propagation.
 * For more information about genetics, see the documentation in the DNA class.
 */
public class Population {

    /**
     * A static, unique ID, which represents the species.
     * Starts at 1.
     */
    public static int ID;

    ArrayList<GameModel> entities;

    public Population(int uniqueID){
        this.ID = uniqueID;
        entities = new ArrayList<GameModel>();
    }

    // TODO think of concept for reproducing, respawning/spawning entities

}
