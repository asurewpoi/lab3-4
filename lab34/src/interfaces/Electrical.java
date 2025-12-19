package interfaces;

import model.Person;

public interface Electrical {
    void turnOn(Person person);
    void turnOff(Person person);
    boolean isTurnedOn();
}
