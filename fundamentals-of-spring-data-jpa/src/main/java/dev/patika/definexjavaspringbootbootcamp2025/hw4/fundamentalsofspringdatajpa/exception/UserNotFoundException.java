package dev.patika.definexjavaspringbootbootcamp2025.hw4.fundamentalsofspringdatajpa.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("UserNotFoundException");
    }
}
