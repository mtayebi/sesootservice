package com.mahdi.sesootservice.core.validators.person;


import com.mahdi.sesootservice.core.exception.user.InvalidEmailException;
import com.mahdi.sesootservice.core.exception.user.InvalidPasswordException;

public class ValidateLoginParams {
    public static void validateEmail(String email, String... message) throws InvalidEmailException {
        if (!email.matches("([\\w\\.\\-_]+)?\\w+@[\\w-_]+(\\.\\w+){1,}")) {
            throw new InvalidEmailException(
                    message!=null ? message[0] :"the entered email is not valid");
        }
    }

    public static void validatePassword(String passwoed) throws InvalidPasswordException {
        if (!passwoed.matches("(?=.*\\d)(?=.*\\d)(?=.*[a-zA-Z]).{8,}")) {
            throw new InvalidPasswordException("the password is wrong");
        }
    }
}
