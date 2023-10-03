# Commentary:

### Entity Factory missing until
*              cc603c7586cf56380ce907cd9f7f7dca48bc88d0 137b.
*  Components added to all created entities here.

### 141. Removing entity from engine == scheduled operation
* will be removed after the system in question finishes processing.

### 142. CollisionSystem
* Requires two families, instead of extending IteratingSystem, extends EntitySystem
