import java.io.*;
import java.util.List;

public class Printer {

    static void print(List<Double> values, String fileName) throws IOException {
        OutputStream os = new FileOutputStream(fileName);
        OutputStreamWriter osw = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(osw);
        for (double n : values) {
            bw.write("- " + n + "\n");
        }
        bw.close();
    }

    static void createExampleFile() {
        String example = "{\n" +
                "\t\"seed\": 1,\n" +
                "\t\"randomNumbersPerSeed\": 100000,\n" +
                "\t\"arrival\": 2.0,\n" +
                "\t\"queues\": [\n" +
                "\t\t{\n" +
                "\t\t\t\"servers\": 1,\n" +
                "\t\t\t\"capacity\": 3,\n" +
                "\t\t\t\"minArrival\": 1.0,\n" +
                "\t\t\t\"maxArrival\": 2.0,\n" +
                "\t\t\t\"minService\": 3.0,\n" +
                "\t\t\t\"maxService\": 6.0\n" +
                "\t\t}\n" +
                "\t],\n" +
                "\t\"randomNumbers\": [\n" +
                "\t\t0.3276,\n" +
                "\t\t0.8851,\n" +
                "\t\t0.1643,\n" +
                "\t\t0.5542,\n" +
                "\t\t0.6813,\n" +
                "\t\t0.7221,\n" +
                "\t\t0.9881\n" +
                "\t]\n" +
                "}";
        try {
            OutputStream os = new FileOutputStream("example.json");
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write(example);
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}