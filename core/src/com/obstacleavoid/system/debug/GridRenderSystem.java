package com.obstacleavoid.system.debug;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.obstacleavoid.util.ViewportUtils;

public class GridRenderSystem extends EntitySystem
{
    // --> constants
    private static final Logger LOG = new Logger(GridRenderSystem.class.getName(), Logger.DEBUG);

    // --> attributes
    private final Viewport viewport;
    private final ShapeRenderer renderer;
    private boolean shown;

    // --> constructors
    public GridRenderSystem(Viewport view, ShapeRenderer shape) {
        //super(0); how priority set
        this.viewport = view;
        this.renderer = shape;
    }

    // --> engine update --> every systems update()
    @Override
    public void update( float deltaTime )
    {
        if (!shown) {
            LOG.debug("GridRenderSystem -> update()");
            shown = true;
        }

        viewport.apply();
        ViewportUtils.drawGrid( viewport, renderer );
    }

}