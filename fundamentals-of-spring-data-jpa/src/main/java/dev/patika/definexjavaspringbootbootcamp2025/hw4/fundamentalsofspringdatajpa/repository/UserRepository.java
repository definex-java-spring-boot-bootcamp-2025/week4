package dev.patika.definexjavaspringbootbootcamp2025.hw4.fundamentalsofspringdatajpa.repository;

import dev.patika.definexjavaspringbootbootcamp2025.hw4.fundamentalsofspringdatajpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
}
