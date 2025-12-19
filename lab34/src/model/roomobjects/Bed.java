package model.roomobjects;

import enums.PersonState;
import exceptions.AlreadyDestroyedException;
import exceptions.IllegalInteractionException;
import interfaces.Destroyable;
import interfaces.Flipable;
import interfaces.Sleepable;
import model.Person;
import model.RoomObject;

import java.util.Objects;

public class Bed extends RoomObject implements Sleepable, Flipable, Destroyable {
    private boolean flipped;
    private boolean occupied;
    private Person occupant;

    public Bed(String name) {
        super(name, 70 + Math.random() * 10);
        this.flipped = false;
        this.occupied = false;
        this.occupant = null;
    }

    // переопределение Sleepable
    public void lieDown(Person occupant) {
        if (occupied) {
            throw new IllegalInteractionException("Кровать уже занята.");
        }

        if (flipped) {
            throw new IllegalInteractionException("Нельзя лечь в перевёрнутую кровать.");
        }

        if (isDestroyed()) {
            throw new IllegalInteractionException("Ты правда хотел лечь в разрушенную кровать?");
        }

        this.occupied = true;
        this.occupant = occupant;
        occupant.setState(PersonState.SLEEPING);
        System.out.println(occupant.getName() + " лёг в кровать.");
    }

    public void getUp(Person occupant) {
        if (this.occupant != occupant) {
            throw new IllegalInteractionException("Этот персонаж не лежит в кровати -> не может встать.");
        }

        this.occupied = false;
        this.occupant = null;
        occupant.setState(PersonState.AWAKE);
        System.out.println(occupant.getName() + " перешёл в бодорствующее состояние.");
    }

    @Override
    public boolean isOccupied() {
        return occupied;
    }

    // переопределение Flipable
    @Override
    public void flip() {
        this.flipped = !this.flipped;
        System.out.println("Кровать перевернулась.");

        if (occupied) {
            System.out.println(occupant.getName() + " выкатился из неё на пол.");
            occupant.setState(PersonState.AWAKE);
            occupied = false;
            occupant = null;
        }
    }

    @Override
    public boolean isFlipped() {
        return flipped;
    }

    // переопределение Destroyable
    @Override
    public void takeDamage(double damage) throws AlreadyDestroyedException {
        if (isDestroyed()) {
            throw new AlreadyDestroyedException(
                    "Кровать уже разрушена."
            );
        }
        super.takeDamage(damage);
        if (damage >= 30) {
            flip();
        }
        if (isDestroyed()) {
            System.out.println("Кровать сломалась!");
        }
    }

    @Override
    public boolean isDestroyed() {
        return super.isDestroyed();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bed bed = (Bed) o;
        return flipped == bed.flipped &&
                occupied == bed.occupied &&
                Objects.equals(occupant, bed.occupant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flipped, occupied, occupant);
    }

    @Override
    public String toString() {
        if (isDestroyed()) {
            return "Разрушенная кровать";
        }

        StringBuilder description = new StringBuilder("Кровать");
        if (flipped) {
            description.append(" [перевёрнута, но не сломана]");
        }
        if (occupied) {
            description.append(" [занята персонажем ").append(occupant.getName()).append("]");
        }
        return description.toString();
    }
}