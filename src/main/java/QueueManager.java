import model.Event;
import model.Network;
import model.Queue;
import util.Generator;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class QueueManager {
    final PriorityQueue<Event> scheduler = new PriorityQueue<>();
    final List<Queue> queues;
    final Generator generator;
    double globalTime = 0;

    public QueueManager(List<Queue> queues, Generator generator) {
        this.queues = queues;
        this.generator = generator;
    }

    public void processArrival(String queueName) {
        Queue queue = queues.stream().filter(q -> q.getName().equalsIgnoreCase(queueName)).findFirst().orElse(null);
        if (nonNull(queue)) {
            if (isNull(queue.getCapacity()) || queue.getCounter() < queue.getCapacity()) {
                queue.incrementCounter();
                if (queue.getCounter() <= queue.getServers()) {
                    this.scheduleMoveOrExit(queue, false);
                }
            } else if (nonNull(queue.getCapacity()) && queue.getCounter() >= queue.getCapacity()) {
                queue.incrementLostClients();
            }
            Event event = new Event(queueName, this.calculateTime(queue.getMinArrival(), queue.getMaxArrival()), Event.Type.ARRIVAL);
            scheduler.add(event);
        }
    }

    public void processMove(String queueName, String targetQueueName) {
        Queue queue = queues.stream().filter(q -> q.getName().equalsIgnoreCase(queueName)).findFirst().orElse(null);
        Queue targetQueue = queues.stream().filter(q -> q.getName().equalsIgnoreCase(targetQueueName)).findFirst().orElse(null);
        if (nonNull(queue)) {
            this.processExit(queueName, false);
            if (nonNull(targetQueue)) {
                if ((isNull(targetQueue.getCapacity()) || targetQueue.getCounter() < targetQueue.getCapacity())) {
                    targetQueue.incrementCounter();
                    if (targetQueue.getCounter() <= targetQueue.getServers()) {
                        Event event = new Event(targetQueue.getName(), true, this.calculateTime(targetQueue.getMinService(), targetQueue.getMaxService()), Event.Type.EXIT);
                        scheduler.add(event);
                    }
                } else if (nonNull(targetQueue.getCapacity()) && targetQueue.getCounter() >= targetQueue.getCapacity()) {
                    targetQueue.incrementLostClients();
                }
            }
        }
    }

    public void processExit(String queueName, boolean isSimpleExit) {
        Queue queue = queues.stream().filter(q -> q.getName().equalsIgnoreCase(queueName)).findFirst().orElse(null);
        if (nonNull(queue)) {
            queue.decrementCounter();
            if (queue.getCounter() >= queue.getServers()) {
                this.scheduleMoveOrExit(queue, isSimpleExit);
            }
        }
    }

    public void updateTimes(double eventTime) {
        double delta = eventTime - globalTime;
        queues.forEach(queue -> {
            if (!queue.getStates().isEmpty() && queue.getCounter() < queue.getStates().size() && queue.getStates().get(queue.getCounter()) != null) {
                double previous = queue.getStates().get(queue.getCounter());
                queue.getStates().set(queue.getCounter(), delta + previous);
            } else {
                queue.getStates().add(queue.getCounter(), delta);
            }
        });
        globalTime = eventTime;
    }

    private String getTargetQueueName(List<Network> networks) {
        if (nonNull(networks) && !networks.isEmpty()) {
            double sum = networks.stream().mapToDouble(Network::getProbability).sum();
            double exitProbability = 1 - sum;
            if (exitProbability > 0) {
                networks.add(new Network(null, exitProbability));
            }
            networks.sort(Comparator.comparingDouble(Network::getProbability).reversed());
            double randomValue = generator.getNextDouble();
            double totalValue = 0;
            for (Network n : networks) {
                if (randomValue >= totalValue && randomValue < totalValue + n.getProbability()) {
                    return n.getTarget();
                }
                totalValue += n.getProbability();
            }
        }

        return null;
    }

    private void scheduleMoveOrExit(Queue queue, boolean isSimpleExit) {
        String targetQueueName = this.getTargetQueueName(queue.getNetworks());
        Event event;
        if (!isSimpleExit && nonNull(targetQueueName)) {
            event = new Event(queue.getName(), targetQueueName, this.calculateTime(queue.getMinService(), queue.getMaxService()), Event.Type.MOVE);
        } else {
            event = new Event(queue.getName(), true, this.calculateTime(queue.getMinService(), queue.getMaxService()), Event.Type.EXIT);
        }
        scheduler.add(event);
    }

    private double calculateTime(double minService, double maxService) {
        return generator.getNextDoubleBetween(minService, maxService) + globalTime;
    }

    public double calculateProbability(double totalTime) {
        return (totalTime * 100) / globalTime;
    }
}