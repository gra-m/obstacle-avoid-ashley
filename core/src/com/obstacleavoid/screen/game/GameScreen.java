package com.obstacleavoid.screen.game;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.obstacleavoid.ObstacleAvoidGame;
import com.obstacleavoid.config.GameConfig;
import com.obstacleavoid.system.debug.GridRenderSystem;
import com.obstacleavoid.util.GdxUtils;

public class GameScreen implements Screen
{
    private static final Logger LOG = new Logger(GameScreen.class.getName(), Logger.DEBUG);
    private final ObstacleAvoidGame obstacleAvoidGame;
    private final AssetManager assetManager;

    private Viewport viewport;
    private ShapeRenderer renderer;
    private PooledEngine engine;
    private boolean shown;

    public GameScreen( ObstacleAvoidGame game ) {
        this.obstacleAvoidGame = game;
        this.assetManager = obstacleAvoidGame.getAssetManager();
    }

    @Override
    public void show()
    {
       LOG.debug("GameScreen -> show()");
       viewport = new FitViewport(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT);
       renderer = new ShapeRenderer(  );
       engine = new PooledEngine(  ); // takes care of pooling automatically

       addAllSystemsToEngine();

    }

    private void addAllSystemsToEngine( ) {
        engine.addSystem(new GridRenderSystem(viewport, renderer));
    }

    @Override
    public void render( float delta )
    {
        if (!shown) {
            LOG.debug("GameScreen -> render()");
            shown = true;
        }
        GdxUtils.clearScreen();
        engine.update(delta);
    }

    @Override
    public void resize( int width, int height )
    {
        LOG.debug("GameScreen -> resize()");
        viewport.update(width, height, true);
    }

    @Override
    public void pause()
    {
        LOG.debug("GameScreen -> pause()");
    }

    @Override
    public void resume()
    {
        LOG.debug("GameScreen -> resume()");
    }

    @Override
    public void hide()
    {
        LOG.debug("GameScreen -> hide()");
        dispose();
    }

    @Override
    public void dispose()
    {
        LOG.debug("GameScreen -> dispose()");
        renderer.dispose();
    }
}
