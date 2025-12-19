package interfaces;
import model.Person;

public interface Sleepable {
    void lieDown(Person sleeper);
    void getUp(Person sleeper);
    boolean isOccupied();
}
