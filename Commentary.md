# Commentary:

### Entity Factory missing until
*              cc603c7586cf56380ce907cd9f7f7dca48bc88d0 137b.
*  Components added to all created entities here.

### 141. Removing entity from engine == scheduled operation
* will be removed after the system in question finishes processing.

### 142. CollisionSystem
* Requires two families, instead of extending IteratingSystem, extends EntitySystem
* requires Intersector.overlaps(bounds, bounds);

### 143. HudRenderSystem
* Created a new viewport? remember to update it!
* No need for extra SpriteBatches, get the one from Game.
```java
public class GameScreen implements Screen
{
    @Override
    public void resize( int width, int height )
    {
        LOG.debug("GameScreen -> resize()");
        viewport.update(width, height, true);
        hudViewport.update(width, height, true);
    }
}
```