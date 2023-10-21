package com.mahdi.sesootservice.entity;

import com.mahdi.sesootservice.entity.Enum.OrderStatus;
import com.mahdi.sesootservice.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor

@Entity
@Table(name = "user_orders")
public class Orders extends BaseEntity<Long> {
    private String userOfferPrice;
    private Timestamp beginningTime;
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

    public Orders(String userOfferPrice,
                  String description,
                  String address,
                  User user,
                  SubCategory subCategory) {
        this.userOfferPrice = userOfferPrice;
        this.beginningTime = Timestamp.valueOf(LocalDateTime.now());
        this.description = description;
        this.address = address;
        this.orderStatus = OrderStatus.WAITING_FOR_EXPERTS;
        this.user = user;
        this.subCategory = subCategory;
    }
}
