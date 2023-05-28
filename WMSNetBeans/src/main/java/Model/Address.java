package Model;

public class Address {
    private String addressID;
    private String line1;
    private String line2;
    private String state;
    private String postalCode;
    private String country;
    private String addressType;

    public Address(String addressID, String line1, String line2, String state, String postalCode, String country, String addressType) {
        this.addressID = addressID;
        this.line1 = line1;
        this.line2 = line2;
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

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
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

    @Override
    public String toString() {
        return "Address{" + "addressID=" + addressID 
                + ", line1=" + line1 + ", line2=" + line2 
                + ", state=" + state + ", postalCode=" + postalCode 
                + ", country=" + country + ", addressType=" + addressType + '}';
    }
}
