package com.mahdi.sesootservice.service.impl;

import com.mahdi.sesootservice.core.exception.*;
import com.mahdi.sesootservice.core.messages.Login;
import com.mahdi.sesootservice.core.messages.UserMessage;
import com.mahdi.sesootservice.core.service.auth.AuthService;
import com.mahdi.sesootservice.core.service.auth.impl.AuthServiceImpl;
import com.mahdi.sesootservice.core.validators.person.ValidateLoginParams;
import com.mahdi.sesootservice.entity.User;
import com.mahdi.sesootservice.entity.base.Person;
import com.mahdi.sesootservice.repository.UserRepo;
import com.mahdi.sesootservice.service.UserService;
import jakarta.persistence.PersistenceException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@Service
public class UserServiceImpl implements UserService {

    UserRepo userRepo;
    AuthService authService;

    public UserServiceImpl(UserRepo userRepo, AuthService authService) {
        this.userRepo = userRepo;
        this.authService = authService;
    }

    @Override
    public void signUp(User user) throws InvalidEmailException, InvalidPasswordException {
        ValidateLoginParams.validateEmail(user.getPerson().getEmail());
        ValidateLoginParams.validatePassword(user.getPerson().getPassword());
        try{
            userRepo.save(user);
        }catch (DataIntegrityViolationException e){
            throw new UserAlreadyExistsException(UserMessage.userExists);
        }catch (Exception e){
            System.out.println("ssss");
        }

    }

    @Override
    public User profile(Person person) throws NoSuchUserException, PermissionDeniedException {
        authService.personIsAuthenticated(person);
        Optional<User> user = userRepo.findUserByPerson_Email(person.getEmail());
        if (user.isEmpty())
            throw new NoSuchUserException(Login.wrongLoginParams);

        return user.orElseThrow(() -> new NoSuchUserException(Login.wrongLoginParams));
    }

    @Override
    public void updateUser(User user) throws NoSuchUserException,
            InvalidEmailException, InvalidPasswordException {
        if(user.getId().equals(null))
            throw new NoSuchUserException(Login.wrongLoginParams);
        if (!user.getPerson().getEmail().isEmpty())
            ValidateLoginParams.validateEmail(user.getPerson().getEmail());
        if (! user.getPerson().getPassword().isEmpty())
            ValidateLoginParams.validatePassword(user.getPerson().getPassword());
        userRepo.save(user);
    }

    @Override
    public List<User> allUsers() {
        return userRepo.findAll();
    }
}
