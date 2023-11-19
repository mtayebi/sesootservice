package com.mahdi.sesootservice.service.impl;

import com.mahdi.sesootservice.entity.base.Person;
import com.mahdi.sesootservice.repository.PersonRepo;
import com.mahdi.sesootservice.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
    private final PersonRepo personRepo;

    @Override
    public Optional<Person> findByEmail(String email) {
        return personRepo.findByEmail(email);
    }
}
