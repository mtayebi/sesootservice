package com.mahdi.sesootservice.entity.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.sql.Date;

public record ExpertOrderDto(
        @Positive
        String price,
        @NotNull
        Date beginningTimeOffer,
        @Positive
        int estimatedDurationHours,
        @Positive
        Long orderId
) {
    /*
     private String price;
    private Date beginningTimeOffer;
    private int estimatedDurationHours;
    Orders order;
    (Expert epert)
    * */
}
