package com.obstacleavoid.common;

import com.badlogic.ashley.core.ComponentMapper;
import com.obstacleavoid.component.*;


// used to retrieve the components with the underlying data that was fed in when the entity was created.
public class Mappers
{
    // any entities CircleBoundsComponent had x, y and radius entered on creation before it was added to entity.
    public static final ComponentMapper< CircleBoundsComponent > CIRCLE_BOUNDS_COMPONENT_MAPPER =
            ComponentMapper.getFor(CircleBoundsComponent.class);
    public static final ComponentMapper< MovementComponent > MOVEMENT_COMPONENT_MAPPER =
            ComponentMapper.getFor(MovementComponent.class);
    public static final ComponentMapper< PositionComponent > POSITION_COMPONENT_MAPPER =
            ComponentMapper.getFor(PositionComponent.class);
    public static final ComponentMapper< HittableCollectableComponent > HITTABLE_COLLECTABLE_COMPONENT_MAPPER =
            ComponentMapper.getFor(HittableCollectableComponent.class);
    public static final ComponentMapper< DimensionComponent > DIMENSION_COMPONENT_MAPPER =
            ComponentMapper.getFor(DimensionComponent.class);
    public static final ComponentMapper< TextureComponent > TEXTURE_COMPONENT_MAPPER =
            ComponentMapper.getFor(TextureComponent.class);
    private Mappers(){}
}
