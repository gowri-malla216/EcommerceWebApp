package com.example.EcommOrder.Service;

import com.example.EcommOrder.Client.CartClient;
import com.example.EcommOrder.Dto.CartResponse;
import com.example.EcommOrder.Dto.OrderRequest;
import com.example.EcommOrder.Dto.OrderResponse;
import com.example.EcommOrder.Dto.OrdersByUserId;
import com.example.EcommOrder.Model.Order;
import com.example.EcommOrder.Model.OrderLineItem;
import com.example.EcommOrder.Respository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.EcommOrder.Mapper.OrderMapper;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private CartClient cartClient;

    public OrderResponse placeOrder(String UserId) {
        CartResponse cartResponse = cartClient.getCart(UserId);
        if(cartResponse!=null) {
            Order order = orderMapper.toOrder(cartResponse);
            order.setOrderStatus("Order Placed");
            Order savedOrder = orderRepo.save(order);
            return orderMapper.toOrderResponse(savedOrder);
        }
        return null;
    }

    public OrderResponse getOrderById(int orderId) throws Exception{
        Order order = orderRepo.findById(orderId).orElseThrow(() -> new Exception("Order not found"));
        return orderMapper.toOrderResponse(order);
    }

    public OrdersByUserId ordersByUserId(String userId) throws Exception{
        List<Order> orders = orderRepo.findByUserId(userId);
        if(!orders.isEmpty()){
            OrdersByUserId ordersByUserId = new OrdersByUserId();
            List<OrderResponse> orderResponses = new ArrayList<>();
            for(Order order : orders){
                orderResponses.add(orderMapper.toOrderResponse(order));
            }
            ordersByUserId.setOrders(orderResponses);
            return ordersByUserId;
        }
        throw new Exception("Orders for Id "+ userId +" not found!");
    }

    public OrderResponse updateOrder(int id, String status) throws Exception{
        Order order = orderRepo.findById(id).orElseThrow(() -> new Exception("Order not found"));
        order.setOrderStatus(status);
        order = orderRepo.save(order);
        return orderMapper.toOrderResponse(order);
    }
}
