package com.obstacleavoid.system.collision;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.Logger;
import com.obstacleavoid.common.Mappers;
import com.obstacleavoid.component.CircleBoundsComponent;
import com.obstacleavoid.component.HittableCollectableComponent;
import com.obstacleavoid.component.PlayerComponent;
import com.obstacleavoid.config.GameConfig;

public class CollisionSystem extends EntitySystem
{
    private static final Logger LOG = new Logger(CollisionSystem.class.getName(), Logger.DEBUG);
    private static final Family PLAYER_FAMILY = Family.all(PlayerComponent.class, CircleBoundsComponent.class).get();
    private static final Family HITTABLE_OR_COLLECTABLE_FAMILY = Family.all(HittableCollectableComponent.class, CircleBoundsComponent.class).get();
    private final CollisionListener listener;

    public CollisionSystem( CollisionListener listener ){ this.listener = listener; }


    @Override
    public void update( float deltaTime )
    {
        // Size of players always expected == 1
        ImmutableArray< Entity > players = getEngine().getEntitiesFor(PLAYER_FAMILY);
        ImmutableArray<Entity> hittableOrCollectables = getEngine().getEntitiesFor(HITTABLE_OR_COLLECTABLE_FAMILY);

        for (Entity playerEntity: players) {
            for (Entity hittableOrCollectableEntity: hittableOrCollectables) {
                HittableCollectableComponent hittableCollectableComponent = Mappers.HITTABLE_COLLECTABLE_COMPONENT_MAPPER.get(hittableOrCollectableEntity);
                CircleBoundsComponent boundsComponent = Mappers.CIRCLE_BOUNDS_COMPONENT_MAPPER.get(hittableOrCollectableEntity);
                if ( hittableCollectableComponent.hitOrCollectedAlready ) {
                    continue;
                }
                if (checkCollision(playerEntity, hittableOrCollectableEntity)) {
                    hittableCollectableComponent.hitOrCollectedAlready = true;
                    if (boundsComponent.bounds.radius == GameConfig.OBSTACLE_BOUNDS_RADIUS) {
                        LOG.debug("collision with obstacle");
                        listener.hitObstacle( );
                        continue;
                    } else if (boundsComponent.bounds.radius == GameConfig.LIFE_COLLECTABLE_BOUNDS_RADIUS) {
                       LOG.debug("successfully collected life collectable");
                       listener.collectedLife( );
                    }
                }
            }
        }
    }



    private boolean checkCollision( Entity playerEntity, Entity otherEntity ) {
        CircleBoundsComponent playerBounds = Mappers.CIRCLE_BOUNDS_COMPONENT_MAPPER.get(playerEntity);
        CircleBoundsComponent otherEntityBounds = Mappers.CIRCLE_BOUNDS_COMPONENT_MAPPER.get(otherEntity);

        return Intersector.overlaps(playerBounds.bounds, otherEntityBounds.bounds);
    }


}
