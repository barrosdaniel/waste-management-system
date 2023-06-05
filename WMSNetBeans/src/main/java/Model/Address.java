/*
Central Queensland University
COIT12200 - Software Design & Development (2023 Term 1)
Campus: External
Assignment 3 - electronic Waste Management System
Student ID: 12184305
Student Name: Daniel Barros
*/
package Model;

public class Address {
    private String addressID;
    private String streetAddress;
    private String suburb;
    private String state;
    private String postalCode;
    private String country;
    private String addressType;

    public Address(String addressID, String streetAddress, String suburb, 
            String state, String postalCode, String country, String addressType) {
        this.addressID = addressID;
        this.streetAddress = streetAddress;
        this.suburb = suburb;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
        this.addressType = addressType;
    }

    public String getAddressID() {
        return addressID;
    }

    public void setAddressID(String addressID) {
        this.addressID = addressID;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setLine1(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setLine2(String suburb) {
        this.suburb = suburb;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }
    
    public String getString() {
        return streetAddress + "\n" + 
                suburb + " " + state + " " + postalCode + "\n" + 
                country;
                
    }

    @Override
    public String toString() {
        return "Address{" + "addressID=" + addressID 
                + ", line1=" + streetAddress + ", line2=" + suburb 
                + ", state=" + state + ", postalCode=" + postalCode 
                + ", country=" + country + ", addressType=" + addressType + '}';
    }
}
