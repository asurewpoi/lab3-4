package model.roomobjects;

import enums.PersonState;
import exceptions.AlreadyDestroyedException;
import exceptions.IllegalInteractionException;
import interfaces.Electrical;
import model.Person;
import model.RoomObject;

import java.util.Objects;

public class Electricity extends RoomObject implements Electrical {
    private boolean on;

    public Electricity() {
        super("Электричество", 80 + Math.random() * 30);
        this.on = true;
    }

    public void turnOn(Person person) throws IllegalInteractionException {
        if (person.getState() == PersonState.DEAD) {
            throw new IllegalInteractionException("Мертвецы не могут включать свет.");
        }
        this.on = !this.on;
        System.out.println("Свет был включен персонажем " + person.getName() + ".");
    }

    public void turnOff(Person person) {
        if (person.getState() == PersonState.DEAD) {
            throw new IllegalInteractionException("Мертвецы не могут выключать свет.");
        }
        this.on = !this.on;
        System.out.println("Свет был выключен персонажем " + person.getName() + ".");
    }

    @Override
    public boolean isTurnedOn() {
        return on;
    }

    @Override
    public void takeDamage(double damage) throws AlreadyDestroyedException {
        if (isDestroyed()) {
            throw new AlreadyDestroyedException(
                    "Электричество уже разрушено."
            );
        }
        super.takeDamage(damage);
        if (isDestroyed()) {
            System.out.println("Электричество выведено из строя!");
        }
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Electricity that = (Electricity) o;
        return on == that.on;
    }

    @Override
    public int hashCode() {
        return Objects.hash(on);
    }

    @Override
    public String toString() {
        return "Электричество[включено=" + on + "]";
    }
}
