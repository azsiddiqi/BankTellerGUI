package com.example.banktellergui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class BankTellerController {

    public static final int VALID_NUMBER_OF_INFORMATION_FOR_OPENING_CHECKING_OR_MONEY_MARKET = 6;
    public static final int VALID_NUMBER_OF_INFORMATION_FOR_OPENING_COLLEGE_CHECKING_OR_SAVINGS = 7;
    public static final int VALID_NUMBER_OF_INFORMATION_FOR_CLOSING_ACCOUNT = 5;

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

    private int accountFinder(Account account) {
        for (int i = 0; i < allAccts.getNumAcct(); i++) {
            if (allAccts.getAccounts()[i].equals(account)) {
                return i;
            }
        }
        return AccountDatabase.NOT_FOUND;
    }

    private boolean sameAccountsChecker(Account account) {
        RadioButton accType = (RadioButton) accountType.getSelectedToggle();
        for (int i = 0; i < allAccts.getNumAcct(); i++) {
            if (accountFinder(account) != AccountDatabase.NOT_FOUND && allAccts.getAccounts()[accountFinder(account)].closed == false) {
                textAreaDisplay.appendText(account.holder.toString() + " same account(type) is in the database.\n");
                return true;
            }
            if (allAccts.getAccounts()[i].holder.equals(account.holder) &&
                    ((allAccts.getAccounts()[i].getType().equals("Checking") &&
                            accType.getText().equals("College Checking")) ||
                            (allAccts.getAccounts()[i].getType().equals("College Checking")
                                    && accType.getText().equals("Checking")))) {
                textAreaDisplay.appendText(account.holder.toString() + " same account(type) is in the database.\n");
                return true;
            }
        }
        return false;
    }

    private boolean validInformationChecker() {
        if (firstName.getText().isEmpty() || lastName.getText().isEmpty() || dateOfBirth.getValue() == null ||
                accountType.getSelectedToggle() == null || balanceAmount.getText().isEmpty()) {
            textAreaDisplay.appendText("Missing data for opening an account.\n");
            return false;
        }
        RadioButton accType = (RadioButton) accountType.getSelectedToggle();
        if (accType.getText().equals("College Checking")) {
            if (campusCode.getSelectedToggle() == null) {
                textAreaDisplay.appendText("Missing data for opening an account.\n");
                return false;
            }
        }
        Date dob = new Date(dateOfBirth.getValue().toString());
        Date today = new Date();
        if (dob.isValid() == false || dob.compareTo(today) >= 0) {
            textAreaDisplay.appendText("Date of birth invalid.\n");
            return false;
        }
        double balance = 0;
        try {
            balance = Double.parseDouble(balanceAmount.getText());
        } catch (NumberFormatException invalidBalance) {
            textAreaDisplay.appendText("Not a valid amount.\n");
            return false;
        }
        if (balance <= 0) {
            textAreaDisplay.appendText("Initial deposit cannot be 0 or negative.\n");
            return false;
        }
        return true;
    }

    @FXML
    void closeAccount(ActionEvent event) {

    }

    @FXML
    void depositIntoAccount(ActionEvent event) {

    }

    @FXML
    void openAccount(ActionEvent event) {
        if (!validInformationChecker()) {
            return;
        }
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
            MoneyMarket testAccount = (MoneyMarket) addAccount;
            if (testAccount.balance < MoneyMarket.MONEY_MARKET_WAIVED_THRESHOLD && accountFinder(testAccount) == AccountDatabase.NOT_FOUND) {
                textAreaDisplay.appendText("Minimum of $2500 to open a MoneyMarket account.\n");
                return;
            }
            if (testAccount.balance < MoneyMarket.MONEY_MARKET_WAIVED_THRESHOLD) {
                testAccount.loyalCustomer = false;
            }
        }
        if (sameAccountsChecker(addAccount)) {
            return;
        }
        if (!(allAccts.open(addAccount))) {
            textAreaDisplay.appendText("Account reopened.\n");
        } else {
            textAreaDisplay.appendText("Account opened.\n");
        }
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
