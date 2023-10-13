package com.mahdi.sesootservice.entity;

import com.mahdi.sesootservice.entity.Enum.OrderStatus;
import com.mahdi.sesootservice.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "user_orders")
public class Orders extends BaseEntity<Long> {
    private String user_offer_price;
    private Date beginningTime;
    private String description;
    private String address;

    @Enumerated(value = EnumType.STRING)
    private OrderStatus orderStatus;
    private int userRate;
    private String userOpinion;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    private SubCategory subCategory;

    @ManyToMany
    @JoinTable(
            name = "expertoffer_userorder",
            joinColumns = @JoinColumn(
                    name = "order_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "expert_offer_id",
                    referencedColumnName = "id"
            )
    )
    private List<ExpertOffer> expertOffer;

}
