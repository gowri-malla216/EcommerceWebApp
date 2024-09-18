package com.example.EcommProduct.Service;

import com.example.EcommProduct.Dto.ProductDto;
import com.example.EcommProduct.Dto.ProductMapper;
import com.example.EcommProduct.Model.Category;
import com.example.EcommProduct.Model.CategoryRepo;
import com.example.EcommProduct.Model.Product;
import com.example.EcommProduct.Model.ProductRepo;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ProductMapper productMapper;

    private final String FAKE_STORE_API_URL = "https://fakestoreapi.com/products";
    @Autowired
    private CategoryRepo categoryRepo;

    public List<ProductDto> syncProducts(){
        RestTemplate restTemplate = new RestTemplate();
        ProductDto[] products = restTemplate.getForObject(FAKE_STORE_API_URL, ProductDto[].class);

        if(products != null){
            for(ProductDto product : products){
                String categoryName = product.getCategory();
                Category category = categoryRepo.findByName(categoryName);
                if(category == null){
                    category = new Category();
                    category.setName(categoryName);
                    categoryRepo.save(category);
                }
                Product productDao = productMapper.toProduct(product);
                productDao.setCategory(category);
                productRepo.save(productDao);
            }
            return Arrays.asList(products);
        }
        return null;
    }

    public List<ProductDto> getAllProducts(){
        return productRepo.findAll().stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    public ProductDto getProductById(Integer id){
        Product product = productRepo.findById(id).orElse(null);
        if(product == null){
            return null;
        }
        return productMapper.toDto(product);
    }

    public List<ProductDto> getProductsByCategory(String category){
        List<Product> products = productRepo.findByCategoryName(category);
        if(!products.isEmpty()){
            return products.stream().map(productMapper::toDto).collect(Collectors.toList());
        }
        return null;
    }

    public void createProduct(ProductDto productDto){
        Product product = productMapper.toProduct(productDto);
        productRepo.save(product);
    }
}
