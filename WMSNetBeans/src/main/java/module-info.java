module WMS {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens WMS to javafx.fxml;
    exports WMS;
    exports Controller;
}
