package model;

import model.Network;

import java.util.ArrayList;
import java.util.List;

public class Queue {
    private String name;
    private int servers;
    private int lostClients;
    private Integer capacity;
    private Double minArrival;
    private Double maxArrival;
    private Double minService;
    private Double maxService;
    private List<Network> networks;

    private List<Double> states = new ArrayList<>();
    private int counter;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getServers() {
        return servers;
    }

    public void setServers(int servers) {
        this.servers = servers;
    }

    public int getLostClients() {
        return lostClients;
    }

    public void incrementLostClients() {
        this.lostClients++;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Double getMinArrival() {
        return minArrival;
    }

    public void setMinArrival(Double minArrival) {
        this.minArrival = minArrival;
    }

    public Double getMaxArrival() {
        return maxArrival;
    }

    public void setMaxArrival(Double maxArrival) {
        this.maxArrival = maxArrival;
    }

    public Double getMinService() {
        return minService;
    }

    public void setMinService(Double minService) {
        this.minService = minService;
    }

    public Double getMaxService() {
        return maxService;
    }

    public void setMaxService(Double maxService) {
        this.maxService = maxService;
    }

    public List<Network> getNetworks() {
        return networks;
    }

    public void setNetworks(List<Network> networks) {
        this.networks = networks;
    }

    public List<Double> getStates() {
        return states;
    }

    public void setStates(List<Double> states) {
        this.states = states;
    }

    public void clear() {
        this.counter = 0;
        this.lostClients = 0;
        this.states.clear();
    }

    public int getCounter() {
        return counter;
    }

    public void incrementCounter() {
        this.counter++;
    }

    public void decrementCounter() {
        this.counter--;
    }
}
