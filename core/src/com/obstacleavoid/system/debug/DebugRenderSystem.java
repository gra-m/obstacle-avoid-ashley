package com.obstacleavoid.system.debug;


import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.obstacleavoid.common.Mappers;
import com.obstacleavoid.component.CircleBoundsComponent;

/**
 * Renders anything that has BOUNDS or to be more specific the CircleBoundsComponent
 */
public class DebugRenderSystem extends IteratingSystem
{
    private static final Logger LOG = new Logger(DebugRenderSystem.class.getName(), Logger.DEBUG);
    private static final Family FAMILY = Family.all(CircleBoundsComponent.class).get();

    private final Viewport viewport;
    private final ShapeRenderer renderer;

    // my keep console clean code
    private boolean updateCalled; // called once
    private int processEntityCalledCount; // called for each entity retrieved in family

    public DebugRenderSystem(Viewport viewport, ShapeRenderer renderer )
    {
        super(FAMILY);
        this.viewport = viewport;
        this.renderer = renderer;
    }

    // iterates over the FAMILY (CircleBoundsComponent.class) that has been passed to super for iteration
    @Override
    public void update( float deltaTime )
    {
        if (!updateCalled ) {
            LOG.debug("DebugRenderSystem_e_IteratingSystem -> update()");
            updateCalled = true;
        }

        Color oldColor = renderer.getColor().cpy();
        viewport.apply();

        renderer.setProjectionMatrix(viewport.getCamera( ).combined);
        renderer.begin(ShapeRenderer.ShapeType.Line);

        renderer.setColor(Color.RED);
        // whole family iterated over and the processEntity method in this child class is called for each
        super.update(deltaTime);

        renderer.end();

        renderer.setColor(oldColor);
    }

    @Override
    protected void processEntity( Entity entity, float deltaTime )
    {
       if ( processEntityCalledCount++ < super.getEntities().size() ) {
        LOG.debug("DebugRenderSystem_e_IteratingSystem -> processEntity() entity = "
                + entity.getClass().getSimpleName() + " is " + processEntityCalledCount + " of " + super.getEntities().size());
    }
      // what happens to each entity that is iterated over:
      // 1 its bounds component is retrieved (our family is built of those with this component)
      CircleBoundsComponent bc = Mappers.CIRCLE_BOUNDS_COMPONENT_MAPPER.get(entity);
      // 2 the entities bounds are rendered
      renderer.circle(bc.bounds.x, bc.bounds.y, bc.bounds.radius, 30);
    }

}
