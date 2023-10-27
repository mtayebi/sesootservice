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
import org.springframework.web.bind.annotation.*;

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
    public ExpertProfileDto signUp(@Valid @RequestBody SignUpDto ExpertSignupDto){
        Person person = Person.builder()
                .fullName(ExpertSignupDto.fullName())
                .password(ExpertSignupDto.password())
                .email(ExpertSignupDto.email())
                .picture(ExpertSignupDto.picture())
                .build();
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
    public ExpertProfileDto profile(){
        Person person = (Person) servletRequest.getSession().getAttribute("person");
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