package com.mahdi.sesootservice.entity.DTO;


import java.sql.Blob;

public record UserProfileDto(
        String fullName,
        String email,
        Blob picture,
        String credit
    ) {

}
