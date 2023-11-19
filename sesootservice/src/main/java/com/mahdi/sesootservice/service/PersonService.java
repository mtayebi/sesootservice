package com.mahdi.sesootservice.service;

import com.mahdi.sesootservice.entity.base.Person;

import java.util.Optional;

public interface PersonService {
    Optional<Person> findByEmail(String email);
}
