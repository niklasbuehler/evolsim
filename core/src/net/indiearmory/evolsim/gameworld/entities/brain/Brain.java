package net.indiearmory.evolsim.gameworld.entities.brain;

import com.badlogic.gdx.Gdx;

import net.indiearmory.evolsim.gameworld.GameModel;
import net.indiearmory.evolsim.gameworld.entities.Entity;
import net.indiearmory.evolsim.gameworld.entities.genetics.DNA;
import net.indiearmory.evolsim.gameworld.entities.motors.MovementMotor;
import net.indiearmory.evolsim.gameworld.entities.motors.ReproductionMotor;
import net.indiearmory.evolsim.gameworld.entities.sensors.SightSensor;
import net.indiearmory.evolsim.gameworld.entities.sensors.VitalSensor;

/**
 * Created by niklas on 02.10.17.
 * Copyright (c) 2017 IndieArmory
 */

/**
 * The Brain class contains sensors and several neural networks
 * and acts as interface between those.
 * It handles input/output, as well as networking between
 * the different networks.
 */
public class Brain {

    GameModel owner;

    SightSensor sightSensor;
    MovementBrainPart movementBrainPart;
    MovementMotor movementMotor;

    VitalSensor vitalSensor;
    ReproductionBrainPart reproductionBrainPart;
    ReproductionMotor reproductionMotor;

    public Brain(Entity owner) {
        this.owner = owner;

        sightSensor = new SightSensor(owner);
        movementBrainPart = new MovementBrainPart(owner, sightSensor.getOutputCount());
        movementMotor = new MovementMotor(owner);

        vitalSensor = new VitalSensor(owner);
        reproductionBrainPart = new ReproductionBrainPart(owner, vitalSensor.getOutputCount());
        reproductionMotor = new ReproductionMotor(owner);
    }

    public Brain(Entity owner, DNA dna){
        this.owner = owner;

        sightSensor = new SightSensor(owner);
        movementBrainPart = new MovementBrainPart(owner, sightSensor.getOutputCount(), dna.getSequence(0));
        movementMotor = new MovementMotor(owner);

        vitalSensor = new VitalSensor(owner);
        reproductionBrainPart = new ReproductionBrainPart(owner, vitalSensor.getOutputCount(), dna.getSequence(1));
        reproductionMotor = new ReproductionMotor(owner);
    }

    public void update() {
        // Getting input data and forwarding/processing it
        float[] sightData = sightSensor.query();
        float[] movementData = movementBrainPart.query(sightData);
        movementMotor.control(movementData);

        float[] vitalData = vitalSensor.query();
        float[] reproductionData = reproductionBrainPart.query(vitalData);
        reproductionMotor.control(reproductionData);
    }

    public DNA createDNA(){
        DNA dna = new DNA();
        dna.addSequence(movementBrainPart.getDNASequence());
        dna.addSequence(reproductionBrainPart.getDNASequence());
        return dna;
    }
}
