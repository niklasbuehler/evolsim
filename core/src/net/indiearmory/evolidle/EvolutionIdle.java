package net.indiearmory.evolidle;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import net.indiearmory.evolidle.gameworld.blob.Blob;

public class EvolutionIdle extends ApplicationAdapter {

	OrthographicCamera orthographicCamera;
	ShapeRenderer shapeRenderer;

	Game game;

	@Override
	public void create () {
		orthographicCamera = new OrthographicCamera(Config.WIDTH, Config.HEIGHT);
		orthographicCamera.position.set(Config.WIDTH/2, Config.HEIGHT/2, 0);

		shapeRenderer = new ShapeRenderer();

		game = Game.currentGame;
	}

	@Override
	public void render () {
		draw();
	}

	private void draw(){
		// Prepare for drawing
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		autoZoomCamera();
		orthographicCamera.update();
		shapeRenderer.setProjectionMatrix(orthographicCamera.combined);

		// DO drawing
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		game.draw(shapeRenderer);
		shapeRenderer.end();
	}

	private void autoZoomCamera(){
		Blob blob = game.getGameWorld().getBlob();
		float radius = blob.getRadius();

		orthographicCamera.zoom = 10f/(Config.WIDTH/radius);
		orthographicCamera.lookAt(blob.getX(), blob.getY(), 0);
	}

	@Override
	public void dispose () {
		shapeRenderer.dispose();
	}
}
