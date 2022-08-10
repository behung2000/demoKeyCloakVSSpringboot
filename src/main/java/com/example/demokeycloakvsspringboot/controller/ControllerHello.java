package com.example.demokeycloakvsspringboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/helloAdmin")
    @PreAuthorize("hasRole('client-admin')")
    public String helloAdmin() {
        return messageSource.getMessage("hello.admin", null, Locale.getDefault());
    }

    @GetMapping("/helloSeller")
    @PreAuthorize("hasRole('client-seller')")
    public String helloSeller() {
        return messageSource.getMessage("hello.seller", null, Locale.getDefault());
    }

    /*
     * Test Language
     */
    @GetMapping("/helloLanguage")
    @PreAuthorize("hasRole('client-admin') or hasRole('client-seller')")
    public String helloLanguage(@RequestHeader(name="Accept-Language",
            required=false) Locale locale) {
        if(locale == null) {
            locale = Locale.getDefault();
        }
        return messageSource.getMessage("hello", null, locale);
    }
}
