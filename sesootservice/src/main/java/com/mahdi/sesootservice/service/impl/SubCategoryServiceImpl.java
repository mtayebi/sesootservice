package com.mahdi.sesootservice.service.impl;

import com.mahdi.sesootservice.core.exception.DbConnectionException;
import com.mahdi.sesootservice.core.exception.category.InvalidSubCategoryNameException;
import com.mahdi.sesootservice.core.exception.category.InvalidSubCategoyPriceException;
import com.mahdi.sesootservice.core.exception.subcategory.SubCategoryException;
import com.mahdi.sesootservice.core.messages.DbConnection;
import com.mahdi.sesootservice.core.messages.Subcategory;
import com.mahdi.sesootservice.core.validators.subcategory.ValidateSubcategory;
import com.mahdi.sesootservice.entity.SubCategory;
import com.mahdi.sesootservice.repository.SubCategoryRepo;
import com.mahdi.sesootservice.service.SubCategoryService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {
    SubCategoryRepo subCategoryRepo;

    public SubCategoryServiceImpl(SubCategoryRepo subCategoryRepo) {
        this.subCategoryRepo = subCategoryRepo;
    }

    @Override
    public void add(SubCategory subCategory) throws InvalidSubCategoryNameException, InvalidSubCategoyPriceException, DbConnectionException {
        ValidateSubcategory.validateName(subCategory.getName());
        ValidateSubcategory.validatePriceValue(subCategory.getBasePrice());
        try {
            subCategoryRepo.save(subCategory);
        }catch (Exception e){
            throw new DbConnectionException(DbConnection.dbConnectionUnknownError);
        }
    }


    @Override
    public void update(SubCategoryService subCategory) {

    }

    @Override
    public void findById(Long id) {
        subCategoryRepo.findById(id);
    }

    @Override
    public SubCategory findByName(String name) throws SubCategoryException {
        Optional<SubCategory> subCategory= subCategoryRepo.findByName(name);
        if (subCategory.isEmpty())
            throw new SubCategoryException(Subcategory.doesNotExist);
        return subCategory.get();
    }
}
