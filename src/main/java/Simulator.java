
import java.io.IOException;
import java.util.PriorityQueue;

public class Simulator {
    final PriorityQueue<Event> scheduler = new PriorityQueue<>();
    private final QueueManager queueManager = new QueueManager(scheduler);

    public void runSimulation(Topology topology) throws IOException {
        Queue queue = topology.getQueues().get(0);
        int seed = topology.getSeed();
        Generator generator = new Generator(seed, topology);
        System.out.println("*************************************************************");
        System.out.println("Semente: " + seed);
        System.out.println("Fila: (G/G/" + queue.getServers() + "/" + queue.getCapacity() + ")");
        System.out.println("Chegada: " + queue.getMinArrival() + " ... " + queue.getMaxArrival());
        System.out.println("Atendimento: " + queue.getMinService() + " ... " + queue.getMaxService());
        System.out.println("*************************************************************");

        start(topology, queue, generator);

        System.out.println("Estado \tTempo \t\t\tProbabilidade");
        for(int i = 0; i < queue.getStates().size(); i++) {
            Double state = queue.getStates().get(i);
            System.out.println(i + "\t\t" + Formatter.format(state, 4) + "\t\t\t" + Formatter.format(queueManager.calculateProbability(state), 2) + "%");
        }
        System.out.println("\nTotal de clientes perdidos: " + queueManager.lostClients);
        System.out.println("\nTempo total de execucao: " + queue.getStates().stream().mapToDouble(x -> x).sum());
        System.out.println("=============================================================");
    }

    private void start(Topology topology, Queue queue, Generator generator) {
        queueManager.scheduler.add(new Event(topology.getArrival(), Event.Type.ARRIVAL));
        while (!generator.getRandoms().isEmpty()) {
            if (queueManager.scheduler.element().getType().equals(Event.Type.ARRIVAL)) {
                queueManager.processArrival(queue, generator, queueManager.scheduler.element().getTime());
            } else {
                queueManager.processExit(queue, generator, queueManager.scheduler.element().getTime());
            }
            queueManager.scheduler.remove();
        }
    }

}