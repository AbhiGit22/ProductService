package com.productservice.products.service;

import com.productservice.products.exceptions.ProductNotPresentException;
import com.productservice.products.models.Product;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface IProductService {


   Product getSingleProduct(Long id) throws ProductNotPresentException;

   List<Product> getAllProducts();

   Product saveProduct(Product productDto);
}
