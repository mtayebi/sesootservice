package com.mahdi.sesootservice.mapper;

import com.mahdi.sesootservice.entity.DTO.ExpertOrderDto;
import com.mahdi.sesootservice.entity.ExpertOffer;
import com.mahdi.sesootservice.entity.Orders;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ExpertOrderDtoToExpertOrder {
    ExpertOrderDtoToExpertOrder Instance = Mappers.getMapper(ExpertOrderDtoToExpertOrder.class);

    ExpertOffer orderDtoToOrder(ExpertOrderDto expertOrderDto);
}
