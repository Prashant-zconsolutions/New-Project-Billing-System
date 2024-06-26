package com.mcb.billing.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bills")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bill_number")
    private int billNumber;

    @Column(name = "bill_date")
    private LocalDate billDate;

    @Column(name = "bill_unit")
    private Integer billUnit;

    @Column(name = "bill_amount")
    private Double billAmount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "meter_number")
    private User user;


}
