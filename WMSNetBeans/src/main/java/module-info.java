module WMS {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;

    opens WMS to javafx.fxml;
    opens Controller to javafx.fxml;
    exports WMS;
    exports Controller;
}
