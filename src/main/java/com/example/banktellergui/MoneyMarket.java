package com.example.banktellergui;


/**
 This class is a subclass of the Savings class, thus extending all of its methods and applies conditions specific to a
 MoneyMarket account. It has a constructor based of a Profile object and account balance. Along with this it has
 monthlyInterest, fee, and getType defined with proper values in respect to the guidelines of a money market
 account. Also, there is a toString method which returns information about the money market account as a string.
 @author Azaan Siddiqi, Karan Patel
 */
public class MoneyMarket extends Savings {

    protected int numberOfWithdrawl;

    public static final double LOYAL_MONEY_MARKET_MONTHLY_INTEREST_RATE = (0.0095/12);
    public static final double REGULAR_MONEY_MARKET_MONTHLY_INTEREST_RATE = (0.008/12);
    public static final int MONEY_MARKET_WAIVED_THRESHOLD = 2500;
    public static final int MONEY_MARKET_WITHDRAW_THRESHOLD = 3;
    public static final int MONEY_MARKET_FEE_THRESHOLD_MET = 0;
    public static final int MONEY_MARKET_FEE_THRESHOLD_NOT_MET = 10;
    public static final int LOYAL = 1;


    /**
     Creates a MoneyMarket object based on a Profile object and a double denoting account balance. Since the conditions
     of a money market account is that it is loyal, the super method call to savings has a loyal customer code of 1.
     @param holder Profile object representing the holder of the account.
     @param balance balance of the account in USD.
     */
    public MoneyMarket(Profile holder, double balance) {
        super(holder, balance, LOYAL);
    }


    /**
     Calculates the amount of money accrued via interest per month based on account balance, money market monthly
     interest rates, and customer loyalty.
     @return the interest accrued in a month in USD based on money market account rates.
     */
    public double monthlyInterest() {
        if (this.balance < MONEY_MARKET_WAIVED_THRESHOLD) {
            loyalCustomer = false;
        }
        if (loyalCustomer == true) {
            return LOYAL_MONEY_MARKET_MONTHLY_INTEREST_RATE * this.balance;
        }
        return REGULAR_MONEY_MARKET_MONTHLY_INTEREST_RATE * this.balance;
    } //return the monthly interest


    /**
     Calculates the fee needed to be paid in order to maintain a money market account by using the money market account
     balance.
     @return the fee in USD to keep the money market account open.
     */
    public double fee() {
        if (balance >= MONEY_MARKET_WAIVED_THRESHOLD && this.numberOfWithdrawl <= MONEY_MARKET_WITHDRAW_THRESHOLD) {
            return MONEY_MARKET_FEE_THRESHOLD_MET;
        }
        return MONEY_MARKET_FEE_THRESHOLD_NOT_MET;
    } //return the monthly fee


    /**
     Returns the type of the money market account as a string.
     @return a string denoting the type of the account, which is Money Market Savings.
     */
    public String getType() {
        return "Money Market Savings";
    } //return the account type (class name)


    /**
     Converts information regarding a money market account into a string.
     @return a string that follows the format of the Savings toString method and includes the number of withdrawls from
     the account.
     */
    @Override
    public String toString() {
        if (closed == true) {
            this.numberOfWithdrawl = 0;
        }
        return super.toString() + "::withdrawl: " + this.numberOfWithdrawl;
    }
}
