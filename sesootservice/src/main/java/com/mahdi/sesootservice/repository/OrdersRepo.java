package com.mahdi.sesootservice.repository;

import com.mahdi.sesootservice.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepo extends JpaRepository<Orders, Long> {
}
