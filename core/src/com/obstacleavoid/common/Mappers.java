package com.obstacleavoid.common;

import com.badlogic.ashley.core.ComponentMapper;
import com.obstacleavoid.component.CircleBoundsComponent;


// used to retrieve the components with the underlying data that was fed in when the entity was created.
public class Mappers
{
    // any entities CircleBoundsComponent had x, y and radius entered on creation before it was added to entity.
    public static final ComponentMapper< CircleBoundsComponent > CIRCLE_BOUNDS_COMPONENT_MAPPER =
            ComponentMapper.getFor(CircleBoundsComponent.class);


    private Mappers(){}
}
