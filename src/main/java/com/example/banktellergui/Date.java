package com.example.banktellergui;

import java.util.Calendar;


/**
 This class creates a Date object based off of a string input in the format of mm/dd/yyyy or if not parameterized it
 creates a Date object based on the current date. It also checks to see if the date created is valid and compares the
 Date object to other Date objects. It also can represent the Date object as a string.
 @author Azaan Siddiqi, Karan Patel
 */
public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;
    public static final int JANUARY = 1;
    public static final int FEBRUARY = 2;
    public static final int MARCH = 3;
    public static final int APRIL = 4;
    public static final int MAY = 5;
    public static final int JUNE = 6;
    public static final int JULY = 7;
    public static final int AUGUST = 8;
    public static final int SEPTEMBER = 9;
    public static final int OCTOBER = 10;
    public static final int NOVEMBER = 11;
    public static final int DECEMBER = 12;
    public static final int FEBRUARY_LEAP_YEAR_MAX_DAYS = 29;
    public static final int FEBRUARY_NORMAL_MAX_DAYS = 28;
    public static final int FIRST_DAY_OF_MONTH = 1;
    public static final int MAX_DAYS_OF_MONTH_THIRTY_ONE = 31;
    public static final int MAX_DAYS_OF_MONTH_THIRTY = 30;
    public static final int NUMBER_OF_DATE_COMPONENTS = 3;


    /**
     Creates a date object based on a string.
     @param date string in mm/dd/yyyy format
     */
    public Date(String date) {
        String[] separateDate = date.split("/");
        int[] convertToInt = new int[NUMBER_OF_DATE_COMPONENTS];
        for (int i = 0; i < convertToInt.length; i++) {
            convertToInt[i] = Integer.parseInt(separateDate[i]);
        }
        this.year = convertToInt[2];
        this.month = convertToInt[0];
        this.day = convertToInt[1];
    }


    /**
     Creates a date object of the current date.
     */
    public Date() {
        Calendar today = Calendar.getInstance();
        this.year = today.get(Calendar.YEAR);
        this.month = today.get(Calendar.MONTH) + 1;
        this.day = today.get(Calendar.DAY_OF_MONTH);
    }


    /**
     Checks to see if the created Date object is a valid date.
     @return true if date is valid and false if date is invalid.
     */
    public boolean isValid() {
        if (this.year >= 0 && this.year % QUADRENNIAL == 0 && this.month == FEBRUARY && this.day >= FIRST_DAY_OF_MONTH
                && this.day <= FEBRUARY_LEAP_YEAR_MAX_DAYS) {
            if (this.year % CENTENNIAL == 0) {
                if (this.year % QUATERCENTENNIAL == 0) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return true;
            }
        } else if (this.year >= 0 && this.day >= FIRST_DAY_OF_MONTH && this.day <= MAX_DAYS_OF_MONTH_THIRTY_ONE &&
                (this.month == JANUARY || this.month == MARCH || this.month == MAY || this.month == JULY ||
                        this.month == AUGUST || this.month == OCTOBER || this.month == DECEMBER)) {
            return true;
        } else if (this.year >= 0 && this.day >= FIRST_DAY_OF_MONTH && this.day <= MAX_DAYS_OF_MONTH_THIRTY &&
                (this.month == APRIL || this.month == JUNE || this.month == SEPTEMBER || this.month == NOVEMBER)) {
            return true;
        } else if (this.year >= 0 && this.month == FEBRUARY && this.day >= FIRST_DAY_OF_MONTH && this.day <=
                FEBRUARY_NORMAL_MAX_DAYS) {
            return true;
        } else {
            return false;
        }
    }


    /**
     Compares date object to another specified date object and returns a value based on its relative position.
     @param date the specified date object that the original date object is being compared to.
     @return returns -1 if the first date is before, 1 if it's after, or 0 if both dates are the same.
     */
    @Override
    public int compareTo(Date date) {
        if (this.year - date.year == 0 && this.month - date.month == 0 && this.day - date.day == 0) {
            return 0;
        } else if (this.year - date.year > 0) {
            return 1;
        } else if (this.year - date.year == 0 && this.month - date.month > 0) {
            return 1;
        } else if (this.year - date.year == 0 && this.month - date.month == 0 && this.day - date.day > 0) {
            return 1;
        } else if (this.year - date.year < 0) {
            return -1;
        } else if (this.year - date.year == 0 && this.month - date.month < 0) {
            return -1;
        } else if (this.year - date.year == 0 && this.month - date.month == 0 && this.day - date.day < 0) {
            return -1;
        } else {
            return 0;
        }
    }


    /**
     Converts Date object to a string.
     @return string of the date in mm/dd/yyyy format.
     */
    @Override
    public String toString() {
        return this.month + "/" + this.day + "/" + this.year;
    }
}
