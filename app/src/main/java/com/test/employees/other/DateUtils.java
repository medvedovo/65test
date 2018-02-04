package com.test.employees.other;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

public class DateUtils {
    private static final String yearMonthDayRegex = "(\\d{4}-\\d{2}-\\d{2})";
    private static final String dayMonthYearRegex = "(\\d{2}-\\d{2}-\\d{4})";

    public static Date stringToDate(String input) {
        if (input == null || input.isEmpty()) {
            return null;
        }
        if (Pattern.compile(yearMonthDayRegex).matcher(input).matches()) {
            try {
                return (new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())).parse(input);
            } catch (ParseException e) {
                Log.e("", e.getMessage());
            }
        } else if (Pattern.compile(dayMonthYearRegex).matcher(input).matches()) {
            try {
                return (new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())).parse(input);
            } catch (ParseException e) {
                Log.e("", e.getMessage());
            }
        } else {
            return null;
        }
        return null;
    }

    public static int calculateAge(Date birthday) {
        Calendar today = Calendar.getInstance();
        Calendar birthDate = Calendar.getInstance();
        birthDate.setTime(birthday);
        if (birthDate.after(today)) {
            return 0;
        }
        int todayYear = today.get(Calendar.YEAR);
        int birthDateYear = birthDate.get(Calendar.YEAR);
        int todayDayOfYear = today.get(Calendar.DAY_OF_YEAR);
        int birthDateDayOfYear = birthDate.get(Calendar.DAY_OF_YEAR);
        int todayMonth = today.get(Calendar.MONTH);
        int birthDateMonth = birthDate.get(Calendar.MONTH);
        int todayDayOfMonth = today.get(Calendar.DAY_OF_MONTH);
        int birthDateDayOfMonth = birthDate.get(Calendar.DAY_OF_MONTH);
        int age = todayYear - birthDateYear;

        if ((birthDateDayOfYear - todayDayOfYear > 3) || (birthDateMonth > todayMonth)) {
            age--;

        } else if ((birthDateMonth == todayMonth) && (birthDateDayOfMonth > todayDayOfMonth)) {
            age--;
        }
        return age;
    }

    public static int calculateAge(String birthday) {
        Date birthdate = stringToDate(birthday);
        if (birthdate != null) {
            return calculateAge(birthdate);
        }
        return 0;
    }
}
