package interfaces;

import exceptions.AlreadyDestroyedException;

public interface Destroyable {
    void takeDamage(double damage)  throws AlreadyDestroyedException;
    boolean isDestroyed();
}