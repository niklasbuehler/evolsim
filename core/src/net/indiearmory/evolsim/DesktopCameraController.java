package net.indiearmory.evolsim;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by niklas on 22.10.17.
 * Copyright (c) 2017 IndieArmory
 */

public class DesktopCameraController implements InputProcessor {

    OrthographicCamera orthographicCamera;

    public DesktopCameraController(OrthographicCamera orthographicCamera){
        this.orthographicCamera = orthographicCamera;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        // TODO also put in relation with zoom

        float x = Gdx.input.getDeltaX();
        float y = Gdx.input.getDeltaY();

        orthographicCamera.translate(-x,y);

        keepInBounds();

        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        // coords of mouse before zoom
        Vector3 screenCoords = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        Vector3 worldCoordsBefore = orthographicCamera.unproject(new Vector3(screenCoords));

        // adjust the zoom level
        orthographicCamera.zoom += amount * orthographicCamera.zoom * 0.1f;
        orthographicCamera.update();

        // get the coords of the the mouse position afterwards
        Vector3 worldCoordsAfter = orthographicCamera.unproject(new Vector3(screenCoords));

        // correct the difference
        Vector3 diff = new Vector3(worldCoordsAfter).sub(worldCoordsBefore);
        orthographicCamera.position.sub(diff);
        orthographicCamera.update();

        keepInBounds();

        return true;
    }

    private void keepInBounds(){
        orthographicCamera.zoom = MathUtils.clamp(orthographicCamera.zoom, 0.1f, Config.WIDTH/orthographicCamera.viewportWidth);

        float effectiveViewportWidth = orthographicCamera.viewportWidth * orthographicCamera.zoom;
        float effectiveViewportHeight = orthographicCamera.viewportHeight * orthographicCamera.zoom;

        orthographicCamera.position.x = MathUtils.clamp(orthographicCamera.position.x, effectiveViewportWidth / 2f, Config.WIDTH - effectiveViewportWidth / 2f);
        orthographicCamera.position.y = MathUtils.clamp(orthographicCamera.position.y, effectiveViewportHeight / 2f, Config.HEIGHT - effectiveViewportHeight / 2f);
    }
}
