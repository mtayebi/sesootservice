package com.mahdi.sesootservice.mapper;

import com.mahdi.sesootservice.entity.DTO.UserProfileDto;
import com.mahdi.sesootservice.entity.User;
import com.mahdi.sesootservice.entity.base.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserProfileMapper {
    UserProfileMapper INSTANCE = Mappers.getMapper(UserProfileMapper.class);

    @Mapping(source = "person.email", target = "email")
    @Mapping(source = "person.fullName", target = "fullName")
    @Mapping(source = "person.picture", target = "picture")
    UserProfileDto userToProfileDto(User user);

}
