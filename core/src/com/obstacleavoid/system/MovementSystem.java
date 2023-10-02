package com.obstacleavoid.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.utils.Logger;
import com.obstacleavoid.common.Mappers;
import com.obstacleavoid.component.CircleBoundsComponent;
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
        // get position component for current entity
        PositionComponent positionComponent = Mappers.POSITION_COMPONENT_MAPPER.get(entity);
        MovementComponent movementComponent = Mappers.MOVEMENT_COMPONENT_MAPPER.get(entity);
        // Speed is always added to position meaning object just leaves screen
        float  newXPosition = positionComponent.x +=  movementComponent.xSpeed;
        float newYPosition = positionComponent.y +=  movementComponent.ySpeed;

        CircleBoundsComponent circleBoundsComponent = Mappers.CIRCLE_BOUNDS_COMPONENT_MAPPER.get(entity);
        LOG.debug("processEntity position x = " +  newXPosition + " position y = " + newYPosition);

        circleBoundsComponent.bounds.setPosition(newXPosition, newYPosition);

        // update bounds

    }
}
