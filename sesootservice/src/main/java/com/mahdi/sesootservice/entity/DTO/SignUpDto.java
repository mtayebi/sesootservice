package com.mahdi.sesootservice.entity.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.sql.Blob;

public record SignUpDto(
        @Email(message = "enter valid email")
        String email,
        @NotNull
        String password,
        @NotNull
        String fullName
//        Blob picture
        ) {
}
