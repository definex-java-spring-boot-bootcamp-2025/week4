package dev.patika.definexjavaspringbootbootcamp2025.week3.fundamentalsofrestservices.service;

import dev.patika.definexjavaspringbootbootcamp2025.week3.fundamentalsofrestservices.model.Cart;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CartServiceImpl implements CartService {

    private Map<String, Cart> carts;

    public CartServiceImpl() {
        carts = new HashMap<>();
    }

    @Override
    public boolean create(Cart cart) {
        if (!carts.containsKey(cart.getId())) {
            carts.put(cart.getId(), cart);
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Cart cart) {
        Cart foundCart = carts.get(cart.getId());
        if (foundCart != null) {
            carts.put(cart.getId(), cart);
            return true;
        }
        return false;
    }

    @Override
    public Cart get(String id) {
        return carts.get(id);
    }

    @Override
    public List<Cart> list() {
        List<Cart> cartList = new ArrayList<>();
        for (Map.Entry<String, Cart> entry: carts.entrySet()){
            cartList.add(entry.getValue());
        }
        return cartList;
    }

    @Override
    public boolean delete(String id) {
        // carts.put(cart.getId(), null);
        Cart removedCart = carts.remove(id);
        if (removedCart != null) {
            return true;
        }
        return false;
    }
}
