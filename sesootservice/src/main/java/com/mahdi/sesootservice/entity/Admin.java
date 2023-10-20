package com.mahdi.sesootservice.entity;

import com.mahdi.sesootservice.entity.base.BaseEntity;
import com.mahdi.sesootservice.entity.base.Person;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "admins")
public class Admin extends BaseEntity<Long> {
    @OneToOne(cascade = CascadeType.ALL)
    private Person person;
}
