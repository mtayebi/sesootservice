package com.mahdi.sesootservice.service;

import com.mahdi.sesootservice.core.exception.DbConnectionException;
import com.mahdi.sesootservice.core.exception.InvalidCategoryNameException;
import com.mahdi.sesootservice.entity.Category;

public interface CategoryService {
    void add(Category category) throws InvalidCategoryNameException, DbConnectionException;
    void update(Category category);

}
