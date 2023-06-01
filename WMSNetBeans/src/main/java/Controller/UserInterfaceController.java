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
import Model.Collection;
import Model.CollectionItem;
import Model.Customer;
import View.FieldAction;
import View.OptionLoader;
import View.UserAlert;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
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
    
    private final int MAX_ANNUAL_COLLECTIONS = 2;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        OptionLoader.loadStateComboBoxOptions(cbState);
        OptionLoader.loadCountryComboBoxOptions(cbCountry);
        OptionLoader.loadAddressTypeComboBoxOptions(cbAddressType);
        OptionLoader.loadYearComboBoxOptions(cbYear);
        OptionLoader.loadItemCategoryComboBoxOptions(cbItemCategory);
        customerSet = DataSet.FULL_SET;
        addressSet = DataSet.FULL_SET;
        inactivateAllFields();
        loadAllCustomersFromDB();
        loadAllAddressesFromDB();
        loadAllCSRsFromDB();
        loadAllItemsFromDB();
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
    private SaveAction customerSaveAction;
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
                customerAddressID = queryResults.getString("customer_address_id");
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
    public void btnNewCustomerClick() {
        customerSaveAction = SaveAction.NEW;
        int nextCustomerID = getNextCustomerID();
        tfCustomerID.setText(nextCustomerID + "");
        tfFirstName.clear();
        FieldAction.activateTextField(tfFirstName);  
        tfLastName.clear();
        FieldAction.activateTextField(tfLastName);
        tfMobile.clear();
        FieldAction.activateTextField(tfMobile);
        tfEmail.clear();
        FieldAction.activateTextField(tfEmail);
        tfCustomerAddressID.clear();
        taAddress.clear();
        int newTotalCustomers = customersList.size() + 1;
        tfCurrentCustomer.setText(newTotalCustomers + "");
        tfTotalCustomers.setText(newTotalCustomers + "");
    }
    
    private int getNextCustomerID() {
        int maxCustomerID = 0;
        for (Customer customer : customersList) {
            int currentCustomerID = Integer.parseInt(customer.getCustomerID());
            if (currentCustomerID > maxCustomerID) {
                maxCustomerID = currentCustomerID;
            }
        }
        return maxCustomerID + 1;
    }
    
    @FXML
    public void btnViewAddressClick() {
        customerAddressID = tfCustomerAddressID.getText();
        if (!DataValidation.isEmpty(customerAddressID)) {
            for (int i = 0; i < addressList.size(); i++) {
                if (addressList.get(i).getAddressID().equals(customerAddressID)) {
                    addressSet = DataSet.FULL_SET;
                    displayAddressRecord(i);
                    currentAddress = i;
                    tfCurrentAddress.setText(currentAddress + 1 + "");
                    totalAddresses = addressList.size();
                    tfTotalAddresses.setText(totalAddresses + "");
                }
            }
        } else {
            UserAlert.displayWarningAlert("No customer selected.", "To "
                    + "view an address, you need to select a customer. ");
        }
    }
    
    @FXML
    public void btnPreviousCustomerClick() {
        if (!DataValidation.isEmpty(tfCustomerID.getText())) {
            inactivateAllCustomerFields();
            if (currentCustomer == 0) {
                currentCustomer = totalCustomers - 1;
            } else {
                currentCustomer--;
            }
            displayCustomerRecord(currentCustomer);
            refreshCustomerPaginationNumbers();
        } else {
            UserAlert.displayWarningAlert("Previous Record Error", 
                    "You have not selected a customer yet. To be able to "
                            + "view the previous customer, you must select a "
                            + "customer first.");
        }
    }
    
    @FXML
    public void btnNextCustomerClick() {
        if (!DataValidation.isEmpty(tfCustomerID.getText())) {
            inactivateAllCustomerFields();
            if (currentCustomer + 1 == totalCustomers) {
                currentCustomer = 0;
            } else {
                currentCustomer++;
            }
            displayCustomerRecord(currentCustomer);
            refreshCustomerPaginationNumbers();
        } else {
            UserAlert.displayWarningAlert("Next Record Error", 
                    "You have not selected a customer yet. To be able to "
                            + "view the next customer, you must select a "
                            + "customer first.");
        }
    }
    
