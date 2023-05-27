/*
Central Queensland University
COIT12200 - Software Design & Development (2023 Term 1)
Campus: External
Assignment 3 - electronic Waste Management System
Student ID: 12184305
Student Name: Daniel Barros
*/
package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class UserInterfaceController implements Initializable {

    @FXML
    public void btnExitClick() {
        Platform.exit();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
