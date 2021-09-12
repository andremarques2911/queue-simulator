import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Application {

    public static void main(String[] args) {

        if(args.length != 1) {
            System.out.println("Dever ser informado somente o caminho completo para o arquivo com a topologia das filas.");
            return;
        }

        Gson gson = new Gson();
        try {
            BufferedReader br = new BufferedReader(new FileReader(args[0]));
            Topology topology = gson.fromJson(br, Topology.class);
            Simulator simulator = new Simulator();
            simulator.runSimulation(topology);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }

    }

}
