import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Generator {

    private List<Double> randoms;

    public Generator(int x, Topology topology) throws IOException {
        if (topology.getRandomNumbers() != null && !topology.getRandomNumbers().isEmpty())
            this.randoms = topology.getRandomNumbers();
        else
            this.randoms = calculateCongruentLinear(1664525.0, x, 4294967295.0, 1013904223, topology.getRandomNumbersPerSeed());
        System.out.println("Números gerados: " + this.randoms.size());
        print();
    }

    public List<Double> getRandoms() {
        return randoms;
    }

    public List<Double> calculateCongruentLinear(double a, int x, double m, double c, int size) {
        randoms = new ArrayList<>();
        double previous = x;
        double xi;
        for (int i = 0; i < size; i++) {
            xi = ((a * previous) + c) % m;
            randoms.add(xi/m);
            previous = xi;
        }
        return randoms;
    }

    public double getNextDoubleBetween(double start, double end) {
        double x = (end - start) *  randoms.get(0) + start;
        randoms.remove(randoms.get(0));
        return x;
    }

    private void print() throws IOException {
        OutputStream os = new FileOutputStream("randoms.txt");
        OutputStreamWriter osw = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(osw);
        for (double n : this.randoms) {
            bw.write("- " + n + "\n");
        }
        bw.close();
    }

}