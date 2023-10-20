package com.mahdi.sesootservice.service;

import com.mahdi.sesootservice.core.exception.DbConnectionException;
import com.mahdi.sesootservice.core.exception.InvalidSubCategoryNameException;
import com.mahdi.sesootservice.core.exception.InvalidSubCategoyPriceException;
import com.mahdi.sesootservice.entity.SubCategory;

public interface SubCategoryService {

    void add(SubCategory subCategory) throws InvalidSubCategoryNameException, InvalidSubCategoyPriceException, DbConnectionException;

    void update(SubCategoryService subCategory);
}
