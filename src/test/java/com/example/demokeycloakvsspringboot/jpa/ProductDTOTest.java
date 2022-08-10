package com.example.demokeycloakvsspringboot.jpa;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductDTOTest {
    ProductDTO productDTO = new ProductDTO();

    ProductDTOTest() {
        productDTO.setName("name");
        productDTO.setPrice("price");
    }

    @Test
    void constructor() {
        productDTO = new ProductDTO("name", "price");
        assertEquals("name", productDTO.getName());
        assertEquals("price", productDTO.getPrice());
    }


    @Test
    void getName() {
        assertEquals("name", productDTO.getName());
    }

    @Test
    void setName() {
        productDTO.setName("new name");
        assertEquals("new name", productDTO.getName());
    }

    @Test
    void getPrice() {
        assertEquals("price", productDTO.getPrice());
    }

    @Test
    void setPrice() {
        productDTO.setPrice("new price");
        assertEquals("new price", productDTO.getPrice());
    }
}