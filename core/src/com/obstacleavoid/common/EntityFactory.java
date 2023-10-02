package com.obstacleavoid.common;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.obstacleavoid.component.*;
import com.obstacleavoid.config.GameConfig;
import com.obstacleavoid.config.GameDifficulty;
import org.graalvm.compiler.lir.sparc.SPARCMove;

public class EntityFactory
{
    private PooledEngine engine;

    public EntityFactory( PooledEngine engine )
    {
        this.engine = engine;
    }


    public void addPlayer( CircleBoundsComponent circleBoundsComponent,
                           MovementComponent movementComponent,
                           PlayerComponent playerComponent,
                           PositionComponent positionComponent,
                           WorldWrapComponent worldWrapComponent ) {
        // original position
       float x = GameConfig.WORLD_WIDTH / 2f;
       positionComponent.x = x;
       float y = GameConfig.PLAYER_SIZE;
       positionComponent.y = y;

        circleBoundsComponent.bounds.set(x, y, GameConfig.PLAYER_BOUNDS_RADIUS);

        Entity entity = engine.createEntity();
        entity.add(circleBoundsComponent);
        entity.add(movementComponent);
        entity.add(playerComponent);
        entity.add(positionComponent);
        entity.add(worldWrapComponent);
        engine.addEntity(entity);
    }


    public void addObstacle(float newObstacleX, float newObstacleY){
        CircleBoundsComponent circleBoundsComponent = engine.createComponent(CircleBoundsComponent.class);
        MovementComponent  movementComponent = engine.createComponent(MovementComponent.class);
        PositionComponent positionComponent = engine.createComponent(PositionComponent.class);
        CleanUpComponent cleanUpComponent = engine.createComponent(CleanUpComponent.class);

        circleBoundsComponent.bounds.set(newObstacleX, newObstacleY, GameConfig.OBSTACLE_BOUNDS_RADIUS);

        movementComponent.xSpeed = 0f;
        movementComponent.ySpeed = -GameManager.INSTANCE.getGameDifficulty().getObjectSpeed();

        positionComponent.x = newObstacleX;
        positionComponent.y = newObstacleY;

        Entity entity = engine.createEntity();

        entity.add(circleBoundsComponent);
        entity.add(movementComponent);
        entity.add(positionComponent);
        entity.add(cleanUpComponent);
        

        engine.addEntity(entity);

    }

    public void removeEnitityFromEngine(Entity entity ) {
        engine.removeEntity(entity);
    }
}
