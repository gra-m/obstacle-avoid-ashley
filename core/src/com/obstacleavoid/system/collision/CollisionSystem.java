package com.obstacleavoid.system.collision;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.Logger;
import com.obstacleavoid.common.Mappers;
import com.obstacleavoid.component.CircleBoundsComponent;
import com.obstacleavoid.component.ObstacleComponent;
import com.obstacleavoid.component.PlayerComponent;

public class CollisionSystem extends EntitySystem
{
    private static final Logger LOG = new Logger(CollisionSystem.class.getName(), Logger.DEBUG);
    private static final Family PLAYER_FAMILY = Family.all(PlayerComponent.class, CircleBoundsComponent.class).get();
    private static final Family OBSTACLE_FAMILY = Family.all(ObstacleComponent.class, CircleBoundsComponent.class).get();
    private final CollisionListener listener;

    public CollisionSystem( CollisionListener listener ){ this.listener = listener; }


    @Override
    public void update( float deltaTime )
    {
        // Size of players always expected == 1
        ImmutableArray< Entity > players = getEngine().getEntitiesFor(PLAYER_FAMILY);
        ImmutableArray<Entity> obstacles = getEngine().getEntitiesFor(OBSTACLE_FAMILY);

        for (Entity playerEntity: players) {
            for (Entity obstacleEntity: obstacles) {
                ObstacleComponent obstacleComponent = Mappers.OBSTACLE_COMPONENT_MAPPER.get(obstacleEntity);
                if (obstacleComponent.hitAlready) {
                    continue;
                }
                if (checkCollision(playerEntity, obstacleEntity)) {
                    obstacleComponent.hitAlready = true;
                    LOG.debug("collision with obstacle");
                    listener.hitObstacle();
                }
            }
        }
    }

    private boolean checkCollision( Entity playerEntity, Entity obstacleEntity ) {
        CircleBoundsComponent playerBounds = Mappers.CIRCLE_BOUNDS_COMPONENT_MAPPER.get(playerEntity);
        CircleBoundsComponent obstacleBounds = Mappers.CIRCLE_BOUNDS_COMPONENT_MAPPER.get(obstacleEntity);

        return Intersector.overlaps(playerBounds.bounds, obstacleBounds.bounds);
    }


}
