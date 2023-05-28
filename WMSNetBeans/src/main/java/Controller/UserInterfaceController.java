/*
Central Queensland University
COIT12200 - Software Design & Development (2023 Term 1)
Campus: External
Assignment 3 - electronic Waste Management System
Student ID: 12184305
Student Name: Daniel Barros
*/
package Controller;

import View.OptionLoader;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class UserInterfaceController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        OptionLoader.loadStateComboBoxOptions(cbState);
        OptionLoader.loadCountryComboBoxOptions(cbCountry);
        OptionLoader.loadAddressTypeComboBoxOptions(cbAdressType);
        OptionLoader.loadYearComboBoxOptions(cbYear);
        OptionLoader.loadItemCategoryComboBoxOptions(cbItemCategory);
        lblCancelled.setText("");
    }
    
    @FXML
    public void btnExitClick() {
        Platform.exit();
    }
    
/*  ==================================================================
    CUSTOMER
=================================================================== */
    
    
    
/*  ==================================================================
    ADDRESS
=================================================================== */
    @FXML
    private ComboBox cbState;
    @FXML
    private ComboBox cbCountry;
    @FXML
    private ComboBox cbAdressType;
    @FXML
    private ComboBox cbYear;
    
/*  ==================================================================
    COLLECTION SERVICE REQUEST - CSR
=================================================================== */
    @FXML
    private Label lblCancelled;
    
    @FXML
    private ComboBox cbItemCategory;
    @FXML
    private ComboBox cbItemType;
    
    @FXML
    public void itemCategorySelect() {
        OptionLoader.loadItemTypeComboBoxOptions(cbItemCategory, 
            cbItemType);
    }

    
}
