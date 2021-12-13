package com.example.contactapp;
import java.util.regex.Pattern;

public class CheckWhileAdding {
    public static boolean checkPhone(String phone) {
        if (phone.length() == 10) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean empty(String name, String mob) {
        if (name.equals("") || mob.equals("")) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean checkMail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
}
