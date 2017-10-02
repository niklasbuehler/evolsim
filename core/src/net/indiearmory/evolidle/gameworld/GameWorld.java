package net.indiearmory.evolidle.gameworld;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

import net.indiearmory.evolidle.Config;

import java.util.ArrayList;

/**
 * Created by niklas on 01.10.17.
 * Copyright (c) 2017 IndieArmory
 */

/**
 * This class contains all game models and handles updating these.
 */
public class GameWorld {
    public static Rectangle RECT = new Rectangle(0, 0, Config.WIDTH, Config.HEIGHT);

    net.indiearmory.evolidle.gameworld.blob.Blob blob;
    ArrayList<net.indiearmory.evolidle.gameworld.GameModel> gameModels;

    public GameWorld(){
        blob = new net.indiearmory.evolidle.gameworld.blob.Blob(Config.WIDTH/2, Config.HEIGHT/2, 1);

        gameModels = new ArrayList<net.indiearmory.evolidle.gameworld.GameModel>();
    }

    public void draw(ShapeRenderer shapeRenderer){
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(0, 0, Config.WIDTH, Config.HEIGHT);

        blob.draw(shapeRenderer);
    }

    public net.indiearmory.evolidle.gameworld.blob.Blob getBlob(){
        return blob;
    }
}
