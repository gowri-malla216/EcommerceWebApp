package com.example.EcommCart.Repository;

import com.example.EcommCart.Model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartsRepo extends JpaRepository<Cart, Integer> {
    public Optional<Cart> findById(int id);
    public Cart findByUserId(String userId);
}
