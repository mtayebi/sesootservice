package com.mahdi.sesootservice.entity.DTO;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.sql.Blob;


public record UserLoginDto(
        @Email(message = "email can not be null")
        String email,
        @NotNull(message = "password can not be null")
        String password) {

}
