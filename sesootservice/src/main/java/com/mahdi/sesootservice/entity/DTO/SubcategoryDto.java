package com.mahdi.sesootservice.entity.DTO;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record SubcategoryDto(

        @NotNull(message = "subcategory name can not be null")
        @Pattern(regexp = "^[a-zA-Z ]+$")
        String name,

        @NotNull(message = "description can not be null")
        String description,
        @NotNull(message = "base price can not be null")
        @Pattern(regexp = "^[0-9]+$")
        String basePrice,
        @NotNull(message = "category name can not be null")
        String categoryName) {
}
