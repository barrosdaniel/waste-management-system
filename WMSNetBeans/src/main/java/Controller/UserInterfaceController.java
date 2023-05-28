/*
Central Queensland University
COIT12200 - Software Design & Development (2023 Term 1)
Campus: External
Assignment 3 - electronic Waste Management System
Student ID: 12184305
Student Name: Daniel Barros
*/
package Controller;

import View.FieldAction;
import View.OptionLoader;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class UserInterfaceController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        OptionLoader.loadStateComboBoxOptions(cbState);
        OptionLoader.loadCountryComboBoxOptions(cbCountry);
        OptionLoader.loadAddressTypeComboBoxOptions(cbAdressType);
        OptionLoader.loadYearComboBoxOptions(cbYear);
        OptionLoader.loadItemCategoryComboBoxOptions(cbItemCategory);
        inactivateAllFields();
        lblCancelled.setText("");
    }
    
    @FXML
    public void btnExitClick() {
        Platform.exit();
    }
    
    private void inactivateAllFields() {
        inactivateAllCustomerFields();
    }
    
    private void inactivateAllCustomerFields() {
        FieldAction.inactivateTextField(tfCustomerID);
        FieldAction.inactivateTextField(tfFirstName);
        FieldAction.inactivateTextField(tfLastName);
        FieldAction.inactivateTextField(tfMobile);
        FieldAction.inactivateTextField(tfEmail);
        FieldAction.inactivateTextField(tfCustomerAddressID);
        FieldAction.inactivateTextArea(taAddress);
        FieldAction.inactivateTextField(tfCurrentCustomer);
        FieldAction.inactivateTextField(tfTotalCustomers);
    }
/*  ==================================================================
    CUSTOMER
=================================================================== */
    @FXML
    private TextField tfCustomerID;
    @FXML
    private TextField tfFirstName;
    @FXML
    private TextField tfLastName;
    @FXML
    private TextField tfMobile;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfCustomerAddressID;
    @FXML
    private TextArea taAddress;
    @FXML
    private TextField tfCurrentCustomer;
    @FXML
    private TextField tfTotalCustomers;
    
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