/*  ==================================================================
    ADDRESS
=================================================================== */
    private ArrayList<Address> addressList= new ArrayList();
    private ArrayList<Address> tempAddressList= new ArrayList();
    private int currentAddress;
    private int totalAddresses;
    private DataSet addressSet;
    private SaveAction addressSaveAction;
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
    private ComboBox cbAddressType;
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
        FieldAction.inactivateComboBox(cbAddressType);
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
        cbAddressType.setValue(address.getAddressType());
        cbAddressType.setStyle("-fx-opacity: 1.0");
        int currentYear = (int) Year.now().getValue();
        cbYear.setValue(currentYear);
        cbYear.setDisable(false);
        int availableCollections = getAvailableCollections(currentYear, address.getAddressID());
        tfAvailableCollections.setText(availableCollections + "");
    }
    
    private int getAvailableCollections(int year, String addressID) {
        int availableCollections = 0;
        int numberOfCollections = 0;
        LocalDate collectionDate = null;
        int collectionYear = 0;
        String collectionAddressID;
        for (int i = 0; i < collectionsList.size(); i++) {
            collectionDate = collectionsList.get(i).getCollectionDate();
            collectionYear = collectionDate.getYear();
            collectionAddressID = collectionsList.get(i).getCsrAddressID();
            if (collectionYear == year && collectionAddressID.equals(addressID)) {
                numberOfCollections++;
            }
        }
        availableCollections = MAX_ANNUAL_COLLECTIONS - numberOfCollections;
        return availableCollections;
    }
    
    private void refreshAddressPaginationNumbers() {
        tfCurrentAddress.setText(currentAddress + 1 + "");
        tfTotalAddresses.setText(totalAddresses + "");
    }
    
    @FXML
    public void btnNewAddressClick() {
        addressSaveAction = SaveAction.NEW;
        int nextAddressID = getNextAddressID();
        tfAddressID.setText(nextAddressID + "");
        tfStreetAddress.clear();
        FieldAction.activateTextField(tfStreetAddress);
        tfSuburb.clear();
        FieldAction.activateTextField(tfSuburb);
        cbState.getSelectionModel().clearSelection();
        FieldAction.activateComboBox(cbState);
        tfPostalCode.clear();
        FieldAction.activateTextField(tfPostalCode);
        cbCountry.getSelectionModel().clearSelection();
        FieldAction.activateComboBox(cbCountry);
        cbAddressType.getSelectionModel().clearSelection();
        FieldAction.activateComboBox(cbAddressType);
        cbYear.getSelectionModel().clearSelection();
        FieldAction.activateComboBox(cbYear);
        tfAvailableCollections.setText(MAX_ANNUAL_COLLECTIONS + "");
        int newTotalAddresses = addressList.size() + 1;
        tfCurrentAddress.setText(newTotalAddresses + "");
        tfTotalAddresses.setText(newTotalAddresses + "");
    }
    
    private int getNextAddressID() {
        int maxAddressID = 0;
        for (Address address : addressList) {
            int currentAddressID = Integer.parseInt(address.getAddressID());
            if (currentAddressID > maxAddressID) {
                maxAddressID = currentAddressID;
            }
        }
        return maxAddressID + 1;
    }
    
    @FXML
    private void btnFillAddressClick() {
        if (customerSaveAction == SaveAction.NEW) {
            tfCustomerAddressID.setText(tfAddressID.getText());
        } else {
            UserAlert.displayWarningAlert("Incorrect Fill Address Use",
                    "To fill in a customer address, you must be adding a "
                            + "new customer.");
        }
        String addressString = getAddressString(tfCustomerAddressID.getText());
        taAddress.setText(addressString);
    }
    
    @FXML
    public void btnPreviousAddressClick() {
        if (!DataValidation.isEmpty(tfAddressID.getText())) {
            inactivateAllAddressFields();
            if (currentAddress == 0) {
                currentAddress = totalAddresses - 1;
            } else {
                currentAddress--;
            }
            displayAddressRecord(currentAddress);
            refreshAddressPaginationNumbers();
        } else {
            UserAlert.displayWarningAlert("Previous Record Error", 
                    "You have not selected an address yet. To be able to "
                            + "view the previous address, you must select an "
                            + "address first.");
        }
    }
    
    @FXML
    public void btnNextAddressClick() {
        if (!DataValidation.isEmpty(tfAddressID.getText())) {
            inactivateAllAddressFields();
            if (currentAddress + 1 == totalAddresses) {
                currentAddress = 0;
            } else {
                currentAddress++;
            }
            displayAddressRecord(currentAddress);
            refreshAddressPaginationNumbers();
        } else {
            UserAlert.displayWarningAlert("Next Record Error", 
                    "You have not selected an address yet. To be able to "
                            + "view the next address, you must select an "
                            + "address first.");
        }
    }
    
    @FXML
    public void btnSaveAddressClick() {
        if (addressSaveAction.equals(SaveAction.NEW)) {
            addNewAddress();
        } else {
            // May be considered for future implementation.
            // editAddress();
        }
    }
    
    private void addNewAddress() {
        Address newAddress = makeNewAddressObjectfromUI();
        boolean addressAddedToDB = addAddressToDB(newAddress);
        if (addressAddedToDB) {
            inactivateAllAddressFields();
            addressList.clear();
            loadAllAddressesFromDB();
            int indexOfNewAddress = -1;
            for (int i = 0; i < addressList.size(); i++) {
                if (addressList.get(i).getAddressID().equals(
                        newAddress.getAddressID())) {
                    indexOfNewAddress = i;
                    break;
                }
            }
            currentAddress = indexOfNewAddress;
            displayAddressRecord(currentAddress);
            totalAddresses = addressList.size();
            refreshAddressPaginationNumbers();
            addressSaveAction = null;
            UserAlert.displayInformationAlert("Save successful", 
                    "The address has been successfully saved to the "
                            + "database.");
        }
    }
    
    private Address makeNewAddressObjectfromUI() {
        addressID = tfAddressID.getText();
        streetAddress = tfStreetAddress.getText();
        suburb = tfSuburb.getText();
        state = cbState.getValue().toString();
        postalCode = tfPostalCode.getText();
        country = cbCountry.getValue().toString();
        addressType = cbAddressType.getValue().toString();
        return makeNewAddressObject();
    }
    
    private boolean addAddressToDB(Address newAddress) {
        boolean addedToDatabase = false;
        addressID = newAddress.getAddressID();
        streetAddress = newAddress.getStreetAddress();
        suburb = newAddress.getSuburb();
        state = newAddress.getState();
        postalCode = newAddress.getPostalCode();
        country = newAddress.getCountry();
        addressType = newAddress.getAddressType();
        try (Connection connection = DatabaseHandler.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    String.format("INSERT INTO addresses "
                            + "(address_id, street_address, suburb, state, postal_code, "
                            + "country, address_type) "
                            + "VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s');",
                            addressID, streetAddress, suburb, state,
                            postalCode, country, addressType));
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                addedToDatabase = true;
            } else {
                UserAlert.displayErrorAlert("Database Error", "eWMS has "
                        + "been unable to save the address to the database. "
                        + "check the data entered and try again.");
            }
            statement.close();
            connection.close();
        } catch (Exception e) {
            UserAlert.displayErrorAlert("Database connection error", 
                    "There was a database connection error and the address "
                            + "has not been saved to the database.");
        }
        return addedToDatabase;
    }
    
