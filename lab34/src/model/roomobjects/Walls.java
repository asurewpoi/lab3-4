package model.roomobjects;

import exceptions.AlreadyDestroyedException;
import model.RoomObject;

public class Walls extends RoomObject {

    public Walls() {
        super("Стены комнаты", 160  + Math.random() * 50);
    }

    @Override
    public void takeDamage(double damage) throws AlreadyDestroyedException {
        if (isDestroyed()) {
            throw new AlreadyDestroyedException(
                    "Стены уже разрушены."
            );
        }
        super.takeDamage(damage);
        System.out.println("Стены комнаты затряслись.");
        if (isDestroyed()) {
            System.out.println("Стены потрескались и вот-вот рухнут!");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Walls walls = (Walls) o;
        return getName().equals(walls.getName());
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }

    @Override
    public String toString() {
        if (isDestroyed()) {
            return "Павшие стены";
        }
        return getName();
    }
}