package com.mahdi.sesootservice.service.impl;

import com.mahdi.sesootservice.core.exception.user.*;
import com.mahdi.sesootservice.core.messages.Login;
import com.mahdi.sesootservice.core.messages.UserMessage;
import com.mahdi.sesootservice.core.validators.person.ValidateLoginParams;
import com.mahdi.sesootservice.entity.Enum.Role;
import com.mahdi.sesootservice.entity.Orders;
import com.mahdi.sesootservice.entity.User;
import com.mahdi.sesootservice.entity.base.Person;
import com.mahdi.sesootservice.repository.UserRepo;
import com.mahdi.sesootservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final BCryptPasswordEncoder passwordEncoder;


    @Override
    public void signUp(User user) throws InvalidEmailException, InvalidPasswordException {
        ValidateLoginParams.validateEmail(user.getPerson().getEmail());
        ValidateLoginParams.validatePassword(user.getPerson().getPassword());
        user.getPerson().setPassword(passwordEncoder.encode(user.getPerson().getPassword()));
        user.getPerson().setRole(Role.ROLE_USER);
        user.setPerson(user.getPerson());
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

    @Override
    public void putOrder(Orders order){

    }
}
