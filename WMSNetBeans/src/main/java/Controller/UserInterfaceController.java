/*
Central Queensland University
COIT12200 - Software Design & Development (2023 Term 1)
Campus: External
Assignment 3 - electronic Waste Management System
Student ID: 12184305
Student Name: Daniel Barros
*/
package Controller;

import Model.Customer;
import View.FieldAction;
import View.OptionLoader;
import View.UserAlert;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
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
        loadAllCustomersFromDB();
    }
    
    @FXML
    public void btnExitClick() {
        Platform.exit();
    }
    
    private void inactivateAllFields() {
        lblCancelled.setText("");
        inactivateAllCustomerFields();
        inactivateAllAddressFields();
        inactivateAllCSRFields();
    }
    
/*  ==================================================================
    CUSTOMER
=================================================================== */
    private ArrayList<Customer> customersList= new ArrayList();
    private ArrayList<Customer> tempCustomersList= new ArrayList();
    private int currentCustomer;
    private int totalCustomers;
    private DataSet customerSet;
    @FXML
    private TextField tfCustomerID;
    private String customerID;
    @FXML
    private TextField tfFirstName;
    private String firstName;
    @FXML
    private TextField tfLastName;
    private String lastName;
    @FXML
    private TextField tfMobile;
    private String mobile;
    @FXML
    private TextField tfEmail;
    private String email;
    @FXML
    private TextField tfCustomerAddressID;
    private String customerAddressID;
    @FXML
    private TextArea taAddress;
    @FXML
    private TextField tfCurrentCustomer;
    @FXML
    private TextField tfTotalCustomers;
    
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
    
    private void loadAllCustomersFromDB(){
        try (Connection connection = DatabaseHandler.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * "
                    + "FROM customers "
                    + "ORDER BY customer_id;"
            );
            ResultSet queryResults = statement.executeQuery();
            while (queryResults.next()) {
                customerID = queryResults.getString("customer_id");
                firstName = queryResults.getString("first_name");
                lastName = queryResults.getString("last_name");
                mobile = queryResults.getString("mobile");
                email = queryResults.getString("email");
                customerAddressID = queryResults.getString("customer_adress_id");
                Customer newCustomer = makeNewCustomerObject();
                customersList.add(newCustomer);
            }
            statement.close();
            queryResults.close();
            connection.close();
        } catch (Exception e) {
            UserAlert.displayErrorAlert("Database Error", "ERROR: Unable to load "
                    + "customers from the database.");
        }
    }
    
    private Customer makeNewCustomerObject() {
        return new Customer(
                customerID,
                firstName,
                lastName,
                mobile,
                email,
                customerAddressID
        );
    }
    
    @FXML
    public void btnViewAllCustomersClick() {
        customerSet = DataSet.FULL_SET;
        inactivateAllCustomerFields();
        if (customersList.size() > 0) {
            currentCustomer = 0;
            totalCustomers = customersList.size();
            displayCustomerRecord(currentCustomer);
            refreshCustomerPaginationNumbers();
        }
    }
    
    private void displayCustomerRecord(int index) {
        Customer customer;
        if (customerSet.equals(DataSet.FULL_SET)) {
            customer = customersList.get(index);
        } else {
            customer = tempCustomersList.get(index);
        }
        tfCustomerID.setText(customer.getCustomerID());
        tfFirstName.setText(customer.getFirstName());
        tfLastName.setText(customer.getLastName());
        tfMobile.setText(customer.getMobile());
        tfEmail.setText(customer.getEmail());
        tfCustomerAddressID.setText(customer.getCustomerAddressID());
        String addressString = getAddressString(tfCustomerAddressID.getText());
        taAddress.setText(addressString);
    }
    
    private String getAddressString(String customerAddressID) {
        String addressString = "3 Pistachio Way\n\n" 
                + "Eglinton WA 6034\n"
                + "Australia";
        return addressString;
    }
    
    private void refreshCustomerPaginationNumbers() {
        tfCurrentCustomer.setText(currentCustomer + 1 + "");
        tfTotalCustomers.setText(totalCustomers + "");
    }
    
/*  ==================================================================
    ADDRESS
=================================================================== */
    @FXML
    private TextField tfAddressID;
    @FXML
    private TextField tfLine1;
    @FXML
    private TextField tfLine2;
    @FXML
    private ComboBox cbState;    
    @FXML
    private TextField tfPostalCode;
    @FXML
    private ComboBox cbCountry;
    @FXML
    private ComboBox cbAdressType;
    @FXML
    private ComboBox cbYear;
    @FXML
    private TextField tfAvailableCollections;
    @FXML
    private TextField tfCurrentAddress;
    @FXML
    private TextField tfTotalAddresses;
    
    private void inactivateAllAddressFields() {
        FieldAction.inactivateTextField(tfAddressID);
        FieldAction.inactivateTextField(tfLine1);
        FieldAction.inactivateTextField(tfLine2);
        FieldAction.inactivateComboBox(cbState);
        FieldAction.inactivateTextField(tfPostalCode);
        FieldAction.inactivateComboBox(cbCountry);
        FieldAction.inactivateComboBox(cbAdressType);
        FieldAction.inactivateComboBox(cbYear);
        FieldAction.inactivateTextField(tfAvailableCollections);
        FieldAction.inactivateTextField(tfCurrentAddress);
        FieldAction.inactivateTextField(tfTotalAddresses);
    }
    
/*  ==================================================================
    COLLECTION SERVICE REQUEST - CSR
=================================================================== */
    @FXML
    private TextField tfCSRID;
    @FXML
    private DatePicker dpBookingDate;
    @FXML
    private DatePicker dpCollectionDate;
    @FXML
    private Label lblCancelled;
    @FXML
    private ComboBox cbItemCategory;
    @FXML
    private ComboBox cbItemType;
    @FXML
    private TextField tfItemDescription;
    @FXML
    private TextField tfQuantity;
    @FXML
    private TextField tfItemNumber;
    @FXML
    private TableView tvCSRSummary;
    @FXML
    private TextField tfCurrentCSR;
    @FXML
    private TextField tfTotalCSRs;
    
    private void inactivateAllCSRFields() {
        FieldAction.inactivateTextField(tfCSRID);
        FieldAction.inactivateDatePicker(dpBookingDate);
        FieldAction.inactivateDatePicker(dpCollectionDate);
        FieldAction.inactivateComboBox(cbItemCategory);
        FieldAction.inactivateComboBox(cbItemType);
        FieldAction.inactivateTextField(tfItemDescription);
        FieldAction.inactivateTextField(tfQuantity);
        FieldAction.inactivateTextField(tfItemNumber);
        FieldAction.inactivateTableView(tvCSRSummary);
        FieldAction.inactivateTextField(tfCurrentCSR);
        FieldAction.inactivateTextField(tfTotalCSRs);
    }
    
    @FXML
    public void itemCategorySelect() {
        OptionLoader.loadItemTypeComboBoxOptions(cbItemCategory, 
            cbItemType);
    }

    
}
