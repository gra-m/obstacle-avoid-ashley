package com.obstacleavoid.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.Logger;
import com.obstacleavoid.common.Mappers;
import com.obstacleavoid.component.MovementComponent;
import com.obstacleavoid.component.PlayerComponent;
import com.obstacleavoid.config.GameConfig;

/**
 * Renders movement for entities that have Movement and Player components
 */
public class PlayerSystem extends IteratingSystem
{
    private static final Logger LOG = new Logger(PlayerSystem.class.getName( ), Logger.DEBUG);
    private static final Family FAMILY = Family.all(
            MovementComponent.class,
            PlayerComponent.class).get( );


    public PlayerSystem( )
    {
        super(FAMILY);
    }

    @Override
    public void update( float deltaTime )
    {
        super.update(deltaTime);
    }

    @Override
    protected void processEntity( Entity entity, float deltaTime )
    {
        MovementComponent movementComponent = Mappers.MOVEMENT_COMPONENT_MAPPER.get(entity);

        // reset to 0 every call, so stopped is default
        movementComponent.xSpeed = 0;

        if ( Gdx.input.isKeyPressed(Input.Keys.RIGHT) ) {
            movementComponent.xSpeed = GameConfig.MAX_PLAYER_X_SPEED;
        } else if ( Gdx.input.isKeyPressed(Input.Keys.LEFT) ) {
            movementComponent.xSpeed = GameConfig.MAX_PLAYER_X_SPEED;
        }

        LOG.debug("processEntity xSpeed = " + movementComponent.xSpeed);

    }
}
