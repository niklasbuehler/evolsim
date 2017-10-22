package net.indiearmory.evolsim.gameworld.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

import net.indiearmory.evolsim.gameworld.GameModel;
import net.indiearmory.evolsim.gameworld.GameWorld;
import net.indiearmory.evolsim.gameworld.entities.brain.Brain;
import net.indiearmory.evolsim.gameworld.entities.genetics.Population;
import net.indiearmory.evolsim.gameworld.entities.genetics.DNA;

/**
 * Created by niklas on 02.10.17.
 * Copyright (c) 2017 IndieArmory
 */

/**
 * This is the "player" object, although it is not controlled by
 * the player directly. The player will influence it indirectly by affecting
 * its sensors and brains.
 */
public class Entity extends GameModel {
    // Minimum possible radius to survive
    public static float minimumRadius = 2f;

    Population population;
    Brain brain;

    // 0: Not willing to reproduce
    // 1: Willing to reproduce (passively)
    // 2: Actively trying to reproduce
    private int willToReproduce = 0;
    // false: not eating, true: eating
    private boolean willToEat = false;

    public Entity(GameWorld gameWorld, Population population, float x, float y, float radius, Color color) {
        super(gameWorld, x, y, radius, color);
        this.population = population;
        brain = new Brain(this);
    }

    public Entity(GameWorld gameWorld, Population population, float x, float y, float radius, DNA dna){
        super(gameWorld, x, y, radius, dna.getColor());
        this.population = population;
        brain = new Brain(this, dna);
    }

    public void update() {
        if(radius < minimumRadius){
            destroy();
        }

        super.update();
        brain.update();

        // Starvation:
        radius -= .0001f;

        // Reproduction:
        reproduction:
        for(int i=0; i<population.entities.size(); i++){
            // Check if two entities of the same species are overlapping
            if(overlaps(population.entities.get(i)) && population.entities.get(i) != this){
                Entity partner = population.entities.get(i);
                tryToReproduce(partner);
                break reproduction;
            }
        }

        // Eating:
        eating:
        if(willToEat && radius >= minimumRadius+.1f){
            // Costs some mass
            radius -= .1f;

            // Entities
            for(int i=0; i<gameWorld.populations.size(); i++){
                if(gameWorld.populations.get(i) != population){ // No canibalism for now
                    for(int j=0; j<gameWorld.populations.get(i).entities.size(); j++){
                        Entity enemy = gameWorld.populations.get(i).entities.get(j);
                        if(overlaps(enemy) && radius >= 1.5f*enemy.radius){
                            enemy.radius--;
                            radius++;
                            break eating;
                        }
                    }
                }
            }

            // Food
            for(int i=0; i<gameWorld.food.size(); i++){
                if(overlaps(gameWorld.food.get(i))){
                    radius+=gameWorld.food.get(i).getRadius();
                    gameWorld.food.get(i).destroy();
                    break eating;
                }
            }
        }
    }

    private void tryToReproduce(Entity partner){
        // Check if both have enough health (are big enough) for reproduction
        if(radius*.25f + partner.radius*.25f < minimumRadius) return;
        // Check for consensus
        if(willToReproduce != 2) return;
        if(partner.willToReproduce == 0) return;

        // Create baby
        float newRadius = radius*.25f + partner.radius*.25f;
        radius *= .75f;
        partner.radius *= .75f;
        Vector2 newPosition = new Vector2((x+partner.x)/2, (y+partner.y)/2);
        DNA dna1 = getDNA();
        DNA dna2 = partner.getDNA();
        DNA newDNA = dna1.cross(dna2);

        Entity baby = new Entity(gameWorld, population, newPosition.x, newPosition.y, newRadius, newDNA);

        population.entities.add(baby);
    }

    @Override
    protected void destroy() {
        population.entities.remove(this);
        for(int i=(int)minimumRadius; i<=radius; i++){
            gameWorld.createFood();
        }
    }

    public DNA getDNA(){
        return brain.getDNA();
    }

    /**
     * 0: Not willing
     * 1: Passively waiting
     * 2: Actively searching
     * @param state
     */
    public void setWillToReproduce(int state){
        willToReproduce = state;
    }

    public void setWillToEat(boolean state){
        willToEat = state;
    }
}
