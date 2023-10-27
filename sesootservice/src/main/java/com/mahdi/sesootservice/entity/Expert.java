package com.mahdi.sesootservice.entity;

import com.mahdi.sesootservice.entity.Enum.ExpertStatus;
import com.mahdi.sesootservice.entity.base.BaseEntity;
import com.mahdi.sesootservice.entity.base.Person;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "experts")
@DiscriminatorValue("expert")

public class Expert extends BaseEntity<Long> {
    private int expertRate;
    private String credit;
    @Enumerated(value = EnumType.STRING)
    private ExpertStatus expertStatus;

    @OneToMany(mappedBy = "expert")
    private List<ExpertOffer> expertOfferList;

    @OneToOne(cascade = CascadeType.ALL)
    private Person person;

}
