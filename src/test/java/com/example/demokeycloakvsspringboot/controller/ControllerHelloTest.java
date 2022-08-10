package com.example.demokeycloakvsspringboot.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Locale;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ControllerHelloTest {
    @InjectMocks
    private ControllerHello controllerHello;

    private ResourceBundle rb;
    ControllerHelloTest() {
        rb = ResourceBundle.getBundle("languagescode.MyMessage",
                Locale.getDefault());
    }

    /*
     * Test hello admin
     */
    @Test
    void helloAdmin() {
        assertEquals(rb.getString("helloAdmin"), controllerHello.helloAdmin());
    }

    /*
     * Test hello seller
     */
    @Test
    void helloSeller() {
        assertEquals(rb.getString("helloSeller"), controllerHello.helloSeller());
    }

    /*
     * Test hello
     */
    @Test
    void hello() {
        assertEquals(rb.getString("hello"), controllerHello.helloLanguage(null));
        assertEquals(rb.getString("hello"), controllerHello.helloLanguage(Locale.getDefault()));
    }
}