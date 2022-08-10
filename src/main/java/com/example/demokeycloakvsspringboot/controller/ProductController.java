package com.example.demokeycloakvsspringboot.controller;

import com.example.demokeycloakvsspringboot.jpa.Product;
import com.example.demokeycloakvsspringboot.jpa.ProductDTO;
import com.example.demokeycloakvsspringboot.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;


@RequestMapping("api/v1/products")
@RestController
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    @PreAuthorize("hasRole('View-product')")
    List<Product> allProducts() {
        return productService.getListProduct();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('View-product')")
    Product oneProduct(@PathVariable String id){
        return productService.getOneProduct(id);
    }

    @PostMapping("")
    @PreAuthorize("hasRole('Create-product')")
    Product productCreate(@RequestBody ProductDTO product){
        return productService.createOneProduct(product);
    }

    @PutMapping ("/{id}")
    @PreAuthorize("hasRole('Edit-product')")
    Product productEdit(@PathVariable String id,
                              @RequestBody ProductDTO product) {
        return productService.updateOneProduct(id, product);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('Delete-product')")
    ResponseEntity<HttpStatus> productDelete(@PathVariable String id) {
        return status(productService.deleteOneProduct(id)).build();
    }
}
