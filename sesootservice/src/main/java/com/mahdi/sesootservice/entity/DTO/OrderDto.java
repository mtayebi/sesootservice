package com.mahdi.sesootservice.entity.DTO;

public record OrderDto(
        String userOfferPrice,
        String description,
        String address,
        String subCategoryName
) {

}
