package com.example.demokeycloakvsspringboot.repository;

import com.example.demokeycloakvsspringboot.jpa.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findAll();
}
