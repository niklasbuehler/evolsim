package net.indiearmory.evolsim.gameworld.entities.genetics;

/**
 * Created by niklas on 04.10.17.
 * Copyright (c) 2017 IndieArmory
 */

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import net.indiearmory.evolsim.Config;
import net.indiearmory.evolsim.EvolSim;
import net.indiearmory.evolsim.gameworld.GameWorld;
import net.indiearmory.evolsim.gameworld.entities.Entity;

import java.util.ArrayList;

/**
 * This class holds all the entities of a population and their DNA. It also handles propagation.
 * For more information about genetics, see the documentation in the DNA class.
 */
public class Population {
    // The base radius for all populations at which the entitites start
    public static int baseRadius = 5;
    // The amount of entities per newly spawned population
    public static int baseEntityCount = 25;

    // A unique ID, which represents the species (created from timestamp).
    public final long ID = System.currentTimeMillis();
    // This populations base color
    public Color baseColor;
    // Reference of the world
    GameWorld gameWorld;
    // List of all entities of this population
    public ArrayList<Entity> entities;

    public Population(GameWorld gameWorld){
        this.gameWorld = gameWorld;

        this.baseColor = EvolSim.getRandomColor();

        entities = new ArrayList<Entity>();
        for(int i=0; i<baseEntityCount; i++){
            createNewEntity(baseRadius);
        }
    }

    public void createNewEntity(float radius){
        entities.add(new Entity(gameWorld, this, EvolSim.randInt(0, Config.WIDTH), EvolSim.randInt(0, Config.HEIGHT), radius, baseColor));
    }

    public void update(){
        for(int i=0; i<entities.size(); i++){
            entities.get(i).update();
        }
    }

    public void draw(ShapeRenderer shapeRenderer){
        for(int i=0; i<entities.size(); i++){
            entities.get(i).draw(shapeRenderer);
        }
    }
}
