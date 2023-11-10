package com.mahdi.sesootservice.service;

import com.mahdi.sesootservice.core.exception.DbConnectionException;
import com.mahdi.sesootservice.core.exception.user.InvalidEmailException;
import com.mahdi.sesootservice.core.exception.user.InvalidPasswordException;
import com.mahdi.sesootservice.core.exception.user.NoSuchUserException;
import com.mahdi.sesootservice.core.exception.user.PermissionDeniedException;
import com.mahdi.sesootservice.entity.Expert;
import com.mahdi.sesootservice.entity.ExpertOffer;
import com.mahdi.sesootservice.entity.base.Person;
;

import java.util.List;

public interface ExpertService {
    void signUp(Expert expert) throws InvalidPasswordException, InvalidEmailException, DbConnectionException;
    Expert profile(Person person) throws PermissionDeniedException, NoSuchUserException;
    void updateUser(Expert expert);
    List<Expert> allUsers();
    void putOffer(ExpertOffer expertOffer, Person person) throws PermissionDeniedException;

}
