package com.example.banktellergui;


/**
 This class is a subclass of the Checking class, thus extending all of its methods and applies conditions specific to a
 CollegeChecking account. It has a constructor based off of a Profile object, account balance, and a campus code. Along
 with this it has monthlyInterest, fee, and getType methods defined with proper values in respect to the guidelines of a
 college checking account. Also, there is a toString method which returns information about the CollegeChecking account
 as a string.
 @author Azaan Siddiqi, Karan Patel
 */
public class CollegeChecking extends Checking {

    protected String campusName;

    public static final double COLLEGE_CHECKING_MONTHLY_INTEREST_RATE = 0.0025/12;
    public static final int NEW_BRUNSWICK_CAMPUS_CODE = 0;
    public static final int NEWARK_CAMPUS_CODE = 1;
    public static final int CAMDEN_CAMPUS_CODE = 2;
    public static final int COLLEGE_CHECKING_FEE = 0;


    /**
     Creates a CollegeChecking object based on a Profile object, a double denoting account balance, and an integer
     denoting the college campus of the account holder.
     @param holder Profile object representing the holder of the account.
     @param balance balance of the account in USD.
     @param campusCode Determines whether the student is from the NEW_BRUNSWICK, NEWARK, or CAMDEN campus.
     */
    public CollegeChecking(Profile holder, double balance, int campusCode) {
        super(holder, balance);
        if (campusCode == NEW_BRUNSWICK_CAMPUS_CODE) {
            this.campusName = "NEW_BRUNSWICK";
        } else if (campusCode == NEWARK_CAMPUS_CODE) {
            this.campusName = "NEWARK";
        } else if (campusCode == CAMDEN_CAMPUS_CODE) {
            this.campusName = "CAMDEN";
        }
    }


    /**
     Calculates the amount of money accrued via interest per month based on account balance and the college checking
     account monthly interest rate.
     @return the interest accrued in a month in USD based on college checking account rates.
     */
    public double monthlyInterest() {
        return  COLLEGE_CHECKING_MONTHLY_INTEREST_RATE * this.balance;
    } //return the monthly interest


    /**
     Calculates the fee needed to be paid in order to maintain a college checking account by using the college checking
     account balance.
     @return the fee in USD to keep the college checking account open.
     */
    public double fee() {
        return COLLEGE_CHECKING_FEE;
    } //return the monthly fee


    /**
     Returns the type of the CollegeChecking account as a string.
     @return a string denoting the type of the account, which is College Checking.
     */
    public String getType() {
        return "College Checking";
    } //return the account type (class name)


    /**
     Converts information regarding a college checking account into a string.
     @return a string that follows the format of the Account and Checking toString methods, and includes the campus name
     of the profile holder.
     */
    @Override
    public String toString() {
        return super.toString() + "::" + this.campusName;
    }
}
