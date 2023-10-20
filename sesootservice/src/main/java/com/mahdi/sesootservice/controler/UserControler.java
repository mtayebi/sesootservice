package com.mahdi.sesootservice.controler;

import com.mahdi.sesootservice.core.exception.InvalidEmailException;
import com.mahdi.sesootservice.core.exception.InvalidPasswordException;
import com.mahdi.sesootservice.core.exception.NoSuchUserException;
import com.mahdi.sesootservice.core.exception.PermissionDeniedException;
import com.mahdi.sesootservice.mapper.UserProfileMapper;
import com.mahdi.sesootservice.entity.DTO.UerSignUpDto;
import com.mahdi.sesootservice.entity.DTO.UserProfileDto;
import com.mahdi.sesootservice.entity.User;
import com.mahdi.sesootservice.entity.base.Person;
import com.mahdi.sesootservice.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserControler {
    private UserService userService;
    private final HttpServletRequest servletRequest;
    public UserControler(UserService userService, HttpServletRequest servletRequest){
        this.userService = userService;
        this.servletRequest = servletRequest;
    }
    @PostMapping("/signup")
    public UserProfileDto userSignup(@RequestBody UerSignUpDto userSignUpDto){
        Person person = Person.builder()
                .fullName(userSignUpDto.fullName())
                .password(userSignUpDto.password())
                .email(userSignUpDto.email())
                .picture(userSignUpDto.picture())
                .build();
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
    public UserProfileDto userProfile(){
        Person person = (Person) servletRequest.getSession().getAttribute("person");
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


}
