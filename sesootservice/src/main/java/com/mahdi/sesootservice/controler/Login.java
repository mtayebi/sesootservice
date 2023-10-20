package com.mahdi.sesootservice.controler;

import com.mahdi.sesootservice.core.exception.NoSuchUserException;
import com.mahdi.sesootservice.core.service.auth.impl.AuthServiceImpl;
import com.mahdi.sesootservice.entity.DTO.UserLoginDto;
import com.mahdi.sesootservice.entity.base.Person;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class Login {
    private final AuthServiceImpl loginService;
    private final HttpServletRequest httpServletRequest;
    public Login(AuthServiceImpl loginService, HttpServletRequest httpServletRequest){
        this.loginService = loginService;
        this.httpServletRequest = httpServletRequest;
    }

    @PostMapping
     UserLoginDto login(@RequestBody UserLoginDto userLoginDto){
        try {

            Person person = loginService.authenticatePerson(userLoginDto.email(), userLoginDto.password());
            HttpSession session = httpServletRequest.getSession();
            session.setAttribute("person", person);
        } catch (NoSuchUserException e) {
            throw new RuntimeException(e);
        }
        return userLoginDto;
    }

    @GetMapping
    Person loginDto(){
        Person person = (Person) httpServletRequest.getSession().getAttribute("person");
        return person;
    }
}
