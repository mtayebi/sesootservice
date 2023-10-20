package com.mahdi.sesootservice.core.service.auth.impl;

import com.mahdi.sesootservice.core.exception.InvalidEmailException;
import com.mahdi.sesootservice.core.exception.NoSuchUserException;
import com.mahdi.sesootservice.core.exception.PermissionDeniedException;
import com.mahdi.sesootservice.core.messages.Login;
import com.mahdi.sesootservice.core.messages.UserMessage;
import com.mahdi.sesootservice.core.repository.auth.AuthRepo;
import com.mahdi.sesootservice.core.service.auth.AuthService;
import com.mahdi.sesootservice.core.validators.person.ValidateLoginParams;
import com.mahdi.sesootservice.entity.base.Person;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AuthServiceImpl implements AuthService {
    private final AuthRepo loginRepo;
    private final HttpServletRequest httpServletRequest;
    public AuthServiceImpl(AuthRepo loginRepo, HttpServletRequest httpServletRequest){
        this.loginRepo = loginRepo;
        this.httpServletRequest = httpServletRequest;
    }

    @Override
    public Person authenticatePerson(String email, String password) throws NoSuchUserException {
        try {
            ValidateLoginParams.validateEmail(email);
        } catch (InvalidEmailException e) {
            throw new NoSuchUserException(Login.wrongLoginParams);
        }catch (RuntimeException e){
            throw new RuntimeException(
                    "an unknown error occurred try again or contact support if the error persists"
            );
        }

        Optional<Person> person =loginRepo.findPersonByEmailAndPassword(email, password);
        return person.orElseThrow(() -> new NoSuchUserException(Login.wrongLoginParams));
    }
    public void personIsAuthenticated(Person person) throws PermissionDeniedException {
     HttpSession session = httpServletRequest.getSession();
     Person authenticatedPerson = (Person) session.getAttribute("person");
     if (authenticatedPerson == null){
         throw new PermissionDeniedException(UserMessage.UserIsNotLogin);
     }
     if (! authenticatedPerson.equals(person)){
         throw new PermissionDeniedException(UserMessage.userIsNotAuthorized);
        }
    }
}
