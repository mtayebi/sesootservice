package com.mahdi.sesootservice.repository;

import com.mahdi.sesootservice.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepo extends JpaRepository<Admin, Long> {
}
