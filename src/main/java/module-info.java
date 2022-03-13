module com.example.banktellergui {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.banktellergui to javafx.fxml;
    exports com.example.banktellergui;
}