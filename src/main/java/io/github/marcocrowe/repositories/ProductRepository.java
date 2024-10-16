package io.github.marcocrowe.repositories;

import io.github.marcocrowe.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
