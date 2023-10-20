package com.mahdi.sesootservice.repository;

import com.mahdi.sesootservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findUserByPerson_Email(String email);
}
