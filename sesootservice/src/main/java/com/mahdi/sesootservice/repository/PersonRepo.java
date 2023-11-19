package com.mahdi.sesootservice.repository;

import com.mahdi.sesootservice.entity.base.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PersonRepo extends CrudRepository<Person, Long>{
        Optional<Person> findByEmail(String email);
}
