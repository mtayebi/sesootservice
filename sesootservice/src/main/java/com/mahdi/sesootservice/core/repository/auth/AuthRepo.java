package com.mahdi.sesootservice.core.repository.auth;

import com.mahdi.sesootservice.entity.base.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepo extends JpaRepository<Person, Long> {
    Optional<Person> findPersonByEmailAndPassword(String email, String password);
}
