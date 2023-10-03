package com.obstacleavoid.system;

import com.badlogic.ashley.systems.IntervalSystem;
import com.badlogic.gdx.math.MathUtils;
import com.obstacleavoid.common.GameManager;
import com.obstacleavoid.config.GameConfig;

public class ScoreSystem extends IntervalSystem
{

    public ScoreSystem()
    {
        super(GameConfig.PLAYER_SCORES_AFTER);
    }

    @Override
    protected void updateInterval( )
    {
        GameManager.INSTANCE.updateScore(MathUtils.random(1, 5));

    }
}
