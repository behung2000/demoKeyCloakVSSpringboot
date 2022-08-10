package languagescode;

import org.junit.jupiter.api.Test;

import java.util.Locale;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

class MyMessage_en_USTest {
    ResourceBundle rb = ResourceBundle.getBundle("languagescode.MyMessage",
            new Locale("en", "US"));

    @Test
    void getString() {
        assertEquals("OK", rb.getString("OK"));
    }

    @Test
    void getStringHello() {
        assertEquals("Hello", rb.getString("hello"));
    }

    @Test
    void getStringHelloAdmin() {
        assertEquals("Hello Admin", rb.getString("helloAdmin"));
    }

    @Test
    void getStringHelloSeller() {
        assertEquals("Hello Seller", rb.getString("helloSeller"));
    }

    @Test
    void getStringProductNotFound() {
        assertEquals("Product not found", rb.getString("ProductNotFound"));
    }

    @Test
    void getStringProductIdError() {
        assertEquals("Id product has to be a number !!!",
                rb.getString("ProductIdError"));
    }

    @Test
    void getStringProductNameNull() {
        assertEquals("Name is required", rb.getString("ProductNameNull"));
    }


}