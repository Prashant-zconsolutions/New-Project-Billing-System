package com.mcb.billing.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bills")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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
    private double billAmount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "meter_number")
    private User user;


}
