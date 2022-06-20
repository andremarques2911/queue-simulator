package model;

public class Event implements Comparable<Event> {
    private String queueName;
    private String targetQueueName;
    private boolean simpleExit;
    private double time;
    private Type type;

    public enum Type { ARRIVAL, MOVE, EXIT }

    public Event(String queueName, double time, Type type) {
        this.queueName = queueName;
        this.time = time;
        this.type = type;
    }

    public Event(String queueName, boolean simpleExit, double time, Type type) {
        this.queueName = queueName;
        this.simpleExit = simpleExit;
        this.time = time;
        this.type = type;
    }

    public Event(String queueName, String targetQueueName, double time, Type type) {
        this.queueName = queueName;
        this.targetQueueName = targetQueueName;
        this.time = time;
        this.type = type;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public boolean isSimpleExit() {
        return simpleExit;
    }

    public void setSimpleExit(boolean simpleExit) {
        this.simpleExit = simpleExit;
    }

    public String getTargetQueueName() {
        return targetQueueName;
    }

    public void setTargetQueueName(String targetQueueName) {
        this.targetQueueName = targetQueueName;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public int compareTo(Event e) {
        if (this.time < e.time) {
            return -1;
        }
        if (this.time > e.time) {
            return 1;
        }
        return 0;
    }
}
