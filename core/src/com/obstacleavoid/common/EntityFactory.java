package com.obstacleavoid.common;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.obstacleavoid.component.CircleBoundsComponent;
import com.obstacleavoid.component.MovementComponent;
import com.obstacleavoid.component.PlayerComponent;
import com.obstacleavoid.component.PositionComponent;
import com.obstacleavoid.config.GameConfig;
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
                           PositionComponent positionComponent ) {
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
        engine.addEntity(entity);
    }
}
