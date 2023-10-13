package com.mahdi.sesootservice.entity.base;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Blob;


@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Person extends BaseEntity<Long>{
    private String fullName;
    private String email;
    private String password;
    @Lob
    private Blob picture;
}
