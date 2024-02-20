package com.example.product.dtos;

import com.example.product.models.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductWrapper {

    private Product product;
    private String messgae;
}
