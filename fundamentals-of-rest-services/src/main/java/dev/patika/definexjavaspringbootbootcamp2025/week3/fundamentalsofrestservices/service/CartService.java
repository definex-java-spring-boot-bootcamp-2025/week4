package dev.patika.definexjavaspringbootbootcamp2025.week3.fundamentalsofrestservices.service;

import dev.patika.definexjavaspringbootbootcamp2025.week3.fundamentalsofrestservices.model.Cart;

import java.util.List;

public interface CartService {

    boolean create(Cart cart);
    boolean update(Cart cart);
    Cart get(String id);
    List<Cart> list();
    boolean delete(String id);
}
