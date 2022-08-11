package com.example.demokeycloakvsspringboot.service;

import org.junit.jupiter.api.Test;

import java.util.Locale;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

class ProductExceptionTest {
    @Test
    void testProductException() {
        ResourceBundle rb = ResourceBundle.getBundle("languagescode.MyMessage",
                Locale.getDefault());
        ProductException productException = new ProductException("OK");
        assertEquals(rb.getString("OK"), productException.getMessage());
    }

    /*
     * Get and set

    @Test
    void testGetKeyMess() {
        ProductException productException = new ProductException("OK");
        assertEquals("OK", productException.getMessage());
    }
    */
}