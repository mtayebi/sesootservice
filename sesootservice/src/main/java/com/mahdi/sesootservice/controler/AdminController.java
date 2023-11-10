package com.mahdi.sesootservice.controler;

import com.mahdi.sesootservice.core.exception.DbConnectionException;
import com.mahdi.sesootservice.core.exception.category.InvalidCategoryNameException;
import com.mahdi.sesootservice.core.exception.category.InvalidSubCategoryNameException;
import com.mahdi.sesootservice.core.exception.category.InvalidSubCategoyPriceException;
import com.mahdi.sesootservice.core.exception.user.NoSuchUserException;
import com.mahdi.sesootservice.core.exception.user.PermissionDeniedException;
import com.mahdi.sesootservice.entity.Category;
import com.mahdi.sesootservice.entity.DTO.AdminUserSearchDto;
import com.mahdi.sesootservice.entity.DTO.CategoryRequestDto;
import com.mahdi.sesootservice.entity.DTO.SubcategoryDto;
import com.mahdi.sesootservice.entity.Expert;
import com.mahdi.sesootservice.entity.SubCategory;
import com.mahdi.sesootservice.entity.base.BaseEntity;
import com.mahdi.sesootservice.entity.base.Person;
import com.mahdi.sesootservice.mapper.CategoryDtoToCategory;
import com.mahdi.sesootservice.mapper.SubcategoryDtoToSubcategory;
import com.mahdi.sesootservice.service.AdminService;
import com.mahdi.sesootservice.service.CategoryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {
    AdminService adminService;
    CategoryService categoryService;
    HttpServletRequest servletRequest;

    public AdminController(AdminService adminService, HttpServletRequest servletRequest, CategoryService categoryService) {
        this.adminService = adminService;
        this.servletRequest = servletRequest;
        this.categoryService = categoryService;
    }


    @PostMapping("/add-category")
    public ResponseEntity<String> addCategory(@Valid @RequestBody CategoryRequestDto categoryRequestDto, Model model){
        Category category = CategoryDtoToCategory.INSTANCE.categoryDtoToCategory(categoryRequestDto);
        Person person = (Person) servletRequest.getSession().getAttribute("person");

        try {
            adminService.addCategory(category, person);
        } catch (InvalidCategoryNameException e) {
            throw new RuntimeException(e);
        } catch (DbConnectionException e) {
            throw new RuntimeException(e);
        } catch (NoSuchUserException e) {
            throw new RuntimeException(e);
        } catch (PermissionDeniedException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok("category created successfully");
    }

    @PostMapping("/add-subcategory")
    public ResponseEntity<String> addSubCategory(@Valid @RequestBody SubcategoryDto subcategoryDto){

        SubCategory subCategory = SubcategoryDtoToSubcategory.INSTANCE.convert(subcategoryDto);
        Person person = (Person) servletRequest.getSession().getAttribute("person");
        Optional<Category> category = categoryService.findCategoryByName(subcategoryDto.categoryName());
        if (category.isEmpty()){
            throw new RuntimeException(com.mahdi.sesootservice.core.messages.Category.categoryDoseNotExist);
        }

        subCategory.setCategory(category.get());

        try {
            adminService.addSubCategory(subCategory, person);
        } catch (DbConnectionException e) {
            throw new RuntimeException(e);
        } catch (InvalidSubCategoyPriceException e) {
            throw new RuntimeException(e);
        } catch (InvalidSubCategoryNameException e) {
            throw new RuntimeException(e);
        } catch (PermissionDeniedException e) {
            throw new RuntimeException(e);
        } catch (NoSuchUserException e) {
            throw new RuntimeException(e);
        }


        return ResponseEntity.ok("subcategory has created successfully");
    }

    @GetMapping("/filter-users")
    public List<AdminUserSearchDto> listOfFilteredUsers(AdminUserSearchDto adminUserSearchDto){


        return null;
    }
}
