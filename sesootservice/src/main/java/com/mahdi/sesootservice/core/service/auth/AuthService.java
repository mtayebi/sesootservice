package com.mahdi.sesootservice.core.service.auth;

import com.mahdi.sesootservice.core.exception.user.NoSuchUserException;
import com.mahdi.sesootservice.core.exception.user.PermissionDeniedException;
import com.mahdi.sesootservice.entity.base.Person;


public interface AuthService {
    Person authenticatePerson(String email,String Password) throws NoSuchUserException;
    void personIsAuthenticated(Person person) throws PermissionDeniedException;
}
