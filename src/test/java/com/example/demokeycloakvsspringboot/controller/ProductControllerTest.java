package com.example.demokeycloakvsspringboot.controller;

import com.example.demokeycloakvsspringboot.jpa.Product;
import com.example.demokeycloakvsspringboot.jpa.ProductDTO;
import com.example.demokeycloakvsspringboot.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.http.ResponseEntity.status;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {
    @Mock
    private ProductService productService;
    @InjectMocks
    private ProductController productController;

    @Test
    void allProducts() {
        List<Product> products = new ArrayList<>();
        when(productService.getListProduct()).thenReturn(products);
        assertEquals(products, productController.allProducts());
    }

    @Test
    void oneProduct() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Product 1");
        product.setPrice(1);
        when(productService.getOneProduct("1")).thenReturn(product);
        //assertEquals(product, productController.oneProduct("1"));
    }

    @Test
    void productCreate() {

        Product product = new Product();
        product.setId(1L);
        product.setName("Product 1");
        product.setPrice(1);
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("Product 1");
        productDTO.setPrice("1");
        when(productService.createOneProduct(productDTO)).thenReturn(product);
        assertEquals(product, productController.productCreate(productDTO));

    }

    @Test
    void productEdit() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Product 1");
        product.setPrice(1);
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("Product 1");
        productDTO.setPrice("1");
        when(productService.updateOneProduct("1", productDTO)).thenReturn(product);
        assertEquals(product, productController.productEdit("1", productDTO));

    }

    @Test
    void productDelete() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Product 1");
        product.setPrice(1);
        when(productService.deleteOneProduct("1")).thenReturn(HttpStatus.NO_CONTENT);
        assertEquals(status(HttpStatus.NO_CONTENT).build(), productController.productDelete("1"));
    }
}