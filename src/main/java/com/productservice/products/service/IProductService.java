package com.productservice.products.service;

import com.productservice.products.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IProductService {


   Product getSingleProduct(Long id);

   List<Product> getAllProducts();
}
