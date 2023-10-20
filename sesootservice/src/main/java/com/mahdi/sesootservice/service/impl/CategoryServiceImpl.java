package com.mahdi.sesootservice.service.impl;

import com.mahdi.sesootservice.core.exception.DbConnectionException;
import com.mahdi.sesootservice.core.exception.InvalidCategoryNameException;
import com.mahdi.sesootservice.core.messages.DbConnection;
import com.mahdi.sesootservice.core.validators.category.ValidateCategory;
import com.mahdi.sesootservice.entity.Category;
import com.mahdi.sesootservice.repository.CategoryRepo;
import com.mahdi.sesootservice.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    CategoryRepo categoryRepo;

    public CategoryServiceImpl(CategoryRepo categoryRepo) {

        this.categoryRepo = categoryRepo;
    }

    @Override
    public void add(Category category) throws InvalidCategoryNameException, DbConnectionException {

        ValidateCategory.validateCategoryName(category.getName());
        try {
            categoryRepo.save(category);
        }catch (Exception e){
            throw new DbConnectionException(DbConnection.dbConnectionUnknownError);
        }
    }

    @Override
    public void update(Category category) {

    }
}
