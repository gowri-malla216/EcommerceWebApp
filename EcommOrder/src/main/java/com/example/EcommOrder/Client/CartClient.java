package com.example.EcommOrder.Client;

import com.example.EcommOrder.Dto.CartResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "EcommCart", path = "/api/carts")
public interface CartClient {
    @RequestMapping(method = RequestMethod.GET, value = "/api/carts")
    CartResponse getCart(@RequestParam String userId);
}
