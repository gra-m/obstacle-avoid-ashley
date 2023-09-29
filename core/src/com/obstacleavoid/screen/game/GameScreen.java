package com.obstacleavoid.screen.game;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.obstacleavoid.ObstacleAvoidGame;
import com.obstacleavoid.common.EntityFactory;
import com.obstacleavoid.config.GameConfig;
import com.obstacleavoid.system.debug.DebugCameraSystem;
import com.obstacleavoid.system.debug.GridRenderSystem;
import com.obstacleavoid.util.GdxUtils;

public class GameScreen implements Screen
{
    private static final Logger LOG = new Logger(GameScreen.class.getName(), Logger.DEBUG);
    private final ObstacleAvoidGame obstacleAvoidGame;
    private final AssetManager assetManager;

    private OrthographicCamera camera;
    private Viewport viewport;
    private ShapeRenderer renderer;
    private PooledEngine engine;
    private boolean shown;
    private EntityFactory entityFactory;

    public GameScreen( ObstacleAvoidGame game ) {
        this.obstacleAvoidGame = game;
        this.assetManager = obstacleAvoidGame.getAssetManager();
    }

    @Override
    public void show()
    {
       LOG.debug("GameScreen -> show()");
       camera = new OrthographicCamera(  );
       viewport = new FitViewport(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT, camera);
       renderer = new ShapeRenderer(  );
       engine = new PooledEngine(  ); // takes care of pooling automatically
       entityFactory = new EntityFactory(engine);
       addAllSystemsToEngine();

       entityFactory.addPlayer();
    }

    // system priorities on update methods in based on order added OR super(int) to EntitySystem lower# higher priority
    private void addAllSystemsToEngine( ) {
        // --> utility/void systems:
        engine.addSystem(new GridRenderSystem(viewport, renderer));
        engine.addSystem(new DebugCameraSystem(camera, GameConfig.WORLD_CENTER_X, GameConfig.WORLD_CENTER_Y));
    }

    @Override
    public void render( float delta )
    {
        if (!shown) {
            LOG.debug("GameScreen -> render()\n\t\t\t\t\t\t\t\t\t\t\t\t\t\tengine.update()");
            LOG.debug("entities size = " + engine.getEntities().size());
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
