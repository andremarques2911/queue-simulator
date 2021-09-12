import java.util.PriorityQueue;

public class QueueManager {
    final PriorityQueue<Event> scheduler;
    int lostClients = 0;
    int queueManager = 0;
    double globalTime = 0;

    public QueueManager(PriorityQueue<Event> scheduler) {
        this.scheduler = scheduler;
    }

    public void processArrival(Queue queue, Generator generator, double eventTime) {
        updateTimes(eventTime, queue);
        if (queueManager < queue.getCapacity()) {
            queueManager++;
            if (queueManager <= queue.getServers()) {
                Event event = new Event(generator.getNextDoubleBetween(queue.getMinService(), queue.getMaxService()) + globalTime, Event.Type.EXIT);
                scheduler.add(event);
            }
        } else {
            lostClients++;
        }
        Event event = new Event(generator.getNextDoubleBetween(queue.getMinArrival(), queue.getMaxArrival()) + globalTime, Event.Type.ARRIVAL);
        scheduler.add(event);
    }

    public void processExit(Queue queue, Generator generator, double eventTime) {
        updateTimes(eventTime, queue);
        queueManager--;
        if (queueManager >= queue.getServers()) {
            Event event = new Event(generator.getNextDoubleBetween(queue.getMinService(), queue.getMaxService()) + globalTime, Event.Type.EXIT);
            scheduler.add(event);
        }
    }

    public double calculateProbability(double totalTime) {
        return (totalTime * 100) / globalTime;
    }

    void updateTimes(double eventTime, Queue queue) {
        double delta = eventTime - globalTime;
        if (!queue.getStates().isEmpty() && queueManager < queue.getStates().size() && queue.getStates().get(queueManager) != null) {
            double previous = queue.getStates().get(queueManager);
            queue.getStates().set(queueManager, delta + previous);
        } else {
            queue.getStates().add(queueManager, delta);
        }
        globalTime = eventTime;
    }
}