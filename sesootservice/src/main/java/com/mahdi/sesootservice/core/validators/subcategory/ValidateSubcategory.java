package com.mahdi.sesootservice.core.validators.subcategory;

import com.mahdi.sesootservice.core.exception.InvalidSubCategoryNameException;
import com.mahdi.sesootservice.core.exception.InvalidSubCategoyPriceException;
import com.mahdi.sesootservice.core.messages.Category;

public class ValidateSubcategory {

    public static void validateName(String name) throws InvalidSubCategoryNameException {
        if (! name.matches("^[a-zA-Z0-9_]*$"))
            throw new InvalidSubCategoryNameException(Category.wrongName);
    }
    public static void validatePriceValue(String price) throws InvalidSubCategoyPriceException {
        if (! price.matches("^[0-9_]*$"))
            throw new InvalidSubCategoyPriceException(Category.wrongName);
    }
}
