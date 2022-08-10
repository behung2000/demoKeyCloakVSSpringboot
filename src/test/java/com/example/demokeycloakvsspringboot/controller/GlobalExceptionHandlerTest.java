package com.example.demokeycloakvsspringboot.controller;

import com.example.demokeycloakvsspringboot.jpa.ApiError;
import com.example.demokeycloakvsspringboot.service.ProductException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.context.request.FacesWebRequest;
import org.springframework.web.context.request.WebRequest;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {

    @Test
    void globalExceptionHandler() {
        final GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();
        Exception e = new ProductException("ProductNotFound");
        assertNotNull(globalExceptionHandler.globalExceptionHandler(e, null));
        e = new ProductException("ProductIdError");
        assertNotNull(globalExceptionHandler.globalExceptionHandler(e, null));
    }
}