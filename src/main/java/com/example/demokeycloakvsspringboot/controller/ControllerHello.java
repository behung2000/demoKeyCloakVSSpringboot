package com.example.demokeycloakvsspringboot.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;
import java.util.ResourceBundle;

@RequestMapping("/api/v1")
@RestController
public class ControllerHello {
    ResourceBundle rb;

    ControllerHello(){
        rb = ResourceBundle.getBundle("languagescode.MyMessage",
                Locale.getDefault());
    }

    @GetMapping("/helloAdmin")
    @PreAuthorize("hasRole('client-admin')")
    public String helloAdmin() {
        return rb.getString("helloAdmin");
    }

    @GetMapping("/helloSeller")
    @PreAuthorize("hasRole('client-seller')")
    public String helloSeller() {
        return rb.getString("helloSeller");
    }

    /*
     * Test Language
     */
    @GetMapping("/helloLanguage")
    @PreAuthorize("hasRole('client-admin') or hasRole('client-seller')")
    public String helloLanguage(@RequestHeader(name="Accept-Language",
            required=false) Locale locale) {
        if(locale== null) {
            locale = Locale.getDefault();
        }
        rb = ResourceBundle.getBundle("languagescode.MyMessage",
                locale);
        return rb.getString("hello");
    }
}
