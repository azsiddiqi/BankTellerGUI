package com.example.banktellergui;


/**
 This class is a subclass of the Account class, thus extending all of its methods and applies conditions specific to a
 Checking account. It has a constructor based off of a Profile object and an account balance. Along with this it has
 monthlyInterest, fee, and getType methods defined with proper values in respect to the guidelines of a checking account.
 @author Azaan Siddiqi, Karan Patel
 */
public class Checking extends Account {

    public static final double CHECKING_MONTHLY_INTEREST_RATE = 0.001/12;
    public static final int CHECKING_FEE_IF_BALANCE_ABOVE_ONE_THOUSAND = 0;
    public static final int CHECKING_FEE_WAIVED_THRESHOLD = 1000;
    public static final int CHECKING_FEE_IF_BALANCE_BELOW_ONE_THOUSAND = 25;


    /**
     Creates a checking account object based off of a Profile object and a specified account balance.
     @param holder Profile object representing the account holder.
     @param balance Balance of the account in USD.
     */
    public Checking(Profile holder, double balance) {
        super(holder, balance);
    }


    /**
     Calculates the amount of money accrued via interest per month based on account balance and the checking account
     monthly interest rate.
     @return the interest accrued in a month in USD based on checking account rates.
     */
    public double monthlyInterest() {
        return CHECKING_MONTHLY_INTEREST_RATE * this.balance;
    } //return the monthly interest


    /**
     Calculates the fee needed to be paid in order to maintain a checking account by using the checking account balance.
     @return the fee in USD to keep the checking account open.
     */
    public double fee() {
        if (balance >= CHECKING_FEE_WAIVED_THRESHOLD) {
            return CHECKING_FEE_IF_BALANCE_ABOVE_ONE_THOUSAND;//
        }
        return CHECKING_FEE_IF_BALANCE_BELOW_ONE_THOUSAND;
    } //return the monthly fee


    /**
     Returns the type of the checking account as a string.
     @return a string denoting the type of the account, which is Checking.
     */
    public String getType() {
        return "Checking";
    } //return the account type (class name)

}
