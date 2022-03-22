package com.example.banktellergui;


/**
 This class is a subclass of the Account class, thus extending all of its methods and applies conditions specific to a
 Savings account. It has a constructor based off of a Profile object, Account balance, and a loyalCustomerCode which
 denotes whether a customer is loyal or not. Along with this it has monthlyInterest, fee, and getType methods defined
 with proper values in respect to the guidelines of a Savings account. Also, there is a toString method which returns
 information about the Savings account as a string.
 @author Azaan Siddiqi, Karan Patel
 */
public class Savings extends Account {

    protected boolean loyalCustomer;

    public static final double LOYAL_SAVINGS_MONTHLY_INTEREST_RATE = 0.0045/12;
    public static final double REGULAR_SAVINGS_MONTHLY_INTEREST_RATE = 0.003/12;
    public static final int SAVINGS_FEE_WAIVED_THRESHOLD = 300;
    public static final int SAVINGS_FEE_IF_BALANCE_ABOVE_THREE_HUNDRED = 0;
    public static final int SAVINGS_FEE_IF_BALANCE_UNDER_THREE_HUNDRED = 6;
    public static final int NOT_LOYAL = 0;


    /**
     Creates a Savings object based on a Profile object, a double denoting account balance, and an integer denoting
     whether the holder is a loyal customer or not.
     @param holder Profile object representing the account holder.
     @param balance Savings account balance in USD.
     @param loyalCustomerCode integer which is 0 if the customer isn't loyal, and 1 if the customer is loyal.
     */
    public Savings(Profile holder, double balance, int loyalCustomerCode) {
        super(holder, balance);
        if (loyalCustomerCode == NOT_LOYAL) {
            this.loyalCustomer = false;
        } else {
            this.loyalCustomer = true;
        }
    }


    /**
     Calculates the amount of money accrued via interest per month based on account balance and savings account monthly
     interest rates.
     @return the interest accrued in a month in USD based on savings account rates.
     */
    public double monthlyInterest() {
        if (loyalCustomer == true) {
            return LOYAL_SAVINGS_MONTHLY_INTEREST_RATE * this.balance;
        }
        return REGULAR_SAVINGS_MONTHLY_INTEREST_RATE * this.balance;
    } //return the monthly interest


    /**
     Calculates the fee needed to be paid in order to maintain a savings account by using the savings account balance.
     The fee will be 0 if the savings account is closed.
     @return the fee in USD to keep the savings account open, but the fee is 0 if the savings account is closed.
     */
    public double fee() {
        if (closed == true){
            return 0;
        }
        if (balance >= SAVINGS_FEE_WAIVED_THRESHOLD) {
            return SAVINGS_FEE_IF_BALANCE_ABOVE_THREE_HUNDRED;
        }
        return SAVINGS_FEE_IF_BALANCE_UNDER_THREE_HUNDRED;
    } //return the monthly fee


    /**
     Returns the type of the savings account as a string.
     @return a string denoting the type of the account, which is Savings.
     */
    public String getType() {
        return "Savings";
    } //return the account type (class name)


    /**
     Converts information regarding a savings account into a string.
     @return a string that follows the format of the Account toString method and includes whether the customer is loyal
     or not.
     */
    @Override
    public String toString() {
        if (loyalCustomer == true) {
            return super.toString() + "::Loyal";
        }
        return super.toString();
    }
}
