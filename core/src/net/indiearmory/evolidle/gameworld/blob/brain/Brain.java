package net.indiearmory.evolidle.gameworld.blob.brain;

import java.util.ArrayList;

/**
 * Created by niklas on 02.10.17.
 * Copyright (c) 2017 IndieArmory
 */

/**
 * The Brain class contains several neural networks, the
 * brain parts, and handles input/output, as well as networking
 * between those networks. This is the interface between sensors
 * and motors.
 */
public class Brain {

    private BrainPart visualBrainPart;

    public Brain(){
        // visualBrainPart = new BrainPart(); // TODO
    }

}
