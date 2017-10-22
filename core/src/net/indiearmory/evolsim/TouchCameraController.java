package net.indiearmory.evolsim;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by niklas on 04.10.17.
 * Copyright (c) 2017 IndieArmory
 */

// TODO add option to follow an entity
public class TouchCameraController implements GestureDetector.GestureListener {

    OrthographicCamera orthographicCamera;

    public TouchCameraController(OrthographicCamera orthographicCamera){
        this.orthographicCamera = orthographicCamera;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        orthographicCamera.translate(-deltaX * orthographicCamera.zoom, deltaY * orthographicCamera.zoom);
        orthographicCamera.update();
        keepInBounds();
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        orthographicCamera.zoom *= (initialDistance / distance);
        orthographicCamera.zoom = Math.min(2, orthographicCamera.zoom);
        orthographicCamera.zoom = Math.max(.1f, orthographicCamera.zoom);
        orthographicCamera.update();
        keepInBounds();
        return true;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }

    private void keepInBounds(){
        orthographicCamera.zoom = MathUtils.clamp(orthographicCamera.zoom, 0.1f, Config.WIDTH/orthographicCamera.viewportWidth);

        float effectiveViewportWidth = orthographicCamera.viewportWidth * orthographicCamera.zoom;
        float effectiveViewportHeight = orthographicCamera.viewportHeight * orthographicCamera.zoom;

        orthographicCamera.position.x = MathUtils.clamp(orthographicCamera.position.x, effectiveViewportWidth / 2f, Config.WIDTH - effectiveViewportWidth / 2f);
        orthographicCamera.position.y = MathUtils.clamp(orthographicCamera.position.y, effectiveViewportHeight / 2f, Config.HEIGHT - effectiveViewportHeight / 2f);
    }
}
