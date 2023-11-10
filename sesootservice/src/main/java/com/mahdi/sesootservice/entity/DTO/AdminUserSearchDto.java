package com.mahdi.sesootservice.entity.DTO;

public record AdminUserSearchDto(
        String role,
        String partOfFullName,
        String partOfEmail,
        int rate
) {
}
