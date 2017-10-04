package net.indiearmory.evolsim.gameworld.actor.motors;

import com.badlogic.gdx.math.Vector2;

import net.indiearmory.evolsim.gameworld.GameModel;

/**
 * Created by niklas on 02.10.17.
 * Copyright (c) 2017 IndieArmory
 */

public class MovementMotor extends Motor {

    float maxAcceleration = .1f;

    public MovementMotor(GameModel owner){
        super(owner);
        inputCount = 1;
    }

    public void control(float[] movementData){
        float angle = movementData[0] * 180f;
        Vector2 force = new Vector2(1, 1).limit(maxAcceleration).setAngle(angle);
        owner.applyForce(force);
    }
}