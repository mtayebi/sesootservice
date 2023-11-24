package com.mahdi.sesootservice.controler;

import com.mahdi.sesootservice.core.exception.DbConnectionException;
import com.mahdi.sesootservice.core.exception.orders.OrderException;
import com.mahdi.sesootservice.core.exception.user.InvalidEmailException;
import com.mahdi.sesootservice.core.exception.user.InvalidPasswordException;
import com.mahdi.sesootservice.core.exception.user.NoSuchUserException;
import com.mahdi.sesootservice.core.exception.user.PermissionDeniedException;
import com.mahdi.sesootservice.entity.DTO.ExpertOrderDto;
import com.mahdi.sesootservice.entity.DTO.ExpertProfileDto;
import com.mahdi.sesootservice.entity.DTO.SignUpDto;
import com.mahdi.sesootservice.entity.Expert;
import com.mahdi.sesootservice.entity.ExpertOffer;
import com.mahdi.sesootservice.entity.Orders;
import com.mahdi.sesootservice.entity.base.Person;
import com.mahdi.sesootservice.mapper.ExpertOrderDtoToExpertOrder;
import com.mahdi.sesootservice.mapper.ExpertProfileMapper;
import com.mahdi.sesootservice.repository.ExpertRepo;
import com.mahdi.sesootservice.service.ExpertService;
import com.mahdi.sesootservice.service.OrdersService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.sql.SQLException;

@RestController
@RequestMapping("/expert")
public class ExpertControler {

    ExpertService expertService;
    OrdersService ordersService;
    HttpServletRequest servletRequest;
    public ExpertControler(ExpertService expertService,
                           HttpServletRequest servletRequest,
                           OrdersService ordersService){
        this.expertService = expertService;
        this.servletRequest = servletRequest;
        this.ordersService =  ordersService;
    }

    @PostMapping("/signup")
    public ExpertProfileDto signUp(@Valid SignUpDto ExpertSignupDto, @RequestPart MultipartFile picture){
        Person person = Person.builder()
                .fullName(ExpertSignupDto.fullName())
                .password(ExpertSignupDto.password())
                .email(ExpertSignupDto.email())
                .enabled(true)
                .build();

        try {
            byte[] p = picture.getBytes();
            person.setPicture(new SerialBlob(p));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SerialException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Expert expert = Expert.builder()
                .person(person)
                .build();

        try {
            expertService.signUp(expert);
        } catch (InvalidPasswordException e) {
            throw new RuntimeException(e);
        } catch (InvalidEmailException e) {
            throw new RuntimeException(e);
        } catch (DbConnectionException e) {
            throw new RuntimeException(e);
        }
        ExpertProfileDto expertProfileDto = ExpertProfileMapper.INSTANCE.expertToExpertDto(expert);
        return expertProfileDto;
    }

    @GetMapping("/profile")
    @PreAuthorize("hasRole('EXPERT')")
    public ExpertProfileDto profile(){
        Person person = (Person) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Expert expert;
        try {
            expert = expertService.profile(person);
        } catch (PermissionDeniedException e) {
            throw new RuntimeException(e);
        } catch (NoSuchUserException e) {
            throw new RuntimeException(e);
        }
        return ExpertProfileMapper.INSTANCE.expertToExpertDto(expert);
    }

    @PostMapping("/putorder")
    @PreAuthorize("hasRole('ROLE_EXPERT')")
    public void putOrder(@Valid @RequestBody ExpertOrderDto expertOrderDto){
        ExpertOffer expertOffer =
                ExpertOrderDtoToExpertOrder.Instance.orderDtoToOrder(expertOrderDto);
        Person person = (Person) servletRequest.getSession().getAttribute("person");
        Orders order;

        try {
            order = ordersService.findById(expertOrderDto.orderId());
        } catch (OrderException e) {
            throw new RuntimeException(e);
        }

        expertOffer.addOrder(order);

        try {
            expertService.putOffer(expertOffer, person);
        } catch (PermissionDeniedException e) {
            throw new RuntimeException(e);
        }

    }


}
