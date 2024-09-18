package com.example.EcommOrder.Respository;

import com.example.EcommOrder.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepo extends JpaRepository<Order, Integer> {
    public Optional<Order> findById(int Id);

    public List<Order> findByUserId(String userId);
}
