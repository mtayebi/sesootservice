package com.mahdi.sesootservice.service.impl;

import com.mahdi.sesootservice.core.exception.DbConnectionException;
import com.mahdi.sesootservice.core.exception.category.InvalidCategoryNameException;
import com.mahdi.sesootservice.core.messages.DbConnection;
import com.mahdi.sesootservice.core.validators.category.ValidateCategory;
import com.mahdi.sesootservice.entity.Category;
import com.mahdi.sesootservice.repository.CategoryRepo;
import com.mahdi.sesootservice.service.CategoryService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public Optional<Category> findCategoryByName(String name){
        return categoryRepo.findByName(name);
    }

    @Override
    @Transactional
    public void update(Category category) {

    }
}
