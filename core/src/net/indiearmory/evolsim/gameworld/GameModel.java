package net.indiearmory.evolsim.gameworld;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import net.indiearmory.evolsim.Config;
import net.indiearmory.evolsim.gameworld.entities.Entity;

/**
 * Created by niklas on 02.10.17.
 * Copyright (c) 2017 IndieArmory
 */

/**
 * The basic game model used in the game world by all entities and other objects in the game world,
 * such as food, etc.
 */
public abstract class GameModel {

    protected GameWorld gameWorld;
    protected float x, y;
    protected float radius;
    protected Color color;

    protected Vector2 velocity;
    protected Vector2 acceleration;

    public GameModel(GameWorld gameWorld, float x, float y, float radius, Color color){
        this.gameWorld = gameWorld;
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = color;
        this.velocity = new Vector2(0, 0);
        this.acceleration = new Vector2(0, 0);
    }

    public void update(){
        // Check collisions
        if(!GameWorld.RECT.contains(getPosition())){
            // if(x < 0) x += Config.WIDTH;
            // if(x > Config.WIDTH) x -= Config.WIDTH;
            // if(y < 0) y += Config.HEIGHT;
            // if(y > Config.HEIGHT) y -= Config.HEIGHT;

            destroy();

            // radius--;
            // x = Math.max(0, Math.min(x, Config.WIDTH));
            // y = Math.max(0, Math.min(y, Config.HEIGHT));
            return;
        }

        // Calculate new velocity
        velocity.add(acceleration);
        // Update position
        x += velocity.x;
        y += velocity.y;

        // Remove acceleration
        acceleration.limit(0);
        // Also add some small resistance to limit impulses and max speed too
        // TODO (Based on weight = radius)
        velocity.scl(.9f);
    }

    public void draw(ShapeRenderer shapeRenderer){
        shapeRenderer.setColor(color);
        shapeRenderer.circle(x, y, radius);
    }

    public void applyForce(Vector2 force){
        acceleration.add(force);
    }

    public boolean contains(Vector2 positionToCheck){
        return (getPosition().dst(positionToCheck) <= radius);
    }

    public boolean overlaps(GameModel gameModel){
        return (getPosition().dst(gameModel.getPosition()) <= getRadius()+gameModel.getRadius());
    }

    protected abstract void destroy();

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

    public float getRadius(){
        return radius;
    }

    public float getAngle(){
        return velocity.angle();
    }

    public Vector2 getPosition(){
        return new Vector2(x, y);
    }

    public GameWorld getGameWorld(){
        return gameWorld;
    }
}
