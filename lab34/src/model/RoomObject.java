package model;

import exceptions.AlreadyDestroyedException;
import interfaces.Destroyable;

public abstract class RoomObject implements Destroyable {
    private final String name;
    private final Health health;

    public RoomObject(String name, double maxHealth) {
        this.name = name;
        this.health = new Health(maxHealth);
    }

    @Override
    public void takeDamage(double damage) throws AlreadyDestroyedException {
        if (isDestroyed()) {
            throw new AlreadyDestroyedException(
                    "Объект '" + name + "' уже разрушен."
            );
        }
        health.reduceHealth(damage);
        System.out.printf("[%s получил урон %.1f. Здоровье: %.1f/%.1f]%n",
                name, damage, health.getCurrent(), health.getMax());

    }

    @Override
    public boolean isDestroyed() {
        return health.isZero();
    }

    public String getName() {
        return name;
    }

    public Health getHealth() {
        return health;
    }
}
