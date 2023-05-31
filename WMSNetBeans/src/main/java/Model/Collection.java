package Model;

import java.time.LocalDate;

public class Collection {
    private String csrID;
    private LocalDate bookingDate;
    private LocalDate collectionDate;
    private String csrCustomerID;
    private String csrAddressID;
    private boolean isCancelled;

    public Collection(String csrID, LocalDate bookingDate, LocalDate collectionDate,
            String csrCustomerID, String csrAddressID, boolean isCancelled) {
        this.csrID = csrID;
        this.bookingDate = bookingDate;
        this.collectionDate = collectionDate;
        this.csrCustomerID = csrCustomerID;
        this.csrAddressID = csrAddressID;
        this.isCancelled = isCancelled;
    }

    public String getCsrID() {
        return csrID;
    }

    public void setCsrID(String csrID) {
        this.csrID = csrID;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public LocalDate getCollectionDate() {
        return collectionDate;
    }

    public void setCollectionDate(LocalDate collectionDate) {
        this.collectionDate = collectionDate;
    }

    public String getCsrCustomerID() {
        return csrCustomerID;
    }

    public void setCsrCustomerID(String csrCustomerID) {
        this.csrCustomerID = csrCustomerID;
    }

    public String getCsrAddressID() {
        return csrAddressID;
    }

    public void setCsrAddressID(String csrAddressID) {
        this.csrAddressID = csrAddressID;
    }

    public boolean isIsCancelled() {
        return isCancelled;
    }

    public void setIsCancelled(boolean isCancelled) {
        this.isCancelled = isCancelled;
    }
    
    
    
}
