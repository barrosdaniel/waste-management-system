/*
Central Queensland University
COIT12200 - Software Design & Development (2023 Term 1)
Campus: External
Assignment 3 - electronic Waste Management System
Student ID: 12184305
Student Name: Daniel Barros
*/
package Model;

public class Customer {
    private String customerID;
    private String firstName;
    private String lastName;
    private String mobile;
    private String email;
    private String customerAddressID;

    public Customer(String customerID, String firstName, String lastName, 
            String mobile, String email, String customerAddressID) {
        this.customerID = customerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobile = mobile;
        this.email = email;
        this.customerAddressID = customerAddressID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCustomerAddressID() {
        return customerAddressID;
    }

    public void setCustomerAddressID(String customerAddressID) {
        this.customerAddressID = customerAddressID;
    }

    @Override
    public String toString() {
        return "Customer{" + "customerID=" + customerID + 
                ", firstName=" + firstName + ", lastName=" + lastName +
                ", mobile=" + mobile + ", email=" + email +
                ", customerAddressID=" + customerAddressID + '}';
    }
    
    
}
