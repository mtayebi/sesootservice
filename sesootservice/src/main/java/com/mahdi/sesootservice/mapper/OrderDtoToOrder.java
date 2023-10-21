package com.mahdi.sesootservice.mapper;

import com.mahdi.sesootservice.entity.DTO.OrderDto;
import com.mahdi.sesootservice.entity.Orders;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderDtoToOrder {
    OrderDtoToOrder INSTANCE = Mappers.getMapper(OrderDtoToOrder.class);
    Orders orderDtoToOrder(OrderDto orderDto);
}
