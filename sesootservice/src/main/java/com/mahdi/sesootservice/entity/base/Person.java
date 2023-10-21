package com.mahdi.sesootservice.entity.base;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
public class Person extends BaseEntity<Long>{
    private String fullName;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String password;

    @Lob
    private Blob picture;
}
