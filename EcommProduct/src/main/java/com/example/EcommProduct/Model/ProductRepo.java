package com.example.EcommProduct.Model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Integer> {

    List<Product> findByCategoryName(String categoryName);
}
