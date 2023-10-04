package com.obstacleavoid.system;

import com.badlogic.ashley.systems.IntervalSystem;
import com.badlogic.gdx.math.MathUtils;
import com.obstacleavoid.common.EntityFactory;
import com.obstacleavoid.config.GameConfig;

public class LifeCollectableSpawnSystem extends IntervalSystem
{
private final EntityFactory factory;

    public LifeCollectableSpawnSystem( EntityFactory factory ) {
        super(GameConfig.LIFE_COLLECTABLES_SPAWN_EVERY);
        this.factory = factory;
    }

    @Override
    protected void updateInterval( )
    {
        float min = 0;
        float max = GameConfig.WORLD_WIDTH - GameConfig.LIFE_COLLECTABLE_SIZE;

        float lifeCollectableX = MathUtils.random(min, max);
        float lifeCollectableY = GameConfig.WORLD_HEIGHT + GameConfig.LIFE_COLLECTABLE_SIZE;

        factory.addLifeCollectable(lifeCollectableX, lifeCollectableY);


    }
}
