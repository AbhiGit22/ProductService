package com.example.product.services;

import com.example.product.dtos.ProductRequestDto;
import com.example.product.models.Product;

import java.util.List;


public interface IProductService {
    public Product getProductById(long id) throws InvalidProductException;

    public List<Product> getAllProducts();

    public Product addProduct(ProductRequestDto productRequestDto);

    public Product updateProduct(long id, ProductRequestDto productRequestDto);
}
