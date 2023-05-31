package View;

import javafx.scene.control.Alert;

public class UserAlert {
    public static void renderAlert(String title, String message, Alert alert) {
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    public static void displayErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        renderAlert(title, message, alert);
    }
    
    public static void displayWarningAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        renderAlert(title, message, alert);
    }
    
    public static void displayInformationAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        renderAlert(title, message, alert);
    }
}
