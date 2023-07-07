package com.exercise.hotel.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerValidate {
    private static final String emailRegexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    public static boolean emailValidate(String email) {
        Pattern pattern = Pattern.compile(emailRegexPattern);
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }
}
