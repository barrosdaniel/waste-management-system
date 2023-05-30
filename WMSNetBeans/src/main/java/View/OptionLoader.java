package View;

import java.time.Year;
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
    
    public static void loadAddressTypeComboBoxOptions(ComboBox cbAddressType){
        cbAddressType.getItems().addAll(
                "Residential",
                "Commercial");
    }
    
    public static void loadYearComboBoxOptions(ComboBox cbYear) {
        int year = (int) Year.now().getValue();
        int nextYear = year + 1;
        cbYear.getItems().addAll(
                year,
                nextYear);
    }
    
    public static void loadItemCategoryComboBoxOptions(ComboBox cbItemCategory) {
        cbItemCategory.getItems().addAll(
                "General Hard",
                "Greens",
                "Metal",
                "Electronic");
    }
    
    public static void loadItemTypeComboBoxOptions(ComboBox cbItemCategory, 
            ComboBox cbItemType) {
        String categorySelection = cbItemCategory.getValue().toString();
        cbItemType.getItems().clear();
        cbItemType.setPromptText("");
        switch (categorySelection) {
            case "General Hard":
                cbItemType.getItems().addAll(
                        "Hard plastic",
                        "Furniture",
                        "Papers",
                        "Glasses",
                        "Timber",
                        "Ceramics");
                break;
            case "Greens":
                cbItemType.getItems().addAll(
                        "Raw wood",
                        "Branches",
                        "Cuttings",
                        "Leaves");
                break;
            case "Metal":
                cbItemType.getItems().addAll(
                        "Scrap metal",
                        "Pieces and equipment",
                        "White goods",
                        "Tins");
                break;
            case "Electronic":
                cbItemType.getItems().addAll(
                        "Electrical",
                        "Electronic",
                        "Other appliances");
                break;
            default:
                break;
        }
    }
}
