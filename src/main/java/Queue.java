import java.util.ArrayList;
import java.util.List;

public class Queue {

    private int servers;
    private int capacity;
    private double minArrival;
    private double maxArrival;
    private double minService;
    private double maxService;
    private List<Double> states = new ArrayList<>();
    private int counter;

    public int getServers() {
        return servers;
    }

    public void setServers(int servers) {
        this.servers = servers;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getMinArrival() {
        return minArrival;
    }

    public void setMinArrival(double minArrival) {
        this.minArrival = minArrival;
    }

    public double getMaxArrival() {
        return maxArrival;
    }

    public void setMaxArrival(double maxArrival) {
        this.maxArrival = maxArrival;
    }

    public double getMinService() {
        return minService;
    }

    public void setMinService(double minService) {
        this.minService = minService;
    }

    public double getMaxService() {
        return maxService;
    }

    public void setMaxService(double maxService) {
        this.maxService = maxService;
    }

    public List<Double> getStates() {
        return states;
    }

    public void setStates(List<Double> states) {
        this.states = states;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

}
