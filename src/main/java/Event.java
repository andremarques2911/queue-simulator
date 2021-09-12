public class Event implements Comparable<Event> {

    private double time;
    private Type type;

    public enum Type { ARRIVAL, EXIT }

    public Event(double time, Type type) {
        this.time = time;
        this.type = type;
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
