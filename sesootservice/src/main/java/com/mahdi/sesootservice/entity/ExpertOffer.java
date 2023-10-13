package com.mahdi.sesootservice.entity;

import com.mahdi.sesootservice.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
//@AllArgsConstructor
@NoArgsConstructor

@Entity
public class ExpertOffer extends BaseEntity<Long> {
    private String price;
    private Date beginningTimeOffer;
    private int estimatedDurationHours;

    @ManyToMany
    @JoinTable(
            name = "expertoffer_userorder",
            joinColumns = @JoinColumn(
                    name ="expert_offer_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns =@JoinColumn(
                    name = "order_id",
                    referencedColumnName = "id"
            )
    )
    private List<Orders> ordersList;

    @ManyToOne(
            cascade = CascadeType.ALL
    )
    private Expert expert;
}
