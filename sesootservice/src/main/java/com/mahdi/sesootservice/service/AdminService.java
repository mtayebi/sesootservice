package com.mahdi.sesootservice.service;

import com.mahdi.sesootservice.core.exception.*;
import com.mahdi.sesootservice.entity.Category;
import com.mahdi.sesootservice.entity.Expert;
import com.mahdi.sesootservice.entity.SubCategory;
import com.mahdi.sesootservice.entity.User;

import java.util.List;

public interface AdminService {
    void updateUser(User user) throws NoSuchUserException, InvalidPasswordException, InvalidEmailException;
    List<User> AllUsers();
    void addCategory(Category category) throws InvalidCategoryNameException, DbConnectionException;
    void addSubCategory(SubCategory subCategory) throws DbConnectionException, InvalidSubCategoyPriceException, InvalidSubCategoryNameException;
    void updateExpert(Expert expert);
    List<Expert> allExperts();

}
