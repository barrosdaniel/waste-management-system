package View;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FieldAction {
    public static void inactivateTextField(TextField tf) {
        tf.setEditable(false);
        tf.setStyle("-fx-control-inner-background: #F1F1F1;");
    }
    
    public static void inactivateTextArea(TextArea ta) {
        ta.setEditable(false);
        ta.setStyle("-fx-control-inner-background: #F1F1F1;");
    }
    
    public static void inactivateComboBox(ComboBox cb) {
        cb.setDisable(true);
        cb.setStyle("-fx-control-inner-background: #F1F1F1;");
    }
    
    
}
