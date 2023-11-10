package com.mahdi.sesootservice.service;

import com.mahdi.sesootservice.core.exception.user.InvalidEmailException;
import com.mahdi.sesootservice.core.exception.user.InvalidPasswordException;
import com.mahdi.sesootservice.core.exception.user.NoSuchUserException;
import com.mahdi.sesootservice.core.exception.user.PermissionDeniedException;
import com.mahdi.sesootservice.entity.Orders;
import com.mahdi.sesootservice.entity.User;
import com.mahdi.sesootservice.entity.base.Person;

import java.util.List;

public interface UserService {
    void signUp(User user) throws InvalidEmailException, InvalidPasswordException;
    User profile(Person person) throws NoSuchUserException, PermissionDeniedException;
    void updateUser(User user) throws NoSuchUserException, InvalidEmailException, InvalidPasswordException;
    List<User> allUsers();
    public void putOrder(Orders order);

}
