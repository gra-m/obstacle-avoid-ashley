package com.obstacleavoid.system;

import com.badlogic.ashley.systems.IntervalSystem;
import com.badlogic.gdx.math.MathUtils;
import com.obstacleavoid.common.EntityFactory;
import com.obstacleavoid.config.GameConfig;

public class ObstacleSpawnSystem extends IntervalSystem
{
private final EntityFactory factory;

    public ObstacleSpawnSystem( EntityFactory factory ) {
        super(GameConfig.OBSTACLES_SPAWN_EVERY);
        this.factory = factory;
    }

    @Override
    protected void updateInterval( )
    {
        float min = 0;
        float max = GameConfig.WORLD_WIDTH - GameConfig.OBSTACLE_SIZE;

        float obstacleX = MathUtils.random(min, max);
        float obstacleY = GameConfig.WORLD_HEIGHT + GameConfig.OBSTACLE_SIZE;

        factory.addObstacle(obstacleX, obstacleY);


    }
}
