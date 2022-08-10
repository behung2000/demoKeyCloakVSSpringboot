package com.example.demokeycloakvsspringboot.jpa;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    Product product = new Product();

    ProductTest() {
        product.setId(1L);
        product.setName("Product 1");
        product.setPrice(100);
    }

    @Test
    void testEquals() {
        Product product2 = new Product();
        product2.setId(1L);
        product2.setName("Product 1");
        product2.setPrice(100);
        assertEquals(product, product2);
    }

    @Test
    void testHashCode() {
        Product product2 = new Product();
        product2.setId(1L);
        product2.setName("Product 1");
        product2.setPrice(100);
        assertEquals(product.hashCode(), product2.hashCode());
    }

    @Test
    void testToString() {
        assertEquals("Product { Id=1, Name='Product 1', Price=100}", product.toString());
    }

    @Test
    void getId() {
        assertEquals(1L, product.getId());
    }

    @Test
    void getName() {
        assertEquals("Product 1", product.getName());
    }

    @Test
    void getPrice() {
        assertEquals(100, product.getPrice());
    }

    @Test
    void setId() {
        product.setId(2L);
        assertEquals(2L, product.getId());
    }

    @Test
    void setName() {
        product.setName("Product 2");
        assertEquals("Product 2", product.getName());
    }

    @Test
    void setPrice() {
        product.setPrice(200);
        assertEquals(200, product.getPrice());
    }
}