package com.example.demokeycloakvsspringboot.service;

import com.example.demokeycloakvsspringboot.controller.ProductController;
import com.example.demokeycloakvsspringboot.jpa.Product;
import com.example.demokeycloakvsspringboot.jpa.ProductDTO;
import com.example.demokeycloakvsspringboot.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void getStringListProduct() {
        List<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setId(1L);
        product.setName("Product 1");
        product.setPrice(100);
        products.add(product);
        when(productRepository.findAll()).thenReturn(products);
        assertEquals(products, productService.getListProduct());
    }

    @Test
    void getOneProduct() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Product 1");
        product.setPrice(100);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        assertEquals(product, productService.getOneProduct("1"));
        when(productRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ProductException.class, () -> productService.getOneProduct("1"));
        assertThrows(ProductException.class, () -> productService.getOneProduct("a"));
    }

    @Test
    void checkNameVsPrice() {
        ProductDTO product = new ProductDTO();
        product.setName("Product 1");
        product.setPrice("100");
        assertEquals("OK",productService.checkNameVsPrice(product));

        //Check name is null
        product.setName(null);
        assertEquals("ProductNameNull",productService
                .checkNameVsPrice(product));
        product.setName("");
        assertEquals("ProductNameNull",productService
                .checkNameVsPrice(product));

        //Check price is null
        product.setName("Product 1");
        product.setPrice("");
        assertEquals("ProductPriceError",productService
                .checkNameVsPrice(product));
        product.setPrice("0");
        assertEquals("ProductPriceError",productService
                .checkNameVsPrice(product));
    }

    @Test
    void createOneProduct() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Product 1");
        product.setPrice(100);
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("Product 1");
        productDTO.setPrice("100");
        when(productRepository.save(new Product())).thenReturn(product);
        when(productRepository.save(product)).thenReturn(product);
        assertEquals(product,productService.createOneProduct(productDTO));
        productDTO.setName(null);
        assertThrows(ProductException.class, () -> productService.createOneProduct(productDTO));
    }

    @Test
    void updateOneProduct() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Product 1");
        product.setPrice(100);
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("Product 1");
        productDTO.setPrice("100");
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.save(product)).thenReturn(product);
        assertEquals(product, productService.updateOneProduct("1", productDTO));
        when(productRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ProductException.class, () -> productService.updateOneProduct("1", productDTO));
        assertThrows(ProductException.class, () -> productService.updateOneProduct("a", productDTO));
        productDTO.setName(null);
        assertThrows(ProductException.class, () -> productService.updateOneProduct("1", productDTO));
    }

    @Test
    void deleteOneProduct() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Product 1");
        product.setPrice(100);
        when(productRepository.existsById(1L)).thenReturn(true);
        assertEquals(HttpStatus.NO_CONTENT, productService.deleteOneProduct("1"));
        when(productRepository.existsById(1L)).thenReturn(false);
        assertThrows(ProductException.class, () -> productService.deleteOneProduct("1"));
        assertThrows(ProductException.class, () -> productService.deleteOneProduct("a"));
    }
}