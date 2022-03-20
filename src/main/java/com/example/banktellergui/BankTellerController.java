package com.example.banktellergui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;


/**
 This class contains and modifies an AccountDatabase object based on the commands and information obtained from the
 BankTeller GUI. These commands may request to open an account, which may be done after checking that the inputted
 information is valid. These commands may also request to close an account, deposit into an account, withdraw from an
 account, and print all the accounts in various ways.
 @author Karan Patel, Azaan Siddiqi
 */
public class BankTellerController {

    private AccountDatabase allAccts = new AccountDatabase();

    @FXML
    private ToggleGroup accountType;

    @FXML
    private ToggleGroup accountType2;

    @FXML
    private TextField balanceAmount;

    @FXML
    private TextField balanceAmount2;

    @FXML
    private ToggleGroup campusCode;

    @FXML
    private DatePicker dateOfBirth;

    @FXML
    private DatePicker dateOfBirth2;

    @FXML
    private TextField firstName;

    @FXML
    private TextField firstName2;

    @FXML
    private TextField lastName;

    @FXML
    private TextField lastName2;

    @FXML
    private RadioButton loyalCustomer;

    @FXML
    private TextArea textAreaDisplay;

    @FXML
    private TextArea textAreaDisplay2;

    @FXML
    private TextArea textAreaDisplay3;


    /**
     Finds the account object that is passed into the method in the AccountDatabase object array and returns its index
     within the array if it exists, or -1 if it doesn't exist.
     @param account the account object that is being searched for in the AccountDatabase object array.
     @return the index of the account object in the array if it exists, or -1 if it doesn't exist in the array.
     */
    private int accountFinder(Account account) {
        for (int i = 0; i < allAccts.getNumAcct(); i++) {
            if (allAccts.getAccounts()[i].equals(account)) {
                return i;
            }
        }
        return AccountDatabase.NOT_FOUND;
    }


    /**
     Checks if there is enough data to deposit or withdraw, if the balance that is being deposited or withdrew is a
     valid amount, and if the balance is positive.
     @param depositOrWithdraw a string that contains "Deposit" if we are depositing into an account, or "Withdraw" if
     we are withdrawing from an account.
     @return true if the deposited or withdrawn balance is valid, false otherwise.
     */
    private boolean depositAndWithdrawChecker(String depositOrWithdraw) {
        if (firstName2.getText().isEmpty() || lastName2.getText().isEmpty() || dateOfBirth2.getValue() == null ||
                accountType2.getSelectedToggle() == null || balanceAmount2.getText().isEmpty()) {
            if (depositOrWithdraw.equals("Deposit")) {
                textAreaDisplay2.appendText("Missing data for depositing into an account.\n");
            } else if (depositOrWithdraw.equals("Withdraw")) {
                textAreaDisplay2.appendText("Missing data for withdrawing from an account.\n");
            }
            return false;
        }
        double balance = 0;
        try {
            balance = Double.parseDouble(balanceAmount2.getText());
        } catch (NumberFormatException invalidBalance) {
            textAreaDisplay2.appendText("Not a valid amount.\n");
            return false;
        }
        if (balance <= 0 && depositOrWithdraw.equals("Deposit")) {
            textAreaDisplay2.appendText("Deposit - amount cannot be 0 or negative.\n");
            return false;
        } else if (balance <= 0 && depositOrWithdraw.equals("Withdraw")) {
            textAreaDisplay2.appendText("Withdraw - amount cannot be 0 or negative.\n");
            return false;
        }
        return true;
    }


