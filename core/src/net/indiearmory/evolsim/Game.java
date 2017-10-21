package net.indiearmory.evolsim;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import net.indiearmory.evolsim.gameworld.GameWorld;

/**
 * Created by niklas on 24.09.17.
 * Copyright (c) 2017 IndieArmory
 */

/**
 * This class contains all the specific game data and objects.
 */
public class Game {
    public static Game currentGame;

    private GameWorld gameWorld;

    public Game(){
        gameWorld = new GameWorld();
    }

    public static void startNewGame(){
        currentGame = new Game();
    }

    public void update(){
        gameWorld.update();
    }

    public void draw(ShapeRenderer shapeRenderer){
        gameWorld.draw(shapeRenderer);
    }

    public GameWorld getGameWorld(){
        return gameWorld;
    }
}
