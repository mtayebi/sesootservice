package com.mahdi.sesootservice.repository;

import com.mahdi.sesootservice.entity.Expert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExpertRepo extends JpaRepository<Expert, Long> {
    Optional<Expert> findExpertByPerson_Email(String email);
}
