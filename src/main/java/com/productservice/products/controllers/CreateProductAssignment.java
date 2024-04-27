package com.productservice.products.controllers;

import com.productservice.products.dtos.CreateProductDto;
import com.productservice.products.models.Category;
import com.productservice.products.models.Product;
import com.productservice.products.service.IProductService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@RestController
public class CreateProductAssignment {

    @Autowired
    private RestTemplate restTemplate;

    private IProductService productService;

    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductDto productDto) {

        Product product = new Product();
        product.setName(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImage(productDto.getImage());

        CreateProductDto emailDto = new CreateProductDto();
        emailDto.setEmail(productDto.getEmail());



        Optional<User> user = restTemplate.postForObject("http://localhost:8082/email", emailDto, Optional.class);

        if (user.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
          Product entity = productService.saveProduct(product);
            return new ResponseEntity<>(entity, HttpStatus.CREATED);
        }
    }

}
