package com.obstacleavoid.common;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.obstacleavoid.assets.AssetDescriptors;
import com.obstacleavoid.component.*;
import com.obstacleavoid.config.GameConfig;

public class EntityFactory
{
    private PooledEngine engine;
    private final TextureAtlas gameplayAtlas;

    public EntityFactory( PooledEngine engine, TextureAtlas gameplayAtlas)
    {
        this.engine = engine;
        this.gameplayAtlas = gameplayAtlas;
    }


    public void addPlayer( CircleBoundsComponent circleBoundsComponent,
                           MovementComponent movementComponent,
                           PlayerComponent playerComponent,
                           PositionComponent positionComponent,
                           WorldWrapComponent worldWrapComponent,
                           DimensionComponent dimensionComponent,
                           TextureComponent textureComponent) {
        // original position
       float x = GameConfig.WORLD_WIDTH / 2f;
       positionComponent.x = x;
       float y = GameConfig.PLAYER_SIZE;
       positionComponent.y = y;

        circleBoundsComponent.bounds.set(x, y, GameConfig.PLAYER_BOUNDS_RADIUS);

        dimensionComponent.height = GameConfig.PLAYER_SIZE;
        dimensionComponent.width = GameConfig.PLAYER_SIZE;

        textureComponent.region = gameplayAtlas.findRegion("player");

        Entity entity = engine.createEntity();
        entity.add(circleBoundsComponent);
        entity.add(movementComponent);
        entity.add(playerComponent);
        entity.add(positionComponent);
        entity.add(worldWrapComponent);
        entity.add(dimensionComponent);
        entity.add(textureComponent);
        engine.addEntity(entity);
    }


    public void addObstacle(float newObstacleX, float newObstacleY){
        CircleBoundsComponent circleBoundsComponent = engine.createComponent(CircleBoundsComponent.class);
        MovementComponent  movementComponent = engine.createComponent(MovementComponent.class);
        PositionComponent positionComponent = engine.createComponent(PositionComponent.class);
        CleanUpComponent cleanUpComponent = engine.createComponent(CleanUpComponent.class);
        ObstacleComponent obstacleComponent = engine.createComponent(ObstacleComponent.class);

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
        entity.add(obstacleComponent);

        engine.addEntity(entity);

    }
}
