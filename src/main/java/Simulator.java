
import model.Arrival;
import model.Event;
import model.Queue;
import model.Topology;
import util.Formatter;
import util.Generator;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.*;

public class Simulator {
    public void runSimulation(Topology topology) throws IOException {
        List<Double> times = new ArrayList<>();
        for (int i = 0; i < topology.getSeeds().size(); i++) {
            int seed = topology.getSeeds().get(i);
            System.out.println("=============================================================");
            System.out.println(Formatter.format("Simulacao: {0}\nSemente: {1}", i + 1, seed));
            System.out.println("=============================================================");

            List<Queue> queues = topology.getQueues();
            queues.forEach(Queue::clear);

            Generator generator = new Generator(seed, topology);

            QueueManager queueManager = new QueueManager(queues, generator);

            topology.getArrivals().forEach(arrival -> addArrivalInScheduler(queueManager, arrival));

            while (!generator.getRandoms().isEmpty()) {
                try {
                    Event event = queueManager.scheduler.element();
                    queueManager.updateTimes(event.getTime());
                    if (event.getType().equals(Event.Type.ARRIVAL)) {
                        queueManager.processArrival(event.getQueueName());
                    } else if (event.getType().equals(Event.Type.MOVE)) {
                        queueManager.processMove(event.getQueueName(), event.getTargetQueueName());
                    } else {
                        queueManager.processExit(event.getQueueName(), event.isSimpleExit());
                    }
                    queueManager.scheduler.remove();
                } catch (Exception e) {
//                    System.err.println(e.getMessage());
                }
            }

            queues.forEach(queue -> printQueueDetails(queueManager, queue));

            times.add(queues.get(0).getStates().stream().mapToDouble(x -> x).sum());
        }

        System.out.println("=============================================================");
        System.out.println("Tempo medio de execucao: " + Formatter.scale(times.stream().mapToDouble(Double::doubleValue).average().orElse(0), 4));
        System.out.println("=============================================================\n");
    }

    private void printQueueDetails(QueueManager queueManager, Queue queue) {
        System.out.println("*************************************************************");
        System.out.println("Fila: " + queue.getName() + MessageFormat.format(" (G/G/{0}{1})", queue.getServers(), nonNull(queue.getCapacity()) ? "/" + queue.getCapacity() : ""));
        if (nonNull(queue.getMinArrival()) || nonNull(queue.getMaxArrival())) {
            System.out.println(MessageFormat.format("Chegada: {0} ... {1}", Formatter.scale(queue.getMinArrival(), 2), Formatter.scale(queue.getMaxArrival(), 2)));
        }
        if (nonNull(queue.getMinService()) || nonNull(queue.getMaxService())) {
            System.out.println(MessageFormat.format("Atendimento: {0} ... {1}", Formatter.scale(queue.getMinService(), 2), Formatter.scale(queue.getMaxService(), 2)));
        }
        System.out.println("*************************************************************");

        System.out.println("Estado \t\tTempo \t\t\t\t\tProbabilidade");
        for(int i = 0; i < queue.getStates().size(); i++) {
            Double state = queue.getStates().get(i);
            System.out.println(i + "\t\t\t" + Formatter.scale(state, 4) + "\t\t\t\t" + Formatter.scale(queueManager.calculateProbability(state), 2) + "%");
        }
        System.out.println("\nTotal de clientes perdidos: " + queue.getLostClients());
        System.out.println();
    }

    private void addArrivalInScheduler(QueueManager queueManager, Arrival arrival) {
        queueManager.scheduler.add(new Event(arrival.getQueueName(), arrival.getValue(), Event.Type.ARRIVAL));
    }
}