package model.roomobjects;

import exceptions.AlreadyDestroyedException;
import model.RoomObject;

import java.util.Objects;

public class GlassWindow extends RoomObject {

    public GlassWindow() {
        super("Оконные стёкла", 30.0);  // стекло хрупкое
    }

    @Override
    public void takeDamage(double damage) throws AlreadyDestroyedException {
        super.takeDamage(damage);

        if (isDestroyed()) {
            System.out.println("Стекла из окон вылетели!");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GlassWindow that = (GlassWindow) o;
        return Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    @Override
    public String toString() {
        if (isDestroyed()) {
            return "Разбитые оконные стёкла";
        }
        return getName();
    }
}