    /**
     Prints the accounts in the database in one of many ways. It can print either in the order given, by account type,
     by fees and monthly interests based on the account types, or by first updating the balances based on fees and
     monthly interests and then printing using the order given.
     @param printType A string that contains the way we will print the account. It may say "printAllAccs,"
     "printByAccType," "printWithCalculatedFeesAndInterests," or "printWithUpdatedBalances."
     */
    private void printDatabase(String printType) {
        if (printType.equals("printWithUpdatedBalances")) {
            for (int i = 0; i < allAccts.getNumAcct(); i++) {
                allAccts.getAccounts()[i].updateBalance();
                textAreaDisplay3.appendText(allAccts.print(allAccts.getAccounts()[i]));
                textAreaDisplay3.appendText("\n");
            }
        } else if (printType.equals("printWithCalculatedFeesAndInterests")) {
            for (int i = 0; i < allAccts.getNumAcct(); i++) {
                textAreaDisplay3.appendText(allAccts.printFeeAndInterest(allAccts.getAccounts()[i]));
                textAreaDisplay3.appendText("\n");
            }
        } else {
            for (int i = 0; i < allAccts.getNumAcct(); i++) {
                textAreaDisplay3.appendText(allAccts.print(allAccts.getAccounts()[i]));
                textAreaDisplay3.appendText("\n");
            }
        }
    }


