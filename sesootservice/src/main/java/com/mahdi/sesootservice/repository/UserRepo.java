package com.mahdi.sesootservice.repository;

import com.mahdi.sesootservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
}
