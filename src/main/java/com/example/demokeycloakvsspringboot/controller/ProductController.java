package com.example.demokeycloakvsspringboot.controller;

import com.example.demokeycloakvsspringboot.jpa.Product;
import com.example.demokeycloakvsspringboot.jpa.ProductDTO;
import com.example.demokeycloakvsspringboot.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

import static org.springframework.http.ResponseEntity.status;


@RequestMapping("api/v1/products")
@RestController
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "Get all products",
            description = "Return list of products",
            security = {@SecurityRequirement(name = "bearer-key")},
            tags = {"Products", "get"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
    })
    @GetMapping("")
    @PreAuthorize("hasRole('View-product')")
    List<Product> allProducts() {
        return productService.getListProduct();
    }

    @Operation(summary = "Get product by id",
            description = "Return product",
            security = {@SecurityRequirement(name = "bearer-key")},
            tags = {"Products","get"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('View-product')")
    Product oneProduct(@PathVariable String id){
        return productService.getOneProduct(id);
    }

    @Operation(summary = "Create product",
            description = "Return product",
            security = {@SecurityRequirement(name = "bearer-key")},
            tags = {"Products", "post"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping("")
    @PreAuthorize("hasRole('Create-product')")
    Product productCreate(@RequestBody ProductDTO product){
        return productService.createOneProduct(product);
    }

    @Operation(summary = "Update product",
            description = "Return product",
            security = {@SecurityRequirement(name = "bearer-key")},
            tags = {"Products", "put"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PutMapping ("/{id}")
    @PreAuthorize("hasRole('Edit-product')")
    Product productEdit(@PathVariable String id,
                              @RequestBody ProductDTO product) {
        return productService.updateOneProduct(id, product);
    }

    @Operation(summary = "Delete product",
            description = "Return product",
            security = {@SecurityRequirement(name = "bearer-key")},
            tags = {"Products", "delete"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('Delete-product')")
    ResponseEntity<HttpStatus> productDelete(@PathVariable String id) {
        return status(productService.deleteOneProduct(id)).build();
    }
}
