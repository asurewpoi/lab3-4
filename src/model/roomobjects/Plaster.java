package model.roomobjects;

import exceptions.AlreadyDestroyedException;
import model.RoomObject;

public class Plaster extends RoomObject {
    public Plaster() {
        super("Штукатурка", 20 + Math.random() * 5);
    }

    @Override
    public void takeDamage(double damage) throws AlreadyDestroyedException {
        if (isDestroyed()) {
            throw new AlreadyDestroyedException(
                    "Штукатурка уже разрушена."
            );
        }
        super.takeDamage(damage);
        System.out.println("Штукатурка с грохотом посыпалась.");
        if (isDestroyed()) {
            System.out.println("Штукатурка полностью осыпалась!");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plaster plaster = (Plaster) o;
        return getName().equals(plaster.getName());
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }
    @Override
    public String toString() {
        if (isDestroyed()) {
            return "Штукатурка осыпалась";
        }
        return "Штукатурка на месте";
    }
}