/*  ==================================================================
    COLLECTION SERVICE REQUEST - CSR
=================================================================== */
    private ArrayList<Collection> collectionsList= new ArrayList();
    @FXML
    private TextField tfCSRID;
    private String csrID;
    @FXML
    private DatePicker dpBookingDate;
    LocalDate bookingDate;
    @FXML
    private DatePicker dpCollectionDate;
    LocalDate collectionDate;
    @FXML
    private TextField tfCSRCustomerID;
    private String csrCustomerID;
    @FXML
    private TextField tfCSRAddressID;
    private String csrAddressID;
    @FXML
    private Label lblCancelled;
    private boolean isCancelled;
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
    
    private void loadAllCSRsFromDB() {
        try (Connection connection = DatabaseHandler.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * "
                    + "FROM collections "
                    + "ORDER BY collection_id;"
            );
            ResultSet queryResults = statement.executeQuery();
            while (queryResults.next()) {
                csrID = queryResults.getString("collection_id");
                bookingDate = LocalDate.parse(queryResults.getString("booking_date"),
                        DateTimeFormatter.ISO_LOCAL_DATE);
                collectionDate = LocalDate.parse(queryResults.getString("collection_date"),
                        DateTimeFormatter.ISO_LOCAL_DATE);
                csrCustomerID = queryResults.getString("csr_customer_id");
                csrAddressID = queryResults.getString("csr_address_id");
                isCancelled = Boolean.parseBoolean(queryResults.getString("cancelled"));
                Collection newCollection = makeNewCollectionObject();
                collectionsList.add(newCollection);
            }
            statement.close();
            queryResults.close();
            connection.close();
        } catch (Exception e) {
            UserAlert.displayErrorAlert("Database Error", "ERROR: Unable to load "
                    + "collections from the database.");
        }
    }
        
    private Collection makeNewCollectionObject() {
        return new Collection(
                csrID,
                bookingDate,
                collectionDate,
                csrCustomerID,
                csrAddressID,
                isCancelled
        );
    }
    
    
    
    
/*  ==================================================================
    COLLECTION ITEMS
    =================================================================== */    
    private ArrayList<CollectionItem> itemsList= new ArrayList();
    private String itemID;
    private String itemCollectionID;
    private String itemCategory;
    private String itemType;
    private String itemDescription;
    private String itemQuantity;
    
    private void loadAllItemsFromDB(){
        try (Connection connection = DatabaseHandler.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * "
                    + "FROM items "
                    + "ORDER BY item_id;"
            );
            ResultSet queryResults = statement.executeQuery();
            while (queryResults.next()) {
                itemID = queryResults.getString("item_id");
                itemCollectionID = queryResults.getString("item_collection_id");
                itemCategory = queryResults.getString("category");
                itemType = queryResults.getString("type");
                itemDescription = queryResults.getString("description");
                itemQuantity = queryResults.getString("quantity");
                CollectionItem newCollectionItem = makeNewCollectionItemObject();
                itemsList.add(newCollectionItem);
            }
            statement.close();
            queryResults.close();
            connection.close();
        } catch (Exception e) {
            UserAlert.displayErrorAlert("Database Error", "ERROR: Unable to load "
                    + "collection items from the database.");
        }
    }
    
    private CollectionItem makeNewCollectionItemObject() {
        return new CollectionItem(
                itemID,
                itemCollectionID,
                itemCategory,
                itemType,
                itemDescription,
                itemQuantity
        );
    }
}
