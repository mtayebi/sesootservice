package com.mahdi.sesootservice.core.messages;

public class Category {
    public final static String wrongName;
    public final static String categoryDoseNotExist;

    static {
        wrongName = "The category name should be true alphanumeric string value";
        categoryDoseNotExist = "the category name you entered does not exists";
    }
}
