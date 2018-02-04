package com.test.employees.other;

public class StringUtils {
    public static String wordCaseString(int value, String string1, String string2, String string5) {
        if (value > 4 && value < 21)
            return string5;
        int lastDigit = value % 10;
        if (lastDigit == 1)
            return string1;
        if (lastDigit > 1 && lastDigit < 5)
            return string2;
        return string5;
    }

    public static String capitalize(String input) {
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }
}
