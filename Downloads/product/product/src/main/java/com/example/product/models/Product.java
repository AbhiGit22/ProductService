package com.example.product.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Product {
    private long id;
    private String name;
    private String description;
    private double price;
    private String image;
    private Category category;
}
