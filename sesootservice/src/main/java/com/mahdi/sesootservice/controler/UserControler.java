package com.mahdi.sesootservice.controler;

import com.mahdi.sesootservice.core.exception.orders.OrderException;
import com.mahdi.sesootservice.core.exception.subcategory.SubCategoryException;
import com.mahdi.sesootservice.core.exception.user.InvalidEmailException;
import com.mahdi.sesootservice.core.exception.user.InvalidPasswordException;
import com.mahdi.sesootservice.core.exception.user.NoSuchUserException;
import com.mahdi.sesootservice.core.exception.user.PermissionDeniedException;
import com.mahdi.sesootservice.entity.DTO.UserOrderDto;
import com.mahdi.sesootservice.entity.Orders;
import com.mahdi.sesootservice.entity.SubCategory;
import com.mahdi.sesootservice.mapper.UserOrderDtoToOrder;
import com.mahdi.sesootservice.entity.DTO.SignUpDto;
import com.mahdi.sesootservice.entity.DTO.UserProfileDto;
import com.mahdi.sesootservice.entity.User;
import com.mahdi.sesootservice.entity.base.Person;
import com.mahdi.sesootservice.mapper.UserProfileMapper;
import com.mahdi.sesootservice.service.OrdersService;
import com.mahdi.sesootservice.service.SubCategoryService;
import com.mahdi.sesootservice.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.sql.SQLException;

@RestController
@RequestMapping("/user")
public class UserControler {
    private UserService userService;
    private final HttpServletRequest servletRequest;
    private SubCategoryService subCategoryService;
    private OrdersService ordersService;
    public UserControler(UserService userService, HttpServletRequest servletRequest, SubCategoryService subCategoryService, OrdersService ordersService){
        this.userService = userService;
        this.servletRequest = servletRequest;
        this.subCategoryService = subCategoryService;
        this.ordersService = ordersService;
    }
    @PostMapping("/signup")
    public UserProfileDto userSignup(@Valid SignUpDto userSignUpDto, @RequestPart MultipartFile picture){
        Person person = Person.builder()
                .fullName(userSignUpDto.fullName())
                .password(userSignUpDto.password())
                .email(userSignUpDto.email())
                .enabled(true)
                .build();
        try {
            byte[] p = picture.getBytes();
            person.setPicture(new SerialBlob(p));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SerialException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        User user = User.builder()
                .person(person)
                .build();
        try {
            userService.signUp(user);
        } catch (InvalidEmailException e) {
            throw new RuntimeException(e);
        } catch (InvalidPasswordException e) {
            throw new RuntimeException(e);
        }
        UserProfileDto userProfileDto = new UserProfileDto(person.getFullName(),
                person.getEmail(),
                person.getPicture(),
                user.getCredit());
        return userProfileDto;
    }

    @GetMapping("/profile")
    @PreAuthorize("hasRole('ROLE_USER')")
    public UserProfileDto userProfile(){
        Person person = (Person)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user;
        try {
            user = userService.profile(person);
        } catch (NoSuchUserException e) {
            throw new RuntimeException(e.getMessage());
        } catch (PermissionDeniedException e) {
            throw new RuntimeException(e.getMessage());
        }

        return UserProfileMapper.INSTANCE.userToProfileDto(user);
    }

    @PostMapping("/putorder")
    @PreAuthorize("hasRole('ROLE_USER')")
    public void putOrder(@RequestBody UserOrderDto orderDto){
        SubCategory subCategory;
        User user;
        try {
            subCategory = subCategoryService.findByName(orderDto.subCategoryName());
        } catch (SubCategoryException e) {
            throw new RuntimeException(e);
        }
        Person person = (Person) servletRequest.getSession().getAttribute("person");
        try {
            user = userService.profile(person);
        } catch (NoSuchUserException e) {
            throw new RuntimeException(e);
        } catch (PermissionDeniedException e) {
            throw new RuntimeException(e);
        }
        Orders order = UserOrderDtoToOrder.INSTANCE.orderDtoToOrder(orderDto);
        order.setSubCategory(subCategory);
        order.setUser(user);
        try {
            ordersService.putOrder(order);
        } catch (OrderException e) {
            throw new RuntimeException(e);
        } catch (SubCategoryException e) {
            throw new RuntimeException(e);
        }

    }

}
