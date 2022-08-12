package com.example.demokeycloakvsspringboot.service;


import lombok.Getter;
import java.util.Locale;
import java.util.ResourceBundle;

@Getter
public class ProductException extends RuntimeException{
    private final String keyMess;
    private final Locale locale;

    public ProductException(String keyMess) {
        this(keyMess, Locale.getDefault());
    }

    public ProductException(String keyMess, Locale locale) {
        this.keyMess = keyMess;
        this.locale = locale;
    }

    public static String getMessageForLocale(String keyMess, Locale locale) {
        ResourceBundle bundle = ResourceBundle.getBundle("MyMessage", locale);
        return bundle.getString(keyMess)+" 'for locale: "+locale.toString()+"'";
    }

    @Override
    public String getMessage() {
        return getMessageForLocale(keyMess, locale);
    }
}
