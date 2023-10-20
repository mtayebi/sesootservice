package com.mahdi.sesootservice.core.validators.category;

import com.mahdi.sesootservice.core.exception.InvalidCategoryNameException;
import com.mahdi.sesootservice.core.messages.Category;

public class ValidateCategory {
    public static void validateCategoryName(String name) throws InvalidCategoryNameException {
        if (! name.matches("^[a-zA-Z0-9_]*$"))
            throw new InvalidCategoryNameException(Category.wrongName);
    }

}
