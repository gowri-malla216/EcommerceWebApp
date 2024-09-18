package com.example.EcommProduct.Controller;

import com.example.EcommProduct.Dto.ProductDto;
import com.example.EcommProduct.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/createProduct")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        productService.createProduct(productDto);
        return ResponseEntity.ok().body(productDto);
    }

    @GetMapping("/syncProducts")
    public List<ProductDto> syncProducts() {
        List<ProductDto> products= productService.syncProducts();
        if(products==null){
            return null;
        }
        return products;
    }

    @GetMapping("/")
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable int id) {
        ProductDto productDto = productService.getProductById(id);
        if (productDto == null) {
            return ResponseEntity.notFound().build();
        }
        else{
            return ResponseEntity.ok(productDto);
        }
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<ProductDto>> getProductsByCategory(@PathVariable String category) {
        List<ProductDto> productDtos = productService.getProductsByCategory(category);
        if (productDtos == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productDtos);
    }

}
