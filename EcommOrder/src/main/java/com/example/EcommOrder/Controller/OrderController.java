package com.example.EcommOrder.Controller;

import com.example.EcommOrder.Dto.OrderRequest;
import com.example.EcommOrder.Dto.OrderResponse;
import com.example.EcommOrder.Dto.OrdersByUserId;
import com.example.EcommOrder.Model.Order;
import com.example.EcommOrder.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/{userId}")
    public ResponseEntity<OrderResponse> placeOrder(@PathVariable String userId) {
        OrderResponse savedOrderResponse =orderService.placeOrder(userId);
        if(savedOrderResponse == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(savedOrderResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable int id) {
        try{
            OrderResponse orderResponse = orderService.getOrderById(id);
            return ResponseEntity.ok(orderResponse);
        }
        catch(Exception e) {
            return  ResponseEntity.notFound().eTag(e.getMessage()).build();//status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<OrdersByUserId> getOrderByUserId(@PathVariable String userId) {
        try{
            OrdersByUserId ordersByUserId = orderService.ordersByUserId(userId);
            return ResponseEntity.ok(ordersByUserId);
        }
        catch(Exception e) {
            return  ResponseEntity.notFound().eTag(e.getMessage()).build();
        }
    }

    @PutMapping("/{id}/{status}")
    public ResponseEntity<OrderResponse> updateOrderStatus(@PathVariable int id, @PathVariable String status) {
        try{
            OrderResponse orderResponse = orderService.updateOrder(id,status);
            return ResponseEntity.ok(orderResponse);
        }
        catch(Exception e) {
            return  ResponseEntity.notFound().eTag(e.getMessage()).build();//status(HttpStatus.NOT_FOUND).build();
        }
    }

}
