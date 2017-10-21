package net.indiearmory.evolsim.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import net.indiearmory.evolsim.Config;
import net.indiearmory.evolsim.EvolSim;
import net.indiearmory.evolsim.Game;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

        // Start the abstract game class
        Game.startNewGame();

        // Create the window
		config.width = Config.WIDTH/2;
		config.height = Config.HEIGHT/2;
		new LwjglApplication(new EvolSim(), config);
	}
}
