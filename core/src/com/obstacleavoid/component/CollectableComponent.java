package com.obstacleavoid.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool.Poolable;

@Deprecated
public class CollectableComponent implements Component, Poolable
{
    public boolean collectedAlready;

    @Override
    public void reset( )
    {
        collectedAlready = false;
    }
}
