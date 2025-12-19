package model;

public class Calculations {
    private final String topic;
    private double result;
    private boolean calculated;

    public Calculations(String topic) {
        this.topic = topic;
        this.calculated = false;
    }

    public void setResult(double result) {
        this.result = result;
        this.calculated = true;
    }

    public String getTopic() { return topic; }

    public double getResult() {
        if (!calculated) {
            throw new IllegalStateException("Расчеты еще не выполнены!");
        }
        return result;
    }

    public boolean isCalculated() { return calculated; }
}