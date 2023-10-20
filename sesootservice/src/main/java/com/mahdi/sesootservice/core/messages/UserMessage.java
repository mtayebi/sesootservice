package com.mahdi.sesootservice.core.messages;

public class UserMessage {
    public static final String userExists;
    public static final String userIsNotAuthorized;
    public static final String UserIsNotLogin;
    static {
        userExists = "the username or email is already" +
                " taken choose another one or just login";
        userIsNotAuthorized = "you don't have permission to do this action," +
                " please login as an authorized user";
        UserIsNotLogin = "please login first";
    }
}
