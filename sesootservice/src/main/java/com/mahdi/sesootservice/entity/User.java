package com.mahdi.sesootservice.entity;

import com.mahdi.sesootservice.entity.base.BaseEntity;
import com.mahdi.sesootservice.entity.base.Person;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "users")
@DiscriminatorValue("user")
public class User extends BaseEntity<Long> {

    @OneToOne(cascade = CascadeType.ALL)
    @Column(nullable = false)
    private Person person;

    private String credit;
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL
    )
    private List<Orders> ordersList;
}
