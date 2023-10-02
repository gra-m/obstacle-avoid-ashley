package com.obstacleavoid.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.utils.Logger;
import com.obstacleavoid.component.MovementComponent;
import com.obstacleavoid.component.PositionComponent;

public class MovementSystem extends IteratingSystem
{
    private static final Logger LOG = new Logger(MovementSystem.class.getName(), Logger.DEBUG);
    private static final Family FAMILY = Family.all(
            PositionComponent.class,
            MovementComponent.class
    ).get();

    public MovementSystem( )
    {
        super(FAMILY);
    }

    @Override
    protected void processEntity( Entity entity, float deltaTime )
    {

    }
}
