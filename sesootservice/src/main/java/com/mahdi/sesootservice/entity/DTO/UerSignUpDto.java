package com.mahdi.sesootservice.entity.DTO;

import java.sql.Blob;

public record UerSignUpDto(
        String email,
        String password,
        String fullName,
        Blob picture
        ) {
}
