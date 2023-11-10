package com.mahdi.sesootservice.mapper;

import com.mahdi.sesootservice.entity.Category;
import com.mahdi.sesootservice.entity.DTO.CategoryRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryDtoToCategory {
    CategoryDtoToCategory INSTANCE = Mappers.getMapper(CategoryDtoToCategory.class);

    Category categoryDtoToCategory(CategoryRequestDto categoryRequestDto);
}
