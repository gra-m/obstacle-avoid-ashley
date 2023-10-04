package com.obstacleavoid.system.debug;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.obstacleavoid.common.GameManager;
import com.obstacleavoid.common.Mappers;
import com.obstacleavoid.component.DimensionComponent;
import com.obstacleavoid.component.PositionComponent;
import com.obstacleavoid.component.WorldWrapComponent;
import com.obstacleavoid.config.GameConfig;

public class WorldWrapSystem extends IteratingSystem
{
    private static final Logger LOG = new Logger(WorldWrapSystem.class.getName(), Logger.DEBUG);
    private static final Family FAMILY = Family.all(
            WorldWrapComponent.class,
            PositionComponent.class,
            DimensionComponent.class
    ).get();
    private final Viewport viewport;
    
    public WorldWrapSystem( Viewport viewport )
    {
        super(FAMILY);
        this.viewport = viewport;
    }

    @Override
    protected void processEntity( Entity entity, float deltaTime )
    {
        PositionComponent positionComponent = Mappers.POSITION_COMPONENT_MAPPER.get(entity);
        DimensionComponent dimensionComponent = Mappers.DIMENSION_COMPONENT_MAPPER.get(entity);

        // clamp positions:
        positionComponent.x = MathUtils.clamp(positionComponent.x, 0,
                viewport.getWorldWidth() - dimensionComponent.width);
        // add up and down to listener to enable this.
        positionComponent.y = MathUtils.clamp(positionComponent.y, 0 + dimensionComponent.height/2,
                viewport.getWorldHeight() - dimensionComponent.height) ;

    }
}
