package BUS;

public class Utils {
    public static boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        return str.matches("-?\\d+(\\.\\d+)?");
    }
}

