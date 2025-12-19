package model.roomobjects;

import enums.PersonState;
import exceptions.AlreadyDestroyedException;
import exceptions.IllegalInteractionException;
import interfaces.Destroyable;
import interfaces.Takeable;
import model.Person;
import model.RoomObject;

import java.util.Objects;

public class Blanket extends RoomObject implements Takeable, Destroyable {
    private boolean taken;
    private Person taker;

    public Blanket() {
        super("Одеяло", 10 + Math.random() * 10);
        this.taken = false;
        this.taker = null;
    }

    // Переопределение Takeable
    @Override
    public void take(Person taker) throws IllegalInteractionException{
        if (taken) {
            throw new IllegalInteractionException("Одеяло уже взято. Отбирать невежливо!");
        }

        if (taker.getState() == PersonState.DEAD) {
            throw new IllegalInteractionException("Мертвецы не могут брать вещи.");
        }

        if (isDestroyed()) {
            throw new IllegalInteractionException("Одеяло порвано, нет смысла его брать.");
        }

        System.out.println("Одеяло взято персонажем " + taker.getName() + ".");
        this.taken = true;
        this.taker = taker;
    }

    @Override
    public void release(Person taker) {
        if (!taken) {
            throw new IllegalInteractionException("Одеяло не взято. Его нельзя бросить.");
        }
        System.out.println("Одеяло брошено персонажем " + taker.getName() + ".");
        this.taken = false;
        this.taker = null;
    }

    @Override
    public boolean isTaken() {
        return taken;
    }

    // Переопределение Destroyable для падения одеяла из-за взрыва
    @Override
    public void takeDamage(double damage) throws AlreadyDestroyedException {
        super.takeDamage(damage);
        if (isDestroyed()) {
            System.out.println("Одеяло порвалось!");
        }
        if (taken) {
            release(taker);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Blanket blanket = (Blanket) o;
        return taken == blanket.taken &&
                Objects.equals(taker, blanket.taker);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taken, taker);
    }

    @Override
    public String toString() {
        if (isDestroyed()) {
            return "Порванное одеяло";
        }

        StringBuilder description = new StringBuilder("Одеяло");
        if (taken) {
            description.append(" [взято персонажем ").append(taker.getName()).append("]");
        }
        return description.toString();
    }
}