    /**
     Checks if an open account of the same type and Profile as the one passed into the method exists in the database.
     Also checks to see if the account passed into the method is a checking account and a similar college checking
     account already exists in the database, and vice versa.
     @param account account object to be compared against the database to see if another matching account already
     exists.
     @param accType A RadioButton object whose text displays "Checking," "College Checking," "Savings," or
     "Money Market."
     @return true if another matching account already exists, false otherwise.
     */
    private boolean sameAccountsChecker(Account account, RadioButton accType) {
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


    /**
     Checks if the information needed to open an account is valid by checking if there is enough data, if the date of
     birth of the potential account holder is valid, if a valid amount of balance is given, and if the initial deposit
     is positive.
     @param accType A RadioButton object whose text displays "Checking", "College Checking", "Savings", or
     "Money Market."
     @return true if the information given is valid, false if it is not valid.
     */
    private boolean validInformationChecker(RadioButton accType) {
        if (firstName.getText().isEmpty() || lastName.getText().isEmpty() || dateOfBirth.getValue() == null ||
                accType == null || balanceAmount.getText().isEmpty()) {
            textAreaDisplay.appendText("Missing data for opening an account.\n");
            return false;
        }
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


    /**
     Deposits money into an account or withdraws money from an account of a specific type in the database as long as
     the information obtained is valid.
     @param event An ActionEvent object that occurs when the "Deposit" button or the "Withdraw" button is pressed in
     the BankTeller GUI.
     */
    @FXML
    void accountDepositOrWithdraw(ActionEvent event) {
        String depositOrWithdraw = ((Button)event.getSource()).getText();
        if (!(depositAndWithdrawChecker(depositOrWithdraw))) {
            return;
        }
        Profile holder = new Profile(firstName2.getText(), lastName2.getText(), new Date(dateOfBirth2.getValue().toString()));
        Account changeBalance = null;
        RadioButton accType = (RadioButton) accountType2.getSelectedToggle();
        if (accType.getText().equals("Checking")) {
            changeBalance = new Checking(holder, Double.parseDouble(balanceAmount2.getText()));
        } else if (accType.getText().equals("College Checking")) {
            changeBalance = new CollegeChecking(holder, Double.parseDouble(balanceAmount2.getText()), 0);
        } else if (accType.getText().equals("Savings")) {
            changeBalance = new Savings(holder, Double.parseDouble(balanceAmount2.getText()), 0);
        } else if (accType.getText().equals("Money Market")) {
            changeBalance = new MoneyMarket(holder, Double.parseDouble(balanceAmount2.getText()));
        }
        int findMatchingAccountIndex = accountFinder(changeBalance);
        if (findMatchingAccountIndex == AccountDatabase.NOT_FOUND && !(accType.getText().equals("Money Market"))) {
            textAreaDisplay2.appendText(changeBalance.holder + " " + changeBalance.getType() + " is not in the database.\n");
            return;
        } else if (findMatchingAccountIndex == AccountDatabase.NOT_FOUND && (accType.getText().equals("Money Market"))) {
            textAreaDisplay2.appendText(changeBalance.holder + " Money Market is not in the database.\n");
            return;
        }
        if (depositOrWithdraw.equals("Deposit")) {
            allAccts.deposit(changeBalance);
            textAreaDisplay2.appendText("Deposit - balance updated.\n");
        } else if (depositOrWithdraw.equals("Withdraw")) {
            if (!(allAccts.withdraw(changeBalance))) {
                textAreaDisplay2.appendText("Withdraw - insufficient fund.\n");
                return;
            }
            textAreaDisplay2.appendText("Withdraw - balance updated.\n");
        }
    }


    /**
     Closes an account of a specific type as long as the information obtained is valid.
     @param event An ActionEvent object that occurs when the "Close" button is pressed in the BankTeller GUI.
     */
    @FXML
    void closeAccount(ActionEvent event) {
        if (firstName2.getText().isEmpty() || lastName2.getText().isEmpty() || dateOfBirth2.getValue() == null ||
                accountType2.getSelectedToggle() == null) {
            textAreaDisplay2.appendText("Missing data for closing an account.\n");
            return;
        }
        Profile holder = new Profile(firstName2.getText(), lastName2.getText(), new Date(dateOfBirth2.getValue().toString()));
        Account closeAccount = null;
        RadioButton accType = (RadioButton) accountType2.getSelectedToggle();
        if (accType.getText().equals("Checking")) {
            closeAccount = new Checking(holder, 0);
        } else if (accType.getText().equals("College Checking")) {
            closeAccount = new CollegeChecking(holder, 0, 0);
        } else if (accType.getText().equals("Savings")) {
            closeAccount = new Savings(holder, 0, 0);
        } else if (accType.getText().equals("Money Market")) {
            closeAccount = new MoneyMarket(holder, 0);
        }
        if (accountFinder(closeAccount) == AccountDatabase.NOT_FOUND) {
            textAreaDisplay2.appendText("Cannot close an account that is not in the database.\n");
        } else if (allAccts.getAccounts()[accountFinder(closeAccount)].closed == true) {
            textAreaDisplay2.appendText("Account is closed already.\n");
        } else {
            allAccts.close(closeAccount);
            textAreaDisplay2.appendText("Account closed.\n");
        }
    }


    /**
     Opens or reopens an account of a specific type as long as the information obtained is valid.
     @param event An ActionEvent object that occurs when the "Open" button is pressed in the BankTeller GUI.
     */
    @FXML
    void openAccount(ActionEvent event) {
        RadioButton accType = (RadioButton) accountType.getSelectedToggle();
        if (!validInformationChecker(accType)) {
            return;
        }
        Profile holder = new Profile(firstName.getText(), lastName.getText(), new Date(dateOfBirth.getValue().toString()));
        Account addAccount = null;
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
        if (sameAccountsChecker(addAccount, accType)) {
            return;
        }
        if (!(allAccts.open(addAccount))) {
            textAreaDisplay.appendText("Account reopened.\n");
        } else {
            textAreaDisplay.appendText("Account opened.\n");
        }
    }


    /**
     Checks if the number of accounts in the database is 0 or not, and if not, then it calls the printDatabase method
     to print these account in one of the many ways specified according to the button pressed in the GUI.
     @param event An ActionEvent object that occurs when one of the four print buttons are pressed in the BankTeller
     GUI.
     */
    @FXML
    void printAccounts(ActionEvent event) {
        if (allAccts.getNumAcct() == 0) {
            textAreaDisplay3.appendText("Account Database is empty!\n");
            return;
        }
        String printType = ((Button)event.getSource()).getId();
        if (printType.equals("printAllAccs")) {
            textAreaDisplay3.appendText("\n*list of accounts in the database*\n");
            printDatabase(printType);
            textAreaDisplay3.appendText("*end of list*\n");
        } else if (printType.equals("printByAccType")) {
            allAccts.printByAccountType();
            textAreaDisplay3.appendText("\n*list of accounts by account type.\n");
            printDatabase(printType);
            textAreaDisplay3.appendText("*end of list.\n");
        } else if (printType.equals("printWithCalculatedFeesAndInterests")) {
            textAreaDisplay3.appendText("\n*list of accounts with fee and monthly interest\n");
            printDatabase(printType);
            textAreaDisplay3.appendText("*end of list.\n");
        } else if (printType.equals("printWithUpdatedBalances")) {
            textAreaDisplay3.appendText("\n*list of accounts with updated balance\n");
            printDatabase(printType);
            textAreaDisplay3.appendText("*end of list.\n");
        }
    }

}
