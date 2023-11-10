package com.mahdi.sesootservice.mapper;

import com.mahdi.sesootservice.entity.DTO.UserOrderDto;
import com.mahdi.sesootservice.entity.Orders;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserOrderDtoToOrder {
    UserOrderDtoToOrder INSTANCE = Mappers.getMapper(UserOrderDtoToOrder.class);
    Orders orderDtoToOrder(UserOrderDto orderDto);
}
