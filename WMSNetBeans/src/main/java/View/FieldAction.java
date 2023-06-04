package View;

import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FieldAction {
    public static void inactivateTextField(TextField tf) {
        tf.setEditable(false);
        tf.setStyle("-fx-control-inner-background: #F1F1F1;");
    }
    
    public static void activateTextField(TextField tf) {
        tf.setEditable(true);
        tf.setStyle("-fx-control-inner-background: #FFFFFF;");
    }
    
    public static void inactivateTextArea(TextArea ta) {
        ta.setEditable(false);
        ta.setStyle("-fx-control-inner-background: #F1F1F1;");
    }
    
    public static void activateTextArea(TextArea ta) {
        ta.setEditable(true);
        ta.setStyle("-fx-control-inner-background: #FFFFFF;");
    }
    
    public static void inactivateComboBox(ComboBox cb) {
        cb.setDisable(true);
        cb.setStyle("-fx-control-inner-background: #F1F1F1;");
    }
    
    public static void activateComboBox(ComboBox cb) {
        cb.setDisable(false);
        cb.setStyle("-fx-control-inner-background: #FFFFFF;");
    }
    
    public static void inactivateDatePicker(DatePicker dp){
        dp.setEditable(false);
        dp.setStyle("-fx-control-inner-background: #F1F1F1;");
    }
    
    public static void activateDatePicker(DatePicker dp){
        dp.setEditable(true);
        dp.setStyle("-fx-control-inner-background: #FFFFFF;");
    }
    
    public static void printTableHeaders(TextArea taCSRItems) {
        String headerString = String.format(
            "%-10s %-20s %-20s %-32s %-8s %n",
            "ID", "Category", "Type", "Description", "Quantity");
        headerString += String.format(
            "%-10s %-20s %-20s %-32s %-8s %n",
            "==========", 
            "====================", "====================", 
            "================================", "========");
        taCSRItems.setText(headerString);
    }
    
}
