package com.mahdi.sesootservice.entity.DTO;

import com.mahdi.sesootservice.entity.Enum.ExpertStatus;
import com.mahdi.sesootservice.entity.ExpertOffer;


import java.sql.Blob;
import java.util.List;

public record ExpertProfileDto(
        String fullName,

        String email,
        Blob picture,
        List<ExpertOffer> expertOfferList,
        ExpertStatus expertStatus,
        String credit,
        int expertRate) {
}
