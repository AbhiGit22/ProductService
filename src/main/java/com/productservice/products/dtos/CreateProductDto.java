package com.productservice.products.dtos;

import com.productservice.products.models.Category;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateProductDto {
    private String title;
    private float price;
    private String email;
    private String description;
    private String image;
}
