package com.example.demokeycloakvsspringboot.jpa;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ApiErrorTest {
    private ApiError apiError;

    /*
     * Khởi tạo ApiError
     */
    ApiErrorTest(){
        apiError = new ApiError(400, "Bad Request",
                "http://localhost:8080/api/v1/products");
    }

    /*
     * Test getStatus()
     */
    @Test
    void getStatus() {
        assertEquals(true,apiError.getStatus() == 400);    // test get status
    }

    /*
     * Test getMessage()
     */
    @Test
    void getMessage() {
        assertEquals(true, apiError.getMessage().equals(
                "Bad Request"));    // test get message
    }

    /*
     * Test getSource()
     */
    @Test
    void getSource() {
        assertEquals(true, apiError.getSource().equals(
                "http://localhost:8080/api/v1/products"));   // test get source
    }

    /*
     * Test setStatus()
     */
    @Test
    void setStatus() {
        apiError.setStatus(500);
        assertEquals(true,apiError.getStatus() == 500);    // test set status
    }

    /*
     * Test setMessage()
     */
    @Test
    void setMessage() {
        apiError.setMessage("Internal Server Error");
        assertEquals(true, apiError.getMessage().equals(
                "Internal Server Error"));  // test set message
    }

    /*
     * Test setSource()
     */
    @Test
    void setSource() {
        apiError.setSource("http://localhost:8081/api/v1/products");
        assertEquals(true, apiError.getSource().equals(
                "http://localhost:8081/api/v1/products"));   // test set source
    }
}