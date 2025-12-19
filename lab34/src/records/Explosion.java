package records;

public record Explosion(double power) {

    public double getPower() {
        return power;
    }
}
