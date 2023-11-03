package com.mahdi.sesootservice.service;

import com.mahdi.sesootservice.core.exception.DbConnectionException;
import com.mahdi.sesootservice.core.exception.category.InvalidCategoryNameException;
import com.mahdi.sesootservice.entity.Category;

import java.util.Optional;

public interface CategoryService {
    void add(Category category) throws InvalidCategoryNameException, DbConnectionException;
    void update(Category category);
    public Optional<Category> findCategoryByName(String name);

}
