package com.example.EcommProduct.Dto;

import com.example.EcommProduct.Model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductDto toDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setTitle(product.getTitle());
        productDto.setImage(product.getImage());
        productDto.setRating(new Rating(product.getRating_rate(),product.getRating_count()));
        productDto.setCategory(product.getCategory().getName());
        return productDto;
    }
    public Product toProduct(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setTitle(productDto.getTitle());
        product.setImage(productDto.getImage());
        product.setRating_count(productDto.getRating().getCount());
        product.setRating_rate(productDto.getRating().getRate());
        return product;
    }
}
