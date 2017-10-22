package net.indiearmory.evolsim;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;

import java.util.Random;

public class EvolSim extends ApplicationAdapter {
	private static Random random = new Random();

	OrthographicCamera orthographicCamera;
	ShapeRenderer shapeRenderer;

	Game game;

	@Override
	public void create () {
		int width = Config.WIDTH; // Gdx.graphics.getWidth();
		int height = Config.HEIGHT; // Gdx.graphics.getHeight();
		orthographicCamera = new OrthographicCamera(width, height);
		orthographicCamera.position.set(width/2, height/2, 0);

		// For android devices:
        // TouchCameraController touchCameraController = new TouchCameraController(orthographicCamera);
		//Gdx.input.setInputProcessor(new GestureDetector(touchCameraController));
		// For desktop devices:
		Gdx.input.setInputProcessor(new DesktopCameraController(orthographicCamera));

		shapeRenderer = new ShapeRenderer();

		game = Game.currentGame;
	}

	@Override
	public void render () {
		update();
		draw();
	}

	private void update(){
		game.update();
	}

	private void draw(){
		// Prepare for drawing
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		orthographicCamera.update();
		shapeRenderer.setProjectionMatrix(orthographicCamera.combined);

		// Do drawing
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		game.draw(shapeRenderer);
		shapeRenderer.end();
	}

	@Override
	public void dispose () {
		shapeRenderer.dispose();
	}

	public static int randInt(int min, int max) {
		// nextInt is normally exclusive of the top value,
		// so add 1 to make it inclusive
		int randomNum = random.nextInt((max - min) + 1) + min;

		return randomNum;
	}

    /**
     * Uses ColorUtils class to get random color by hue.
     * Can't use java.awt.Color because that isn't supported by Android.
     * @return
     */
    // FIXME not distict
    public static Color getRandomColor(){
        float hue = EvolSim.randInt(0, 1000) / 1000f;
        return ColorUtils.HSBtoColor(hue, 1f, 1f);
    }
}
