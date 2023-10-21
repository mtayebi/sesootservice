package com.mahdi.sesootservice.service.impl;

import com.mahdi.sesootservice.core.exception.orders.OrderException;
import com.mahdi.sesootservice.core.exception.subcategory.SubCategoryException;
import com.mahdi.sesootservice.core.messages.Subcategory;
import com.mahdi.sesootservice.core.validators.orders.Validateorders;
import com.mahdi.sesootservice.entity.Orders;
import com.mahdi.sesootservice.repository.OrdersRepo;
import com.mahdi.sesootservice.repository.SubCategoryRepo;
import com.mahdi.sesootservice.service.OrdersService;
import org.springframework.stereotype.Service;

@Service
public class OrdersServiceImpl implements OrdersService {

    OrdersRepo ordersRepo;
    SubCategoryRepo subCategoryRepo;


    public OrdersServiceImpl(OrdersRepo ordersRepo, SubCategoryRepo subCategoryRepo) {
        this.ordersRepo = ordersRepo;
        this.subCategoryRepo = subCategoryRepo;
    }

    @Override
    public void putOrder(Orders order) throws OrderException, SubCategoryException {


        Validateorders.validateOfferPrice(order.getUserOfferPrice());
        if (subCategoryRepo.findById(order.getSubCategory().getId()) == null){
            throw new SubCategoryException(Subcategory.doesNotExist);
        }
        if (order.getAddress() == null){
            throw new OrderException(com.mahdi.sesootservice.core.messages.Orders.priceOrAddresIsNull);
        }

        ordersRepo.save(order);

    }
}
