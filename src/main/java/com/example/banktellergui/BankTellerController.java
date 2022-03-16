package com.example.banktellergui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class BankTellerController {

    @FXML
    private ToggleGroup accType;

    @FXML
    private RadioButton accountType;

    @FXML
    private ToggleGroup campCode;

    @FXML
    private RadioButton campusCode;

    @FXML
    private TextArea displayResult;

    @FXML
    private DatePicker dob;

    @FXML
    private TextField fname;

    @FXML
    private TextField lname;

    @FXML
    private RadioButton loyalCustomer;

    @FXML
    void closeAccount(ActionEvent event) {

    }

    @FXML
    void openAccount(ActionEvent event) {

    }

}