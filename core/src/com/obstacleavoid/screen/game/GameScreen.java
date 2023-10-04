package com.obstacleavoid.screen.game;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.obstacleavoid.ObstacleAvoidGame;
import com.obstacleavoid.assets.AssetDescriptors;
import com.obstacleavoid.common.EntityFactory;
import com.obstacleavoid.common.GameManager;
import com.obstacleavoid.component.*;
import com.obstacleavoid.config.GameConfig;
import com.obstacleavoid.screen.menu.MenuScreen;
import com.obstacleavoid.system.*;
import com.obstacleavoid.system.collision.CollisionListener;
import com.obstacleavoid.system.collision.CollisionSystem;
import com.obstacleavoid.system.debug.DebugCameraSystem;
import com.obstacleavoid.system.debug.DebugRenderSystem;
import com.obstacleavoid.system.debug.GridRenderSystem;
import com.obstacleavoid.system.debug.WorldWrapSystem;
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
    private Sound hit;

    private Viewport hudViewport;
    private BitmapFont font;


    public GameScreen( ObstacleAvoidGame game ) {
        this.obstacleAvoidGame = game;
        this.assetManager = obstacleAvoidGame.getAssetManager();
    }

    CollisionListener listener = new CollisionListener( )
    {
        @Override
        public void hitObstacle( )
        {
            GameManager.INSTANCE.decrementLives();
            hit.play();
            
            if ( GameManager.INSTANCE.isGameOver( ) ) {
                GameManager.INSTANCE.updateHighScore();
            } else {
                engine.removeAllEntities();
                createPlayerComponentsAndAddPlayer();
            }
                

        }
    };

    @Override
    public void show()
    {
       LOG.debug("GameScreen -> show()");
       camera = new OrthographicCamera(  );
       viewport = new FitViewport(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT, camera);
       hudViewport = new FitViewport(GameConfig.HUD_WIDTH, GameConfig.HUD_HEIGHT);
       font = assetManager.get(AssetDescriptors.UI_FONT_32);
       hit = assetManager.get(AssetDescriptors.CRASH_WAV);


       renderer = new ShapeRenderer(  );
       engine = new PooledEngine(  ); // takes care of pooling automatically
       entityFactory = new EntityFactory(engine, assetManager);
       addAllSystemsToEngine();

       // added so components are shown in calls
       createPlayerComponentsAndAddPlayer();
    }

    private void createPlayerComponentsAndAddPlayer( ) {

        entityFactory.addPlayer(
                engine.createComponent(CircleBoundsComponent.class),
                engine.createComponent(MovementComponent.class),
                engine.createComponent(PlayerComponent.class),
                engine.createComponent(PositionComponent.class),
                engine.createComponent(WorldWrapComponent.class),
                engine.createComponent(DimensionComponent.class),
                engine.createComponent(TextureComponent.class));

    }

    // system priorities on update methods in based on order added OR super(int) to EntitySystem lower# higher priority
    private void addAllSystemsToEngine( ) {
        // --> utility/void systems:
        engine.addSystem(new DebugCameraSystem(camera, GameConfig.WORLD_CENTER_X, GameConfig.WORLD_CENTER_Y));
        engine.addSystem(new PlayerSystem());
        engine.addSystem(new MovementSystem());
        engine.addSystem(new WorldWrapSystem(viewport));
        engine.addSystem(new BoundsSystem());
        engine.addSystem(new ObstacleSpawnSystem(entityFactory));
        engine.addSystem(new CleanUpSystem());
        engine.addSystem(new CollisionSystem(listener));
        engine.addSystem(new ScoreSystem());

        // render order
        engine.addSystem(new RenderSystem(viewport, obstacleAvoidGame.getSpriteBatch()));
        engine.addSystem(new GridRenderSystem(viewport, renderer));
        engine.addSystem(new DebugRenderSystem(viewport, renderer));
        engine.addSystem((new HudRenderSystem(hudViewport, obstacleAvoidGame.getSpriteBatch(), font)));
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

        if ( GameManager.INSTANCE.isGameOver( ) ) {
            GameManager.INSTANCE.reset();
            obstacleAvoidGame.setScreen(new MenuScreen(obstacleAvoidGame));
        }
    }

    @Override
    public void resize( int width, int height )
    {
        LOG.debug("GameScreen -> resize()");
        viewport.update(width, height, true);
        hudViewport.update(width, height, true);
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
