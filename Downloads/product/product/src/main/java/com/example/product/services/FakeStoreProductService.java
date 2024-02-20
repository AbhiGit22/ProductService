package com.example.product.services;

import com.example.product.dtos.ProductRequestDto;
import com.example.product.dtos.ProductResponseDto;
import com.example.product.models.Category;
import com.example.product.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements IProductService{

    @Autowired
    RestTemplate restTemplate;

    public Product getProductFromResponseDtos(ProductResponseDto responseDto){
        Product product = new Product();
        product.setId(responseDto.getId());
        product.setName(responseDto.getTitle());
        product.setPrice(responseDto.getPrice());
        product.setDescription(responseDto.getDescription());
        product.setImage(responseDto.getImage());


        Category category = new Category();
        category.setName(responseDto.getCategory());
        product.setCategory(category);

        //This is alternative of above 3 lines

        /*product.setCategory(new Category());
        product.getCategory().setName(responseDto.getCategory());
*/
        return product;

    }

    @Override
    public Product getProductById(long id) throws InvalidProductException {
        if(id > 20){
            throw new InvalidProductException();
        }
        //pass id to fakestore Api and get the details of product with this id.
        ProductResponseDto response =
                restTemplate.getForObject("https://fakestoreapi.com/products/" + id, ProductResponseDto.class);

        return getProductFromResponseDtos(response);
    }

    @Override
    public List<Product> getAllProducts() {

        /*ResponseEntity<List<ProductResponseDto>> responseEntity;
        responseEntity = restTemplate.exchange(
                "https://fakestoreapi.com/products/",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ProductResponseDto>>() {}
        );

        List<Product> products = new ArrayList<>();
        for(ProductResponseDto productResponseDto : responseEntity.getBody()){
            products.add(getProductFromResponseDtos(productResponseDto));
        }
        return products;*/

        ProductResponseDto[] responseDtos =
                restTemplate.getForObject("https://fakestoreapi.com/products/", ProductResponseDto[].class);

        List<Product> output = new ArrayList<>();

        for(ProductResponseDto productResponseDto : responseDtos){
            output.add(getProductFromResponseDtos(productResponseDto));
        }
        return output;
    }

    @Override
    public Product addProduct(ProductRequestDto productRequestDto) {

        return null ;
    }

    @Override
    public Product updateProduct(long id, ProductRequestDto productRequestDto) {

        RequestCallback requestCallback = restTemplate.httpEntityCallback(productRequestDto, ProductResponseDto.class);
        HttpMessageConverterExtractor<ProductResponseDto> responseExtractor =
                new HttpMessageConverterExtractor<>(ProductResponseDto.class,
                        restTemplate.getMessageConverters());

        ProductResponseDto productResponseDto = restTemplate.execute("https://fakestoreapi.com/products/" +id,
                HttpMethod.PUT, requestCallback, responseExtractor);

        return getProductFromResponseDtos(productResponseDto);




        /*restTemplate.put("https://fakestoreapi.com/products/"+id,
                productRequestDto);                                         Try this if return type if void
        System.out.println("Succcesssfullly upppdaaateddd");
*/

    }
}
