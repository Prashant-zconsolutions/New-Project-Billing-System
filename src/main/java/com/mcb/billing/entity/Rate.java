package com.mcb.billing.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rates")
public class Rate {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rate_id")
    private int rateId;

    @Column(name = "user_type")
    private String userType;

    @Column(name = "user_price")
    private Double userPrice;

    @Column(name = "rate_min")
    private Integer rateMin;

    @Column(name = "rate_max")
    private Integer rateMax;


}
