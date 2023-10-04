package com.obstacleavoid.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.obstacleavoid.common.Mappers;
import com.obstacleavoid.component.DimensionComponent;
import com.obstacleavoid.component.PositionComponent;
import com.obstacleavoid.component.TextureComponent;

// Instead of extending IteratingSystem, we will create entity array for ourselves
public class RenderSystem extends EntitySystem
{
    private static final Family FAMILY = Family.all(
            TextureComponent.class,
            PositionComponent.class,
            DimensionComponent.class
    ).get();
    private final Viewport viewport;
    private final SpriteBatch batch;

    private Array< Entity > renderQueue = new Array<>( );

    public RenderSystem( Viewport viewport, SpriteBatch batch )
    {
        this.viewport = viewport;
        this.batch = batch;
    }

    @Override
    public void update( float deltaTime )
    {
        ImmutableArray<Entity> entities = getEngine().getEntitiesFor(FAMILY);
        renderQueue.addAll(entities.toArray());

        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);

        batch.begin();
       
        draw();

        batch.end();

        renderQueue.clear();// cleared every tick

    }

    private void draw( ) {
        for(Entity entity: renderQueue) {
            PositionComponent positionComponent = Mappers.POSITION_COMPONENT_MAPPER.get(entity);
            DimensionComponent dimensionComponent = Mappers.DIMENSION_COMPONENT_MAPPER.get(entity);
            TextureComponent textureComponent = Mappers.TEXTURE_COMPONENT_MAPPER.get(entity);

            batch.draw(textureComponent.region, positionComponent.x, positionComponent.y, dimensionComponent.width,
                    dimensionComponent.height);


        }
        
    }
}
