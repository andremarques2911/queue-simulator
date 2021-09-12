import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Application {

    public static void main(String[] args) {

        if(args.length != 1) {
            help();
            return;
        }
        switch (args[0]) {
            case "help":
                help();
                break;
            case "example":
                Printer.createExampleFile();
                break;
            default:
                start(args[0]);
        }
    }

    private static void start(String arg) {
        Gson gson = new Gson();
        try {
            BufferedReader br = new BufferedReader(new FileReader(arg));
            Topology topology = gson.fromJson(br, Topology.class);
            Simulator simulator = new Simulator();
            simulator.runSimulation(topology);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    private static void help() {
        System.out.println("Guia do usuario");
        System.out.println("\nExecutar simulacao:");
        System.out.println("\tjava -jar queue-simulator.jar C:\\example.json");
        System.out.println("\nBaixar modelo de exemplo:");
        System.out.println("\tjava -jar queue-simulator.jar example");
        System.out.println("\nAjuda:");
        System.out.println("\tjava -jar queue-simulator.jar help");
        System.out.println("\nOBS: Se for enviada uma lista randoms manual nao sera feita a geracao dos numeros automaticamente utilizando utlizando o metodo de congruencia linear");
    }
}
