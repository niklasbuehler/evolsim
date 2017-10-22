package net.indiearmory.evolsim.gameworld;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import net.indiearmory.evolsim.ColorUtils;
import net.indiearmory.evolsim.Config;
import net.indiearmory.evolsim.EvolSim;
import net.indiearmory.evolsim.gameworld.entities.genetics.Population;

import java.util.ArrayList;

/**
 * Created by niklas on 01.10.17.
 * Copyright (c) 2017 IndieArmory
 */

/**
 * This class contains all populations and handles updating these.
 */
public class GameWorld {
    // A rectangle representing the outer bounds of the world
    public static Rectangle RECT = new Rectangle(0, 0, Config.WIDTH, Config.HEIGHT);
    // The number of populations
    public static int basePopulationCount = 2;
    // The number of free food at start
    public static int baseFoodCount = 1000;
    // Populations containing all entities
    public ArrayList<Population> populations;
    // Food which does not move, is filled when entities die, so no mass is lost
    public ArrayList<Food> food;

    public GameWorld(){
        populations = new ArrayList<Population>();
        for(int i=0; i<basePopulationCount; i++){
            populations.add(new Population(this, EvolSim.randInt(0, Config.WIDTH), EvolSim.randInt(0, Config.HEIGHT)));
        }

        food = new ArrayList<Food>();
        for(int i=0; i<baseFoodCount; i++){
            createFood();
        }
    }

    public void update(){
        for(int i=0; i<populations.size(); i++){
            populations.get(i).update();
        }

        // Randomly spawning new populations
        if(EvolSim.randInt(1, 3000) == 1) populations.add(new Population(this, EvolSim.randInt(0, Config.WIDTH), EvolSim.randInt(0, Config.HEIGHT)));
    }

    public void draw(ShapeRenderer shapeRenderer){
        shapeRenderer.setColor(com.badlogic.gdx.graphics.Color.BLACK);
        shapeRenderer.rect(0, 0, Config.WIDTH, Config.HEIGHT);

        for(int i=0; i<food.size(); i++){
            food.get(i).draw(shapeRenderer);
        }

        for(int i=0; i<populations.size(); i++){
            populations.get(i).draw(shapeRenderer);
        }
    }

    public void createFood(){
        food.add(new Food(this, EvolSim.randInt(0, Config.WIDTH), EvolSim.randInt(0, Config.HEIGHT)));
    }

    /**
     * This function returns the hue of the color
     * of the found object at the given position.
     * If the checked position is out of bounds, -1 is returned
     * If there is no object, 0 is returned.
     *
     * @param positionToCheck
     * @return color hue at position
     */
    public float checkPositionForColor(Vector2 positionToCheck){
        if(!RECT.contains(positionToCheck)){
            return -1;
        }
        for(int i=0; i<populations.size(); i++){
            for(int j=0; j<populations.get(i).entities.size(); j++){
                if(populations.get(i).entities.get(j).contains(positionToCheck)){
                    Color foundColor = populations.get(i).entities.get(j).getColor();
                    return ColorUtils.getHue(foundColor);
                }
            }
        }
        return 0;
    }
}
