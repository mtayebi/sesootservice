package com.mahdi.sesootservice.service;

import com.mahdi.sesootservice.core.exception.orders.OrderException;
import com.mahdi.sesootservice.core.exception.subcategory.SubCategoryException;
import com.mahdi.sesootservice.entity.Orders;

public interface OrdersService {
    void putOrder(Orders order) throws OrderException, SubCategoryException;

}
