/*
Central Queensland University
COIT12200 - Software Design & Development (2023 Term 1)
Campus: External
Assignment 3 - electronic Waste Management System
Student ID: 12184305
Student Name: Daniel Barros
*/
package Model;

public class CollectionItem {
    private String itemID;
    private String itemCollectionID;
    private String itemCategory;
    private String itemType;
    private String itemDescription;
    private String itemQuantity;

    public CollectionItem(String itemID, String itemCollectionID, 
            String itemCategory, String itemType, String itemDescription, 
            String itemQuantity) {
        this.itemID = itemID;
        this.itemCollectionID = itemCollectionID;
        this.itemCategory = itemCategory;
        this.itemType = itemType;
        this.itemDescription = itemDescription;
        this.itemQuantity = itemQuantity;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getItemCollectionID() {
        return itemCollectionID;
    }

    public void setItemCollectionID(String itemCollectionID) {
        this.itemCollectionID = itemCollectionID;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(String itemQuantity) {
        this.itemQuantity = itemQuantity;
    }
    
    public String getString() {
        return String.format("%-10s %-20s %-20s %-32s %-8s %n", 
            this.itemID, this.itemCategory,this.itemType, 
            this.itemDescription, this.itemQuantity);
    }
}
