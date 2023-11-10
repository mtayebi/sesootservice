package com.mahdi.sesootservice.mapper;

import com.mahdi.sesootservice.entity.DTO.SubcategoryDto;
import com.mahdi.sesootservice.entity.SubCategory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubcategoryDtoToSubcategory {
    SubcategoryDtoToSubcategory INSTANCE = Mappers.getMapper(SubcategoryDtoToSubcategory.class);

    SubCategory convert(SubcategoryDto subcategoryDto);
}
