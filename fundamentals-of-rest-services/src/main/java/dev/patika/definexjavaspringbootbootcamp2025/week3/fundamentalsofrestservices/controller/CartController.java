package dev.patika.definexjavaspringbootbootcamp2025.week3.fundamentalsofrestservices.controller;

import dev.patika.definexjavaspringbootbootcamp2025.week3.fundamentalsofrestservices.model.Cart;
import dev.patika.definexjavaspringbootbootcamp2025.week3.fundamentalsofrestservices.model.CartApiInfo;
import dev.patika.definexjavaspringbootbootcamp2025.week3.fundamentalsofrestservices.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    // /api/cart/v1, GET, CartController.info
    @GetMapping("/v1")
    public ResponseEntity<CartApiInfo> info() {
        CartApiInfo apiInfo = new CartApiInfo();
        apiInfo.setApiName("Cart API");
        apiInfo.setVersion("v1.0.0");
        return ResponseEntity.ok(apiInfo);
    }

    @PostMapping("/v1/create")
    public ResponseEntity<Cart> create(@RequestBody Cart newCart) {
        newCart.setId(UUID.randomUUID().toString());
        boolean result = this.cartService.create(newCart);
        if (result) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.internalServerError().build();
    }

    @PutMapping("/v1/update")
    public ResponseEntity<Cart> update(@RequestBody Cart updatedCart) {
        boolean result = this.cartService.update(updatedCart);
        if (result) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.internalServerError().build();
    }

    @GetMapping("/v1/get/{id}")
    public ResponseEntity<Cart> get(@PathVariable String id) {
        Cart foundCart = this.cartService.get(id);
        if (foundCart != null) {
            return ResponseEntity.ok(foundCart);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/v1/list")
    public ResponseEntity<List<Cart>> list() {
        return ResponseEntity.ok(this.cartService.list());
    }

    @DeleteMapping("/v1/delete/{id}")
    public ResponseEntity<Cart> delete(@PathVariable String id) {
        boolean result = this.cartService.delete(id);
        if (result) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.internalServerError().build();
    }
}
