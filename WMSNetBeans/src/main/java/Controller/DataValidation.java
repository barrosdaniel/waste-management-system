package Controller;


public class DataValidation {
    public static boolean isEmpty(String inputString) {
        if (inputString.isEmpty() || inputString.isBlank() ||
                        inputString == null) {
            return true;
        } else {
            return false;
        }
    }
}
