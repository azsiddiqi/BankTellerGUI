package com.example.banktellergui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class BankTellerController {

    private static AccountDatabase allAccts = new AccountDatabase();

    @FXML
    private ToggleGroup accType;

    @FXML
    private RadioButton accountType;

    @FXML
    private TextField balance;

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
    void calculateFeesAndInterests(ActionEvent event) {

    }

    @FXML
    void closeAccount(ActionEvent event) {

    }

    @FXML
    void depositIntoAccount(ActionEvent event) {

    }

    @FXML
    void openAccount(ActionEvent event) {
        Profile holder = new Profile(fname.getText(), lname.getText(), new Date(dob.getValue().toString()));
        Account addAccount = null;
        if (accountType.getText().equals("Checking")) {
            addAccount = new Checking(holder, Double.parseDouble(balance.getText()));
        } else if (accountType.getText().equals("College Checking")) {
            addAccount = new CollegeChecking(holder, Double.parseDouble(balance.getText()), Integer.parseInt(campusCode.getText()));
        } else if (accountType.getText().equals("Savings")) {
            addAccount = new Savings(holder, Double.parseDouble(balance.getText()), Integer.parseInt(loyalCustomer.getText()));
        } else if (accountType.getText().equals("Money Market")) {
            addAccount = new MoneyMarket(holder, Double.parseDouble(balance.getText()));
        }
        allAccts.open(addAccount);
        displayResult.appendText("Account opened.\n");
    }

    @FXML
    void printAccounts(ActionEvent event) {

    }

    @FXML
    void printAccountsByType(ActionEvent event) {

    }

    @FXML
    void printAccountsWithFeesAndInterests(ActionEvent event) {

    }

    @FXML
    void withdrawFromAccount(ActionEvent event) {

    }

}
