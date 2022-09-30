package com.infobip.bipcars.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String name;

    @NotNull
    @Min(0)
    private Double price;
}
