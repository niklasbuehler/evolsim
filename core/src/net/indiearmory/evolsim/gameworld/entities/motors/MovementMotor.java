package net.indiearmory.evolsim.gameworld.entities.motors;

import com.badlogic.gdx.math.Vector2;

import net.indiearmory.evolsim.gameworld.entities.Entity;

/**
 * Created by niklas on 02.10.17.
 * Copyright (c) 2017 IndieArmory
 */

public class MovementMotor extends Motor {

    float maxAcceleration = .125f;

    public MovementMotor(Entity owner){
        super(owner);
        inputCount = 2;
    }

    public void control(float[] movementData){
        float angle = owner.getAngle() + movementData[0] * 360f - 180f;
        float force = movementData[1] * maxAcceleration;
        Vector2 vecForce = new Vector2(1, 1).setLength(force).setAngle(angle);
        owner.applyForce(vecForce);
    }
}