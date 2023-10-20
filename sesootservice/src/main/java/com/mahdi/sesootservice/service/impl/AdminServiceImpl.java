package com.mahdi.sesootservice.service.impl;

import com.mahdi.sesootservice.core.exception.*;
import com.mahdi.sesootservice.entity.Category;
import com.mahdi.sesootservice.entity.Expert;
import com.mahdi.sesootservice.entity.SubCategory;
import com.mahdi.sesootservice.entity.User;
import com.mahdi.sesootservice.repository.AdminRepo;
import com.mahdi.sesootservice.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    AdminRepo adminRepo;
    UserService userService;
    ExpertService expertService;
    CategoryService categoryService;
    SubCategoryService subCategoryService;

    @Autowired
    public AdminServiceImpl(
            AdminRepo adminRepo,
            UserService userService,
            ExpertService expertService,
            CategoryService categoryService,
            SubCategoryService subCategoryService) {
        this.adminRepo = adminRepo;
        this.userService = userService;
        this.expertService = expertService;
        this.categoryService = categoryService;
        this.subCategoryService = subCategoryService;
    }

    @Override
    public void updateUser(User user) throws NoSuchUserException,
            InvalidPasswordException, InvalidEmailException {
        userService.updateUser(user);
    }

    @Override
    public List<User> AllUsers() {
        return userService.allUsers();
    }

    @Override
    public void addCategory(Category category) throws InvalidCategoryNameException, DbConnectionException {
        categoryService.add(category);
    }


    @Override
    public void addSubCategory(SubCategory subCategory) throws
            DbConnectionException, InvalidSubCategoyPriceException, InvalidSubCategoryNameException {
        subCategoryService.add(subCategory);
    }

    @Override
    public void updateExpert(Expert expert) {

    }

    @Override
    public List<Expert> allExperts() {
        return null;
    }
}
