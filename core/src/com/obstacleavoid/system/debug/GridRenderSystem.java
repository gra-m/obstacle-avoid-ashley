package com.obstacleavoid.system.debug;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.obstacleavoid.util.ViewportUtils;

public class GridRenderSystem extends EntitySystem
{
    private static final Logger LOG = new Logger(GridRenderSystem.class.getName(), Logger.DEBUG);
    // --> attributes
    private final Viewport viewport;
    private final ShapeRenderer renderer;

    // --> constructors
    public GridRenderSystem(Viewport view, ShapeRenderer shape) {
        this.viewport = view;
        this.renderer = shape;
    }

    // --> engine update --> every systems update()

    @Override
    public void update( float deltaTime )
    {
        viewport.apply();
        ViewportUtils.drawGrid( viewport, renderer );
    }

}