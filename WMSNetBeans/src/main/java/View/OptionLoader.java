package View;

import javafx.scene.control.ComboBox;

public class OptionLoader {
    
    public static void loadStateComboBoxOptions(ComboBox cbState) {
        cbState.getItems().addAll(
                "QLD", 
                "NSW", 
                "ACT",
                "VIC",
                "TAS",
                "NT",
                "SA",
                "WA",
                "Other");
    }
    
    public static void loadCountryComboBoxOptions(ComboBox cbCountry) {
        cbCountry.getItems().addAll("Australia");
    }
    
    public static void loadAddressTypeComboBoxOptions(ComboBox cbAdressType){
        cbAdressType.getItems().addAll(
                "Residential",
                "Commercial");
    }
}
