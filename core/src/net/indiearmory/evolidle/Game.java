package net.indiearmory.evolidle;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by niklas on 24.09.17.
 * Copyright (c) 2017 IndieArmory
 */

public class Game {
    public static Game currentGame;

    private net.indiearmory.evolidle.gameworld.GameWorld gameWorld;

    public Game(){
        gameWorld = new net.indiearmory.evolidle.gameworld.GameWorld();
    }

    public static void startNewGame(){
        currentGame = new Game();
    }

    public void draw(ShapeRenderer shapeRenderer){
        gameWorld.draw(shapeRenderer);
    }

    public net.indiearmory.evolidle.gameworld.GameWorld getGameWorld(){
        return gameWorld;
    }
}
