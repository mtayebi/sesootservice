package com.mahdi.sesootservice.mapper;

import com.mahdi.sesootservice.entity.DTO.ExpertProfileDto;
import com.mahdi.sesootservice.entity.Expert;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ExpertProfileMapper {
    ExpertProfileMapper INSTANCE = Mappers.getMapper(ExpertProfileMapper.class);

    @Mapping(source = "person.email", target = "email")
    @Mapping(source = "person.fullName", target = "fullName")
    @Mapping(source = "person.picture", target = "picture")
    ExpertProfileDto expertToExpertDto(Expert expert);
}
