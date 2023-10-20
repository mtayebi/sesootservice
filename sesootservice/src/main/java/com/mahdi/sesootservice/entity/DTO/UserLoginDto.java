package com.mahdi.sesootservice.entity.DTO;

import jakarta.persistence.Lob;

import java.sql.Blob;


public record UserLoginDto(String email, String password) {

}
