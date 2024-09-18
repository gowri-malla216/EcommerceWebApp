package com.example.EcommCart.Controller;

import com.example.EcommCart.Dtos.CartRequest;
import com.example.EcommCart.Dtos.CartResponse;
import com.example.EcommCart.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
public class CartController {
    @Autowired
    CartService cartService;

    @PostMapping("/")
    public ResponseEntity<CartResponse> addCart(@RequestBody CartRequest cartRequest) {
        return ResponseEntity.ok(cartService.addCart(cartRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartResponse> getCart(@PathVariable int id) {
        try{
            return ResponseEntity.ok(cartService.findCartById(id));
        }
        catch(Exception e){
            return ResponseEntity.notFound().eTag(e.getMessage()).build();
        }
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public CartResponse getCart(@RequestParam String userId) {
        return cartService.findCartByUserId(userId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartResponse> updateCart(@PathVariable int id, @RequestBody CartRequest cartRequest) {
        try {
            return ResponseEntity.ok(cartService.updateCart(cartRequest,id));
        }
        catch(Exception e){
            return ResponseEntity.notFound().eTag(e.getMessage()).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CartResponse> deleteCart(@PathVariable int id) {
        try {
            return ResponseEntity.ok(cartService.deleteCart(id));
        }
        catch(Exception e){
            return ResponseEntity.notFound().eTag(e.getMessage()).build();
        }
    }
}
