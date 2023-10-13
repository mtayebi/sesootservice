package com.mahdi.sesootservice.entity;

import com.mahdi.sesootservice.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "subcategory")
public class SubCategory extends BaseEntity<Long> {

    private String name;
    private String description;
    private String basePrice;

    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;

    @OneToMany(
            mappedBy = "subCategory",
            cascade = CascadeType.ALL
    )
    private List<Orders> ordersList;
}
