package com.mahdi.sesootservice.service.impl;

import com.mahdi.sesootservice.core.exception.DbConnectionException;
import com.mahdi.sesootservice.core.exception.user.*;
import com.mahdi.sesootservice.core.messages.DbConnection;
import com.mahdi.sesootservice.core.messages.Login;
import com.mahdi.sesootservice.core.messages.UserMessage;
import com.mahdi.sesootservice.core.validators.person.ValidateLoginParams;
import com.mahdi.sesootservice.entity.Enum.ExpertStatus;
import com.mahdi.sesootservice.entity.Enum.Role;
import com.mahdi.sesootservice.entity.Expert;
import com.mahdi.sesootservice.entity.ExpertOffer;
import com.mahdi.sesootservice.entity.base.Person;
import com.mahdi.sesootservice.repository.ExpertRepo;
import com.mahdi.sesootservice.service.ExpertOfferService;
import com.mahdi.sesootservice.service.ExpertService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExpertServiceImpl implements ExpertService {
    private final ExpertRepo expertRepo;
    private final ExpertOfferService expertOfferService;
    private final BCryptPasswordEncoder passwordEncoder;


    @Override
    public void signUp(Expert expert) throws InvalidPasswordException, InvalidEmailException, DbConnectionException {
        ValidateLoginParams.validateEmail(expert.getPerson().getEmail());
        ValidateLoginParams.validatePassword(expert.getPerson().getPassword());
        expert.getPerson().setPassword(passwordEncoder.encode(expert.getPerson().getPassword()));
        expert.getPerson().setRole(Role.ROLE_EXPERT);
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
        Optional<Expert> expert = expertRepo.findExpertByPerson_Email(person.getEmail());
        if (expert.isEmpty())
            throw new NoSuchUserException(Login.wrongLoginParams);
        return expert.orElseThrow(() -> new NoSuchUserException(Login.wrongLoginParams));
    }

    @Override
    public void putOffer(ExpertOffer expertOffer, Person person) throws PermissionDeniedException {
        Optional<Expert> expert = expertRepo.findExpertByPerson_Email(person.getEmail());
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
