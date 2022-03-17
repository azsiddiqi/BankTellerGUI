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
    private ToggleGroup accountType;

    @FXML
    private TextField balanceAmount;

    @FXML
    private ToggleGroup campusCode;

    @FXML
    private DatePicker dateOfBirth;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private RadioButton loyalCustomer;

    @FXML
    private TextArea textAreaDisplay;

    @FXML
    private TextArea textAreaDisplay3;

    @FXML
    void closeAccount(ActionEvent event) {

    }

    @FXML
    void depositIntoAccount(ActionEvent event) {

    }

    @FXML
    void openAccount(ActionEvent event) {
        Profile holder = new Profile(firstName.getText(), lastName.getText(), new Date(dateOfBirth.getValue().toString()));
        Account addAccount = null;
        RadioButton accType = (RadioButton) accountType.getSelectedToggle();
        if (accType.getText().equals("Checking")) {
            addAccount = new Checking(holder, Double.parseDouble(balanceAmount.getText()));
        } else if (accType.getText().equals("College Checking")) {
            RadioButton campCode = (RadioButton) campusCode.getSelectedToggle();
            addAccount = new CollegeChecking(holder, Double.parseDouble(balanceAmount.getText()), Integer.parseInt(campCode.getId()));
        } else if (accType.getText().equals("Savings")) {
            if (loyalCustomer.isSelected()) {
                addAccount = new Savings(holder, Double.parseDouble(balanceAmount.getText()), 1);
            } else {
                addAccount = new Savings(holder, Double.parseDouble(balanceAmount.getText()), 0);
            }
        } else if (accType.getText().equals("Money Market")) {
            addAccount = new MoneyMarket(holder, Double.parseDouble(balanceAmount.getText()));
        }
        allAccts.open(addAccount);
        textAreaDisplay.appendText("Account opened.\n");
    }

    @FXML
    void printAccounts(ActionEvent event) {
        textAreaDisplay3.appendText("\n*list of accounts in the database*\n");
        for (int i = 0; i < allAccts.getNumAcct(); i++) {
            textAreaDisplay3.appendText(allAccts.print(allAccts.getAccounts()[i]));
            textAreaDisplay3.appendText("\n");
        }
        textAreaDisplay3.appendText("*end of list*\n");
    }

    @FXML
    void printAccountsByType(ActionEvent event) {
        allAccts.printByAccountType();
        textAreaDisplay3.appendText("\n*list of accounts by account type.\n");
        for (int i = 0; i < allAccts.getNumAcct(); i++) {
            textAreaDisplay3.appendText(allAccts.print(allAccts.getAccounts()[i]));
            textAreaDisplay3.appendText("\n");
        }
        textAreaDisplay3.appendText("*end of list.\n");

    }

    @FXML
    void printAccountsWithFeesAndInterests(ActionEvent event) {
        textAreaDisplay3.appendText("\n*list of accounts with fee and monthly interest\n");
        for (int i = 0; i < allAccts.getNumAcct(); i++) {
            textAreaDisplay3.appendText(allAccts.printFeeAndInterest(allAccts.getAccounts()[i]));
            textAreaDisplay3.appendText("\n");
        }
        textAreaDisplay3.appendText("*end of list.\n");
    }

    @FXML
    void printAccountsWithUpdatedBalances(ActionEvent event) {
        textAreaDisplay3.appendText("\n*list of accounts with updated balance\n");
        for (int i = 0; i < allAccts.getNumAcct(); i++) {
            allAccts.getAccounts()[i].updateBalance();
            textAreaDisplay3.appendText(allAccts.print(allAccts.getAccounts()[i]));
            textAreaDisplay3.appendText("\n");
        }
        textAreaDisplay3.appendText("*end of list.\n");
    }

    @FXML
    void withdrawFromAccount(ActionEvent event) {

    }

}
