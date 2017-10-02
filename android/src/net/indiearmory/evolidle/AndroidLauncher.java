package net.indiearmory.evolidle;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import net.indiearmory.evolidle.EvolutionIdle;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Start libgdx stuff
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new EvolutionIdle(), config);
	}
}
