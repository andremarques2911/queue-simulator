package util;

import java.text.MessageFormat;

public class Formatter {
    public static String scale(double n, int scale) {
        return String.format("%." + scale + "f", n);
    }

    public static String format(String pattern, Object ... arguments) {
        return MessageFormat.format(pattern, arguments);
    }
}