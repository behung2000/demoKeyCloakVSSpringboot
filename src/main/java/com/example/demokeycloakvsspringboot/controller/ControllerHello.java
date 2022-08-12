package com.example.demokeycloakvsspringboot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RequestMapping("/api/v1")
@RestController
public class ControllerHello {

    @Autowired
    private MessageSource messageSource;

    @Operation(summary = "Print hello admin with user has role admin",
            description = "Return string",
            security = {@SecurityRequirement(name = "bearer-key")},
            tags = {"Hello", "get"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @GetMapping("/helloAdmin")
    @PreAuthorize("hasRole('client-admin')")
    public String helloAdmin() {
        return messageSource.getMessage("hello.Admin", null, Locale.getDefault());
    }

    @Operation(summary = "Print hello seller with user has role seller",
            description = "Return string",
            security = {@SecurityRequirement(name = "bearer-key")},
            tags = {"Hello", "get"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @GetMapping("/helloSeller")
    @PreAuthorize("hasRole('client-seller')")
    public String helloSeller() {
        return messageSource.getMessage("hello.Seller", null, Locale.getDefault());
    }

    /*
     * Test Language
     */
    @Operation(summary = "Print hello with user has role admin",
            description = "Return string",
            security = {@SecurityRequirement(name = "bearer-key")},
            tags = {"Hello", "get"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @GetMapping("/helloLanguage")
    @PreAuthorize("hasRole('client-admin') or hasRole('client-seller')")
    public String helloLanguage(@RequestHeader(name="Accept-Language",
            required=false) Locale locale) {
        if(locale == null) {
            locale = LocaleContextHolder.getLocale();
        }
        return messageSource.getMessage("hello", null, locale);
    }
}
