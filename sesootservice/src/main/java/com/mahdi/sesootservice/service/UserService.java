package com.mahdi.sesootservice.service;

import com.mahdi.sesootservice.core.exception.InvalidEmailException;
import com.mahdi.sesootservice.core.exception.InvalidPasswordException;
import com.mahdi.sesootservice.core.exception.NoSuchUserException;
import com.mahdi.sesootservice.core.exception.PermissionDeniedException;
import com.mahdi.sesootservice.entity.User;
import com.mahdi.sesootservice.entity.base.Person;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void signUp(User user) throws InvalidEmailException, InvalidPasswordException;
    User profile(Person person) throws NoSuchUserException, PermissionDeniedException;
    void updateUser(User user) throws NoSuchUserException, InvalidEmailException, InvalidPasswordException;
    List<User> allUsers();

}
