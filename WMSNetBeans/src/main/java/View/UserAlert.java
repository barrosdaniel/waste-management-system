/*
Central Queensland University
COIT12200 - Software Design & Development (2023 Term 1)
Campus: External
Assignment 3 - electronic Waste Management System
Student ID: 12184305
Student Name: Daniel Barros
*/
package View;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

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
    
    public static ButtonType displayConfirmationAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        return alert.showAndWait().orElse(ButtonType.CANCEL);
    }
}
