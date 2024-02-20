package com.example.product.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDto {
    private long id;
    private String title;
    private double price;
    private String description;
    private String image;
    private String category;
}
