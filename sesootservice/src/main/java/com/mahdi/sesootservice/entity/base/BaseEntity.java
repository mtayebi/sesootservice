package com.mahdi.sesootservice.entity.base;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Data

@MappedSuperclass
public class BaseEntity<ID extends Serializable> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private ID id;
    private final Timestamp creationTime;

    {
        creationTime = Timestamp.valueOf(LocalDateTime.now());
    }

    public BaseEntity(){}
    public BaseEntity(ID id){
        this.id = id;
    }

}
