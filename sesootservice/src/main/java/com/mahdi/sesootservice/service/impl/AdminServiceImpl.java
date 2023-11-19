package com.mahdi.sesootservice.service.impl;

import com.mahdi.sesootservice.core.exception.*;
import com.mahdi.sesootservice.core.exception.category.InvalidCategoryNameException;
import com.mahdi.sesootservice.core.exception.category.InvalidSubCategoryNameException;
import com.mahdi.sesootservice.core.exception.category.InvalidSubCategoyPriceException;
import com.mahdi.sesootservice.core.exception.user.InvalidEmailException;
import com.mahdi.sesootservice.core.exception.user.InvalidPasswordException;
import com.mahdi.sesootservice.core.exception.user.NoSuchUserException;
import com.mahdi.sesootservice.core.messages.Admin;
import com.mahdi.sesootservice.entity.Category;
import com.mahdi.sesootservice.entity.DTO.AdminUserSearchDto;
import com.mahdi.sesootservice.entity.Expert;
import com.mahdi.sesootservice.entity.SubCategory;
import com.mahdi.sesootservice.entity.User;
import com.mahdi.sesootservice.entity.base.Person;
import com.mahdi.sesootservice.repository.AdminRepo;
import com.mahdi.sesootservice.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminRepo adminRepo;
    private final UserService userService;
    private final CategoryService categoryService;
    private final SubCategoryService subCategoryService;
    private final BCryptPasswordEncoder passwordEncoder;

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
    public void addCategory(Category category, Person person) throws
            NoSuchUserException,
            InvalidCategoryNameException,
            DbConnectionException {
        if (! isAdmin(person))
            throw new NoSuchUserException(Admin.isNotAdmin);
        categoryService.add(category);
    }


    @Override
    public void addSubCategory(SubCategory subCategory, Person person) throws
            DbConnectionException, InvalidSubCategoyPriceException,
            InvalidSubCategoryNameException,
            NoSuchUserException {
        if (! isAdmin(person))
            throw new NoSuchUserException(Admin.isNotAdmin);
        subCategoryService.add(subCategory);
    }

    public List<AdminUserSearchDto> searchUsers(AdminUserSearchDto adminUserSearchDto){
        if (adminUserSearchDto.partOfEmail() != null){

        }
        return null;
    }

    @Override
    public void updateExpert(Expert expert) {

    }

    @Override
    public List<Expert> allExperts() {
        return null;
    }

    private Boolean isAdmin(Person person){
       return adminRepo.findAdminByPerson_Email(person.getEmail()).isPresent();
    }
}
