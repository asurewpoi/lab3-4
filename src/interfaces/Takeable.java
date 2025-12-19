package interfaces;

import exceptions.IllegalInteractionException;
import model.Person;

public interface Takeable   {
    void take(Person taker) throws IllegalInteractionException;
    void release(Person taker);
    boolean isTaken();
}
