package net.indiearmory.evolidle.gameworld;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by niklas on 02.10.17.
 * Copyright (c) 2017 IndieArmory
 */

/**
 * The basic game model used in the game world by all entities.
 */
public abstract class GameModel {
    protected float x, y;
    protected float radius;
    protected Color color;

    public GameModel(float x, float y, float radius, Color color){
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = color;
    }

    public void update(){
        if(!GameWorld.RECT.contains(x, y)){
            die();
        }
    }

    public void draw(ShapeRenderer shapeRenderer){
        shapeRenderer.setColor(color);
        shapeRenderer.circle(x, y, radius, 50);
    }

    protected abstract void die();

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

    public float getRadius(){
        return radius;
    }
}
