package model.persons;

import model.Person;

import java.util.Objects;

public class Znaika extends Person {
    public Znaika() {
        super("Знайка", 70 + Math.random() * 20);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Znaika znaika = (Znaika) o;
        return Objects.equals(getName(), znaika.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getClass());
    }

    @Override
    public String toString() {
        return String.format("Знайка[здоровье=%.1f, состояние=%s]",
                getHealth().getCurrent(), getState());
    }
}
