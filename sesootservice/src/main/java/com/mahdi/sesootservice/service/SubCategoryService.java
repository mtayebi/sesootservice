package com.mahdi.sesootservice.service;

import com.mahdi.sesootservice.core.exception.DbConnectionException;
import com.mahdi.sesootservice.core.exception.category.InvalidSubCategoryNameException;
import com.mahdi.sesootservice.core.exception.category.InvalidSubCategoyPriceException;
import com.mahdi.sesootservice.core.exception.subcategory.SubCategoryException;
import com.mahdi.sesootservice.entity.SubCategory;

public interface SubCategoryService {

    void add(SubCategory subCategory) throws InvalidSubCategoryNameException, InvalidSubCategoyPriceException, DbConnectionException;

    void update(SubCategoryService subCategory);

    void findById(Long id);
    SubCategory findByName(String name) throws SubCategoryException;
}
