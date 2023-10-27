package com.mahdi.sesootservice.service.impl;

import com.mahdi.sesootservice.core.exception.DbConnectionException;
import com.mahdi.sesootservice.core.exception.user.*;
import com.mahdi.sesootservice.core.messages.DbConnection;
import com.mahdi.sesootservice.core.messages.Login;
import com.mahdi.sesootservice.core.messages.UserMessage;
import com.mahdi.sesootservice.core.service.auth.AuthService;
import com.mahdi.sesootservice.core.validators.person.ValidateLoginParams;
import com.mahdi.sesootservice.entity.Enum.ExpertStatus;
import com.mahdi.sesootservice.entity.Expert;
import com.mahdi.sesootservice.entity.ExpertOffer;
import com.mahdi.sesootservice.entity.base.Person;
import com.mahdi.sesootservice.repository.ExpertOfferRepo;
import com.mahdi.sesootservice.repository.ExpertRepo;
import com.mahdi.sesootservice.repository.OrdersRepo;
import com.mahdi.sesootservice.service.ExpertOfferService;
import com.mahdi.sesootservice.service.ExpertService;
import com.mahdi.sesootservice.service.OrdersService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpertServiceImpl implements ExpertService {
    ExpertRepo expertRepo;
    AuthService authService;
    ExpertOfferService expertOfferService;
    OrdersService ordersService;

    public ExpertServiceImpl(ExpertRepo expertRepo,
                             AuthService authService,
                             ExpertOfferService expertOfferService,
                             OrdersService ordersService
                             ) {
        this.expertRepo = expertRepo;
        this.authService = authService;
        this.expertOfferService = expertOfferService;
        this.ordersService = ordersService;
    }

    @Override
    public void signUp(Expert expert) throws InvalidPasswordException, InvalidEmailException, DbConnectionException {
        ValidateLoginParams.validateEmail(expert.getPerson().getEmail());
        ValidateLoginParams.validatePassword(expert.getPerson().getPassword());
        try{
            expert.setExpertStatus(ExpertStatus.WAITING_FOR_QUALIFICATION);
            expertRepo.save(expert);
        }catch (DataIntegrityViolationException e){
            throw new UserAlreadyExistsException(UserMessage.userExists);
        }catch (Exception e){
            throw new DbConnectionException(DbConnection.dbConnectionUnknownError);
        }
    }

    @Override
    public Expert profile(Person person) throws PermissionDeniedException, NoSuchUserException {
        authService.personIsAuthenticated(person);
        Optional<Expert> expert = expertRepo.findExpertByPerson_Email(person.getEmail());
        if (expert.isEmpty())
            throw new NoSuchUserException(Login.wrongLoginParams);
        return expert.orElseThrow(() -> new NoSuchUserException(Login.wrongLoginParams));
    }

    @Override
    public void putOffer(ExpertOffer expertOffer, Person person) throws PermissionDeniedException {
        authService.personIsAuthenticated(person);
        Optional<Expert> expert = expertRepo.findExpertByPerson_Email(person.getEmail());
        if (expert.isEmpty())
            authService.personIsAuthenticated(null);
        expertOffer.setExpert(expert.get());
        expertOfferService.save(expertOffer);
    }

    @Override
    public void updateUser(Expert expert) {

    }

    @Override
    public List<Expert> allUsers() {
        return null;
    }
}
