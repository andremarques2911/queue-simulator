package model;

public class Arrival {
    private String queueName;
    private double value;

    public Arrival(String queueName, double value) {
        this.queueName = queueName;
        this.value = value;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
