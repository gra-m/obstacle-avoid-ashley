package com.obstacleavoid.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.obstacleavoid.component.MovementComponent;
import com.obstacleavoid.component.PlayerComponent;

/**
 * Renders movement for entities that have Movement and Player components
 */
public class PlayerSystem extends IteratingSystem
{
    private static final Logger LOG = new Logger(PlayerSystem.class.getName(), Logger.DEBUG);
    private static final Family FAMILY = Family.all(
            MovementComponent.class,
            PlayerComponent.class).get();

    private final Viewport viewport;
    private final ShapeRenderer renderer;

    public PlayerSystem( Viewport viewport, ShapeRenderer renderer )
    {
        super(FAMILY);
        this.viewport = viewport;
        this.renderer = renderer;
    }

    @Override
    public void update( float deltaTime )
    {
        super.update(deltaTime);
    }

    @Override
    protected void processEntity( Entity entity, float deltaTime )
    {

    }
}
