package com.mahdi.sesootservice.entity;

import com.mahdi.sesootservice.entity.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "category")
public class Category extends BaseEntity<Long> {
    private String name;
    @OneToMany(mappedBy = "category")
    private List<SubCategory> subCategory;
}
