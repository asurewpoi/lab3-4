package model;

public class Health{
    private double currentHealth;
    private final double maxHealth;

    public Health(double maxHealth) {
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
    }

    public void reduceHealth(double amount) {
        this.currentHealth -= amount;

        if (this.currentHealth < 0) {
            this.currentHealth = 0;
        }
    }

    public boolean isZero() {
        return currentHealth == 0;
    }

    public double getCurrent() {
        return currentHealth;
    }

    public double getMax() {
        return maxHealth;
    }
}
