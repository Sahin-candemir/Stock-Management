package com.sahin.inventoryservice.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double amount;

    private Long productId;
}
