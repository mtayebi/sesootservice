package com.mahdi.sesootservice.repository;

import com.mahdi.sesootservice.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Long> {
}
