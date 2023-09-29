package com.obstacleavoid.system.debug;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.Logger;
import com.obstacleavoid.util.debug.DebugCameraController;

public class DebugCameraSystem extends EntitySystem
{
    // --> constants
    private static final Logger LOG = new Logger(DebugCameraSystem.class.getName(), Logger.DEBUG);
    private static final DebugCameraController  DEBUG_CAMERA_CONTROLLER = new DebugCameraController();

    // --> attributes
    private final OrthographicCamera camera;
    private boolean shown;

    public DebugCameraSystem( OrthographicCamera camera, final float WORLD_CENTER_X, final float WORLD_CENTER_Y )
    {
        this.camera = camera;
        DEBUG_CAMERA_CONTROLLER.setStartPosition(WORLD_CENTER_X, WORLD_CENTER_Y);
    }

    @Override
    public void update( float deltaTime )
    {
        if (!shown) {
            LOG.debug("DebugCameraSystem -> update()");
            shown = true;
        }
        DEBUG_CAMERA_CONTROLLER.handleDebugInput(deltaTime);
        DEBUG_CAMERA_CONTROLLER.applyTo(camera);
    }
}
