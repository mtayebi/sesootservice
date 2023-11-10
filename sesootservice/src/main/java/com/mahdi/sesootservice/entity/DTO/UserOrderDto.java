package com.mahdi.sesootservice.entity.DTO;

public record UserOrderDto(
        String userOfferPrice,
        String description,
        String address,
        String subCategoryName
) {

}
