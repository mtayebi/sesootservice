package com.mahdi.sesootservice.repository;

import com.mahdi.sesootservice.entity.SubCategory;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubCategoryRepo extends JpaRepository<SubCategory, Long> {
    @Override
    Optional<SubCategory> findById(Long aLong);

    Optional<SubCategory> findByName(String name);

}
