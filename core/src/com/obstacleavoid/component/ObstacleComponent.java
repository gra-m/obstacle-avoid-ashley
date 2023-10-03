package com.obstacleavoid.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool.Poolable;

public class ObstacleComponent implements Component, Poolable
{
    public boolean hitAlready;

    @Override
    public void reset( )
    {
        hitAlready = false;
    }
}
