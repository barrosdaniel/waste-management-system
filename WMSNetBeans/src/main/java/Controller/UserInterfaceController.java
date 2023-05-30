/*
Central Queensland University
COIT12200 - Software Design & Development (2023 Term 1)
Campus: External
Assignment 3 - electronic Waste Management System
Student ID: 12184305
Student Name: Daniel Barros
*/
package Controller;

import Model.Address;
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
        customerSet = DataSet.FULL_SET;
        addressSet = DataSet.FULL_SET;
        inactivateAllFields();
        loadAllCustomersFromDB();
        loadAllAddressesFromDB();
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
        String addressString = "";
        for (Address address : addressList) {
            if (address.getAddressID().equals(customerAddressID)) {
                addressString = address.getString();
            }
        }
        return addressString;
    }
    
    private void refreshCustomerPaginationNumbers() {
        tfCurrentCustomer.setText(currentCustomer + 1 + "");
        tfTotalCustomers.setText(totalCustomers + "");
    }
    
    @FXML
    public void btnViewAddressClick() {
        customerAddressID = tfCustomerAddressID.getText();
        if (!DataValidation.isEmpty(customerAddressID)) {
            for (int i = 0; i < addressList.size(); i++) {
                if (addressList.get(i).getAddressID().equals(customerAddressID)) {
                    displayAddressRecord(i);
                }
            }
        } else {
            UserAlert.displayWarningAlert("No customer selected.", "To "
                    + "view an address, you need to select a customer. ");
        }
    }
    
    @FXML
    public void btnPreviousCustomerClick() {
        inactivateAllCustomerFields();
        if (currentCustomer == 0) {
            currentCustomer = totalCustomers - 1;
        } else {
            currentCustomer--;
        }
        displayCustomerRecord(currentCustomer);
        refreshCustomerPaginationNumbers();
    }
    
    @FXML
    public void btnNextCustomerClick() {
        inactivateAllCustomerFields();
        if (currentCustomer + 1 == totalCustomers) {
            currentCustomer = 0;
        } else {
            currentCustomer++;
        }
        displayCustomerRecord(currentCustomer);
        refreshCustomerPaginationNumbers();
    }
    
/*  ==================================================================
    ADDRESS
=================================================================== */
    private ArrayList<Address> addressList= new ArrayList();
    private ArrayList<Address> tempAddressList= new ArrayList();
    private int currentAddress;
    private int totalAddresses;
    private DataSet addressSet;
    @FXML
    private TextField tfAddressID;
    private String addressID;
    @FXML
    private TextField tfStreetAddress;
    private String streetAddress;
    @FXML
    private TextField tfSuburb;
    private String suburb;
    @FXML
    private ComboBox cbState;    
    private String state;
    @FXML
    private TextField tfPostalCode;
    private String postalCode;
    @FXML
    private ComboBox cbCountry;
    private String country;
    @FXML
    private ComboBox cbAdressType;
    private String addressType;
    @FXML
    private ComboBox cbYear;
    private String year;
    @FXML
    private TextField tfAvailableCollections;
    private int availableCollections;
    @FXML
    private TextField tfCurrentAddress;
    @FXML
    private TextField tfTotalAddresses;
    
    private void inactivateAllAddressFields() {
        FieldAction.inactivateTextField(tfAddressID);
        FieldAction.inactivateTextField(tfStreetAddress);
        FieldAction.inactivateTextField(tfSuburb);
        FieldAction.inactivateComboBox(cbState);
        FieldAction.inactivateTextField(tfPostalCode);
        FieldAction.inactivateComboBox(cbCountry);
        FieldAction.inactivateComboBox(cbAdressType);
        FieldAction.inactivateComboBox(cbYear);
        FieldAction.inactivateTextField(tfAvailableCollections);
        FieldAction.inactivateTextField(tfCurrentAddress);
        FieldAction.inactivateTextField(tfTotalAddresses);
    }
    
    private void loadAllAddressesFromDB(){
        try (Connection connection = DatabaseHandler.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * "
                    + "FROM addresses "
                    + "ORDER BY address_id;"
            );
            ResultSet queryResults = statement.executeQuery();
            while (queryResults.next()) {
                addressID = queryResults.getString("address_id");
                streetAddress = queryResults.getString("street_address");
                suburb = queryResults.getString("suburb");
                state = queryResults.getString("state");
                postalCode = queryResults.getString("postal_code");
                country = queryResults.getString("country");
                addressType = queryResults.getString("address_type");
                Address newAddress = makeNewAddressObject();
                addressList.add(newAddress);
            }
            statement.close();
            queryResults.close();
            connection.close();
        } catch (Exception e) {
            UserAlert.displayErrorAlert("Database Error", "ERROR: Unable to load "
                    + "addresses from the database.");
        }
    }
    
    private Address makeNewAddressObject() {
        return new Address(
                addressID,
                streetAddress,
                suburb,
                state,
                postalCode,
                country,
                addressType
        );
    }
    
    @FXML
    public void btnViewAllAddressesClick() {
        addressSet = DataSet.FULL_SET;
        inactivateAllAddressFields();
        if (addressList.size() > 0) {
            currentAddress = 0;
            totalAddresses = addressList.size();
            displayAddressRecord(currentAddress);
            refreshAddressPaginationNumbers();
        }
    }
    
    private void displayAddressRecord(int index) {
        Address address;
        if (addressSet.equals(DataSet.FULL_SET)) {
            address = addressList.get(index);
        } else {
            address = tempAddressList.get(index);
        }
        tfAddressID.setText(address.getAddressID());
        tfStreetAddress.setText(address.getStreetAddress());
        tfSuburb.setText(address.getSuburb());
        cbState.setValue(address.getState());
        cbState.setStyle("-fx-opacity: 1.0");
        tfPostalCode.setText(address.getPostalCode());
        cbCountry.setValue(address.getCountry());
        cbCountry.setStyle("-fx-opacity: 1.0");
        cbAdressType.setValue(address.getAddressType());
        cbAdressType.setStyle("-fx-opacity: 1.0");
    }
    
    private void refreshAddressPaginationNumbers() {
        tfCurrentAddress.setText(currentAddress + 1 + "");
        tfTotalAddresses.setText(totalAddresses + "");
    }
    
    @FXML
    public void btnPreviousAddressClick() {
        inactivateAllAddressFields();
        if (currentAddress == 0) {
            currentAddress = totalAddresses - 1;
        } else {
            currentAddress--;
        }
        displayAddressRecord(currentAddress);
        refreshAddressPaginationNumbers();
    }
    
    @FXML
    public void btnNextAddressClick() {
        inactivateAllAddressFields();
        if (currentAddress + 1 == totalAddresses) {
            currentAddress = 0;
        } else {
            currentAddress++;
        }
        displayAddressRecord(currentAddress);
        refreshAddressPaginationNumbers();
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
