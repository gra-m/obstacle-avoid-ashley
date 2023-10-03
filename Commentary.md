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
### 144. Collision Listener
* This is about creating an interface to save having so many game related fields needing to be sent to a system. Instead
* the CollisionListener Interface is implemented and sent to the system.
* After rendering is completed check for game over, exiting to a new screen before an active render is completed leads to crashes.
* Game reset below:

```java
import java.awt.desktop.ScreenSleepListener;

public class GameScreen implements Screen
{
    @Override
    public void render( float delta )
    {
        GdxUtils.clearScreen();
        engine.update(delta);

        if ( GameManager.INSTANCE.isGameOver( ) ) {
            obstacleAvoidGame.setScreen(new MenuScreen(obstacleAvoidGame));
        }
    }
}

```
