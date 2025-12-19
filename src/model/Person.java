package model;

import enums.PersonState;
import exceptions.AlreadyDestroyedException;
import interfaces.Destroyable;

public abstract class Person implements Destroyable {
    private final String name;
    private final Health health;
    private PersonState state;

    public Person(String name, double maxHealth) {
        this.name = name;
        this.health = new Health(maxHealth);
        this.state = PersonState.AWAKE;
    }

    @Override
    public void takeDamage(double damage) throws AlreadyDestroyedException {
        if (isDestroyed()) {
            throw new AlreadyDestroyedException(
                    "Персонаж '" + name + "' уже мёртв, нельзя нанести урон " + damage
            );
        }
        health.reduceHealth(damage);
        if (health.isZero()) {
            setState(PersonState.DEAD);
        } else if (state == PersonState.SLEEPING) {
            setState(PersonState.AWAKE);
        }
    }

    @Override
    public boolean isDestroyed() {
        return health.isZero();
    }

    public void calculate(Calculations calc) {
        setState(PersonState.CALCULATING);

        double result = 5 + Math.random() * 7;
        calc.setResult(result);

        System.out.println(name + " вычислил: " + calc.getTopic() + " = " + String.format("%.1f", result));
        setState(PersonState.AWAKE);
    }

    public String getName() {
        return name;
    }

    public Health getHealth() {
        return health;
    }

    public PersonState getState() {
        return state;
    }

    public void setState(PersonState state) {
        this.state = state;
    }
}