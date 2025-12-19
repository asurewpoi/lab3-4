package model.persons;

import model.Person;

import java.util.Objects;

public class Zvezdochkin extends Person {
    public Zvezdochkin() {
        super("Профессор Звёздочкин", 60 + Math.random() * 15);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Zvezdochkin that = (Zvezdochkin) o;
        return Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getClass());
    }

    @Override
    public String toString() {
        return String.format("Звёздочкин[здоровье=%.1f, состояние=%s]",
                getHealth().getCurrent(), getState());
    }
}