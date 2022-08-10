package com.example.demokeycloakvsspringboot.service;

import lombok.Getter;
import lombok.Setter;

import java.util.Locale;
import java.util.ResourceBundle;

@Getter
@Setter
public class ProductException extends RuntimeException {
    private final String keyMess;
    private final String locale = Locale.getDefault().toString();
    private static final  ResourceBundle rb = ResourceBundle.getBundle("languagescode.MyMessage",
            Locale.getDefault());

    public ProductException(String keyMess) {
        super(rb.getString(keyMess));
        this.keyMess = keyMess;
    }
}
