package com.mahdi.sesootservice.entity.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CategoryRequestDto(
        @NotNull
        @Pattern(regexp = "[A-Za-z0-9 ]*")
        String name) {
}
