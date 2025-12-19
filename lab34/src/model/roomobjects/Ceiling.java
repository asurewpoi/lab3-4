package model.roomobjects;

import exceptions.AlreadyDestroyedException;
import model.RoomObject;

import java.util.Objects;

public class Ceiling extends RoomObject {
    private final Plaster plaster;

    public Ceiling() {
        super("Потолок", 150 + Math.random() * 50);
        this.plaster = new Plaster();
    }

    @Override
    public void takeDamage(double damage) throws AlreadyDestroyedException {
        if (isDestroyed()) {
            throw new AlreadyDestroyedException(
                    "Потолок уже разрушен."
            );
        }

        if (plaster.isDestroyed()) {
            super.takeDamage(damage);
        } else {
            plaster.takeDamage(damage);
        }

        if (isDestroyed()) {
            System.out.println("Потолок упал!");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ceiling ceiling = (Ceiling) o;
        return Objects.equals(plaster, ceiling.plaster);
    }

    @Override
    public int hashCode() {
        return Objects.hash(plaster);
    }
    @Override
    public String toString() {
        if (isDestroyed()) {
            return "Потолок (пал)";
        }
        return getName();
    }
}
