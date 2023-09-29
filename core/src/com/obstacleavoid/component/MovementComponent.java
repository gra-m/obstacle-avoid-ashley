package com.obstacleavoid.component;

import com.badlogic.ashley.core.Component;

/**
 * Obstacles have ySpeed, player has xSpeed
 */
public class MovementComponent implements Component
{
   public float xSpeed;
   public float ySpeed;
}
