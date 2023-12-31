package com.obstacleavoid.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.utils.Logger;
import com.obstacleavoid.common.EntityFactory;
import com.obstacleavoid.common.Mappers;
import com.obstacleavoid.component.CircleBoundsComponent;
import com.obstacleavoid.component.CleanUpComponent;
import com.obstacleavoid.component.PositionComponent;
import com.obstacleavoid.config.GameConfig;

public class CleanUpSystem extends IteratingSystem
{
    private static final Logger LOG = new Logger(CleanUpSystem.class.getName(), Logger.DEBUG);
    private static final Family FAMILY = Family.all(
           CleanUpComponent.class,
           PositionComponent.class
    ).get();

    public CleanUpSystem( )
    {
        super(FAMILY);
    }

    @Override
    protected void processEntity( Entity entity, float deltaTime )
    {
        PositionComponent position = Mappers.POSITION_COMPONENT_MAPPER.get(entity);

        if (position.y <  -GameConfig.OBSTACLE_SIZE ) {
            getEngine().removeEntity(entity);
        }

    }
}
