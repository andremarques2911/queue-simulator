public class Formatter {

    public static String format(double n, int scale) {
        return String.format("%." + scale + "f", n);
    }

}