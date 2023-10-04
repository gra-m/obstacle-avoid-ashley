package com.obstacleavoid.system;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.obstacleavoid.component.DimensionComponent;
import com.obstacleavoid.component.PositionComponent;
import com.obstacleavoid.component.TextureComponent;

public class RenderSystem extends EntitySystem
{
    private static final Family FAMILY = Family.all(
            TextureComponent.class,
            PositionComponent.class,
            DimensionComponent.class
    ).get();

    
}
