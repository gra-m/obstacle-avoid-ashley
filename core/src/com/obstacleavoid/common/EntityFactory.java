package com.obstacleavoid.common;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.obstacleavoid.assets.AssetDescriptors;
import com.obstacleavoid.assets.RegionNames;
import com.obstacleavoid.component.*;
import com.obstacleavoid.config.GameConfig;

public class EntityFactory
{
    private PooledEngine engine;
    private final AssetManager assetManager;
    private final TextureAtlas gameplayAtlas;

    public EntityFactory( PooledEngine engine, AssetManager assetManager)
    {
        this.engine = engine;
        this.assetManager = assetManager;
        this.gameplayAtlas = this.assetManager.get(AssetDescriptors.GAMEPLAY_ATlAS);
    }

    public void addBackground( PositionComponent positionComponent, DimensionComponent dimensionComponent, TextureComponent textureComponent ) {
        positionComponent.x = 0f;
        positionComponent.y= 0f;
        dimensionComponent.width = GameConfig.WORLD_WIDTH;
        dimensionComponent.height = GameConfig.WORLD_HEIGHT;
        textureComponent.region = gameplayAtlas.findRegion(RegionNames.BACKGROUND);

        Entity entity = engine.createEntity();
        entity.add(positionComponent);
        entity.add(dimensionComponent);
        entity.add(textureComponent);
        engine.addEntity(entity);
    }


    public void addPlayer( CircleBoundsComponent circleBoundsComponent,
                           MovementComponent movementComponent,
                           PlayerComponent playerComponent,
                           PositionComponent positionComponent,
                           WorldWrapComponent worldWrapComponent,
                           DimensionComponent dimensionComponent,
                           TextureComponent textureComponent) {
        // original position
       float x = (GameConfig.WORLD_WIDTH - GameConfig.PLAYER_SIZE) / 2f;
       positionComponent.x = x;
       float y = 1 - (GameConfig.PLAYER_SIZE / 2f);
       positionComponent.y = y;

        circleBoundsComponent.bounds.set(x, y, GameConfig.PLAYER_BOUNDS_RADIUS);

        dimensionComponent.height = GameConfig.PLAYER_SIZE;
        dimensionComponent.width = GameConfig.PLAYER_SIZE;

        textureComponent.region = gameplayAtlas.findRegion(RegionNames.PLAYER);

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

    // Create spawner, how check no obstacle on X? not going to.
    public void addLifeCollectable(float newLifeCollectableX, float newLifeCollectableY) {
        HittableCollectableComponent hittableCollectableComponent = engine.createComponent(HittableCollectableComponent.class);
        CleanUpComponent cleanUpComponent = engine.createComponent(CleanUpComponent.class);

        CircleBoundsComponent circleBoundsComponent = engine.createComponent(CircleBoundsComponent.class);
        MovementComponent  movementComponent = engine.createComponent(MovementComponent.class);
        PositionComponent positionComponent = engine.createComponent(PositionComponent.class);
        DimensionComponent dimensionComponent = engine.createComponent(DimensionComponent.class);
        TextureComponent textureComponent = engine.createComponent(TextureComponent.class);

        circleBoundsComponent.bounds.set(newLifeCollectableX, newLifeCollectableY, GameConfig.LIFE_COLLECTABLE_BOUNDS_RADIUS);

        movementComponent.xSpeed = 0f;
        movementComponent.ySpeed = -GameConfig.HARD_OBSTACLE_SPEED;

        positionComponent.x = newLifeCollectableX;
        positionComponent.y = newLifeCollectableY;

        dimensionComponent.width = GameConfig.LIFE_COLLECTABLE_SIZE;
        dimensionComponent.height = GameConfig.LIFE_COLLECTABLE_SIZE;

        textureComponent.region = gameplayAtlas.findRegion(RegionNames.LIFE_COLLECTABLE);

        Entity entity = engine.createEntity();
        entity.add(hittableCollectableComponent);
        entity.add(cleanUpComponent);

        entity.add(circleBoundsComponent);
        entity.add(movementComponent);
        entity.add(positionComponent);
        entity.add(dimensionComponent);
        entity.add(textureComponent);

        engine.addEntity(entity);

    }


    public void addObstacle(float newObstacleX, float newObstacleY){

        HittableCollectableComponent hittableCollectableComponent = engine.createComponent(HittableCollectableComponent.class);
        CleanUpComponent cleanUpComponent = engine.createComponent(CleanUpComponent.class);

        CircleBoundsComponent circleBoundsComponent = engine.createComponent(CircleBoundsComponent.class);
        MovementComponent  movementComponent = engine.createComponent(MovementComponent.class);
        PositionComponent positionComponent = engine.createComponent(PositionComponent.class);
        DimensionComponent dimensionComponent = engine.createComponent(DimensionComponent.class);
        TextureComponent textureComponent = engine.createComponent(TextureComponent.class);

        circleBoundsComponent.bounds.set(newObstacleX, newObstacleY, GameConfig.OBSTACLE_BOUNDS_RADIUS);

        movementComponent.xSpeed = 0f;
        movementComponent.ySpeed = -GameManager.INSTANCE.getGameDifficulty().getObjectSpeed();

        positionComponent.x = newObstacleX;
        positionComponent.y = newObstacleY;

        dimensionComponent.width = GameConfig.OBSTACLE_SIZE;
        dimensionComponent.height = GameConfig.OBSTACLE_SIZE;

        textureComponent.region = gameplayAtlas.findRegion(RegionNames.OBSTACLE);


        Entity entity = engine.createEntity();
        entity.add(hittableCollectableComponent);
        entity.add(cleanUpComponent);

        entity.add(circleBoundsComponent);
        entity.add(movementComponent);
        entity.add(positionComponent);
        entity.add(dimensionComponent);
        entity.add(textureComponent);

        engine.addEntity(entity);

    }
}
