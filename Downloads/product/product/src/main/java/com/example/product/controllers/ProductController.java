package com.example.product.controllers;

import com.example.product.dtos.ErrorResponseDto;
import com.example.product.dtos.ProductRequestDto;
import com.example.product.dtos.ProductResponseDto;
import com.example.product.dtos.ProductWrapper;
import com.example.product.models.Product;
import com.example.product.services.FakeStoreProductService;
import com.example.product.services.IProductService;
import com.example.product.services.InvalidProductException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class ProductController {


    @Autowired
    private IProductService productService;

    //get all products
    @GetMapping("/products")
    public List<Product> getAllProducts(){

        return productService.getAllProducts();
    }



    //get a single product with given id
    @GetMapping("/products/{id}")
    public ResponseEntity<ProductWrapper> getProductWithId(@PathVariable("id") long id) throws InvalidProductException {
        //'https://fakestoreapi.com/products/1'
        ResponseEntity<ProductWrapper> response;
            Product product = productService.getProductById(id);
            ProductWrapper wrapper = new ProductWrapper(product, "Succesfully retrieved the data");
            response = new ResponseEntity<>(wrapper, HttpStatus.OK);

        return  response;
    }


    //Add product
    @PostMapping("/products")
    public Product addProduct(@RequestBody ProductRequestDto productRequestDto){

        return productService.addProduct(productRequestDto);
    }



    //updtae a product
    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable long id,
                                 @RequestBody ProductRequestDto productRequestDto){
        return productService.updateProduct(id, productRequestDto);
    }



    @DeleteMapping("/products/{id}")
    public Product deleteProduct(@PathVariable long id){

        return null;
    }
}
