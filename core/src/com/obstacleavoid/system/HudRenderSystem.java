package com.obstacleavoid.system;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.obstacleavoid.common.GameManager;
import com.obstacleavoid.config.GameConfig;

public class HudRenderSystem extends EntitySystem
{
    private static final Logger LOG = new Logger(HudRenderSystem.class.getName(), Logger.DEBUG);

    private final Viewport viewport;
    private final SpriteBatch batch;
    private final BitmapFont font;
    private final GlyphLayout glyphLayout = new GlyphLayout(  );

    public HudRenderSystem( Viewport viewport, SpriteBatch batch, BitmapFont font ) {
        this.viewport = viewport;
        this.batch = batch;
        this.font = font;
    }


    @Override
    public void update( float deltaTime )
    {
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);

        batch.begin();
        draw();
        batch.end();
    }

    private void draw( ) {
        String livesString = "LIVES: " + GameManager.INSTANCE.getLives();
        String scoreString = "SCORE: " + GameManager.INSTANCE.getScore();

        // draw liveString using batch 20 worldunits in from side at heighte ascertained by glyphlayouts height
        glyphLayout.setText(font, livesString);
        font.draw(batch, livesString,
                20,
                GameConfig.HUD_HEIGHT - glyphLayout.height);

        // draw scoreString
       glyphLayout.setText(font, scoreString);
       font.draw(batch, scoreString,
               GameConfig.HUD_WIDTH - (glyphLayout.width + 20),
               GameConfig.HUD_HEIGHT - glyphLayout.height);
    }
}
