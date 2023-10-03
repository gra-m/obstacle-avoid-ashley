package com.obstacleavoid.system.collision;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.utils.Logger;
import com.obstacleavoid.component.CircleBoundsComponent;
import com.obstacleavoid.component.ObstacleComponent;
import com.obstacleavoid.component.PlayerComponent;

public class CollisionSystem extends EntitySystem
{
    private static final Logger LOG = new Logger(CollisionSystem.class.getName(), Logger.DEBUG);
    private static final Family PLAYER_FAMILY = Family.all(PlayerComponent.class, CircleBoundsComponent.class).get();
    private static final Family OBSTACLE_FAMILY = Family.all(ObstacleComponent.class, CircleBoundsComponent.class).get();

    @Override
    public void update( float deltaTime )
    {
    }
}
