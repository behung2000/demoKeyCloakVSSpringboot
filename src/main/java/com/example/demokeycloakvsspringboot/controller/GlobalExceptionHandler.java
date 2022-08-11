package com.example.demokeycloakvsspringboot.controller;

import com.example.demokeycloakvsspringboot.jpa.ApiError;
import com.example.demokeycloakvsspringboot.service.ProductException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;

import java.util.Locale;
import java.util.ResourceBundle;

import static org.springframework.web.context.request.RequestAttributes.SCOPE_REQUEST;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({
            ProductException.class,
            Exception.class,
    })
    public ResponseEntity<ApiError> globalExceptionHandler(final Exception e,
                                                           final WebRequest request) {
        final String source = e.getClass().getSimpleName();
        log.error("Handling exception {} due to {}", source, e.getMessage());

        if(e instanceof AccessDeniedException) {

            final ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED.value(),
                    e.getMessage(), source);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED.value())
                    .body(apiError);

        }
        else {
            if (e instanceof ProductException) {
                String keyMess = ((ProductException) e).getKeyMess();
                int httpstatus = HttpStatus.BAD_REQUEST.value();
                if (keyMess.equals("ProductNotFound")) {
                    httpstatus = HttpStatus.NOT_FOUND.value();
                }
                final ApiError apiError = new ApiError(httpstatus,
                        e.getMessage(), source);
                return ResponseEntity.status(httpstatus).body(apiError);
            } else {
                log.warn("Unknown exception type: " + e.getClass().getName());
                final HttpHeaders headers = new HttpHeaders();
                final HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
                final ApiError apiError = new ApiError(
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        e.getMessage(), source);
                return handleExceptionInternal(e, apiError, headers, status,
                        request);
            }
        }
    }

    protected ResponseEntity<ApiError> handleExceptionInternal(
            final Exception e, final ApiError body, final HttpHeaders headers,
            final HttpStatus status, final WebRequest request) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, e,
                    SCOPE_REQUEST);
        }
        return new ResponseEntity<>(body, headers, status);
    }

}
