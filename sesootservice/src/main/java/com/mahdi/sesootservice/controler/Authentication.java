package com.mahdi.sesootservice.controler;

import com.mahdi.sesootservice.core.exception.user.NoSuchUserException;
import com.mahdi.sesootservice.core.service.auth.impl.AuthServiceImpl;
import com.mahdi.sesootservice.entity.DTO.UserLoginDto;
import com.mahdi.sesootservice.entity.base.Person;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class Authentication {
    private final AuthServiceImpl loginService;
    private final HttpServletRequest httpServletRequest;
    public Authentication(AuthServiceImpl loginService, HttpServletRequest httpServletRequest){
        this.loginService = loginService;
        this.httpServletRequest = httpServletRequest;
    }

    @PostMapping("/login")
     UserLoginDto login(@Valid @RequestBody UserLoginDto userLoginDto){
        try {

            Person person = loginService.authenticatePerson(userLoginDto.email(), userLoginDto.password());
            HttpSession session = httpServletRequest.getSession();
            session.setAttribute("person", person);
        } catch (NoSuchUserException e) {
            throw new RuntimeException(e);
        }
        return userLoginDto;
    }

    @GetMapping("/login")
    Person loginDto(){
        Person person = (Person) httpServletRequest.getSession().getAttribute("person");
        return person;
    }

    @GetMapping("/logout")
    ResponseEntity<String> logout(){
        httpServletRequest.removeAttribute("person");
        return ResponseEntity.ok("you logged out successfully");
    }
}
