package com.obstacleavoid.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool.Poolable;

public class HittableCollectableComponent implements Component, Poolable
{
    public boolean hitOrCollectedAlready;

    @Override
    public void reset( )
    {
        hitOrCollectedAlready = false;
    }
}
