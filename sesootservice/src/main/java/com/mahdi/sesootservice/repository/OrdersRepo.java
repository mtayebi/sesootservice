package com.mahdi.sesootservice.repository;

import com.mahdi.sesootservice.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrdersRepo extends JpaRepository<Orders, Long> {
    @Override
    Optional<Orders> findById(Long aLong);
}
