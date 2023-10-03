package com.obstacleavoid.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

/**
 * Obstacles have ySpeed, player has xSpeed
 */
public class MovementComponent implements Component, Pool.Poolable
{
   public float xSpeed;
   public float ySpeed;

   @Override
   public void reset( )
   {
      xSpeed = 0f;
      ySpeed = 0f;
   }
}
