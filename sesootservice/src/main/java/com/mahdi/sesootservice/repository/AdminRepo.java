package com.mahdi.sesootservice.repository;

import com.mahdi.sesootservice.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepo extends JpaRepository<Admin, Long> {

    Optional<Admin> findAdminByPerson_Email(String email);
}
