package com.mahdi.sesootservice.entity.DTO;

public record ErrorMessageDto(String message, org.springframework.http.HttpStatus badRequest){

}
