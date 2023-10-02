package com.obstacleavoid.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.utils.Logger;
import com.obstacleavoid.common.Mappers;
import com.obstacleavoid.component.CircleBoundsComponent;
import com.obstacleavoid.component.PositionComponent;

public class BoundsSystem extends IteratingSystem
{
    private static final Logger LOG = new Logger(BoundsSystem.class.getName(), Logger.DEBUG);
    private static final Family FAMILY = Family.all(
            CircleBoundsComponent.class,
            PositionComponent.class
    ).get();

    public BoundsSystem()
    {
        super(FAMILY);
    }


    @Override
    protected void processEntity( Entity entity, float deltaTime )
    {
        CircleBoundsComponent circleBoundsComponent = Mappers.CIRCLE_BOUNDS_COMPONENT_MAPPER.get(entity);
        PositionComponent positionComponent = Mappers.POSITION_COMPONENT_MAPPER.get(entity);
        
        circleBoundsComponent.bounds.setPosition(positionComponent.x, positionComponent.y);

    }
}
