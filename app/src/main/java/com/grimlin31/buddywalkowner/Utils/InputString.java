package com.grimlin31.buddywalkowner.Utils;

import android.util.Patterns;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

import java.util.regex.Pattern;

public class InputString {

    public static boolean isPassValid(@NonNull String pass) {
        /*Pattern validator = Pattern.compile(
                "^" + "(?=.*[0-9])"
                        + "(?=.*[a-z])"
                        + "(?=.*[A-Z])"
                        + "(?=.*[@#$%^^+=])"
                        + "(?=\\S+$)"
                        + ".{4,}"
                        + "$"
        );*/
        if (pass.isEmpty()) return false;
        //else if (!validator.matcher(pass).matches()) return false;
        else return true;
    }

    public static boolean isEmailValid(@NonNull String user) {
        if (user.isEmpty()) return false;
        else if (!Patterns.EMAIL_ADDRESS.matcher(user).matches()) return false;
        else return true;
    }

    public static boolean isPassConfirmed(String pass, String secondPass) {
        return pass.equals(secondPass);
    }
}
