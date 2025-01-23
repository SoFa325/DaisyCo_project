package com.example.restservice_demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(ThereIsNoSuchProductException.class)
    protected ResponseEntity<UniversalException> handleThereIsNoSuchProductException() {
        return new ResponseEntity<>(new UniversalException("There is no such product"), HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(NameShouldBeShorterException.class)
    protected ResponseEntity<UniversalException> handleNameShouldBeShorterException() {
        return new ResponseEntity<>(new UniversalException("Product name must be no more than 255 characters"), HttpStatus.NOT_FOUND);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(DescriptionShouldBeShorterException.class)
    protected ResponseEntity<UniversalException> handleDescriptionShouldBeShorterException() {
        return new ResponseEntity<>(new UniversalException("Product description must be no more than 4096 characters"), HttpStatus.NOT_FOUND);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(PriceShouldBeMoreThanZero.class)
    protected ResponseEntity<UniversalException> handlePriceShouldBeMoreThanZero() {
        return new ResponseEntity<>(new UniversalException("Product price must be not less than 0.0"), HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(PriceShouldBeFloatingPointNumber.class)
    protected ResponseEntity<UniversalException> handlePriceShouldBeFloatingPointNumber() {
        return new ResponseEntity<>(new UniversalException("Product price must be floating-point number"), HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(WrongProperty.class)
    protected ResponseEntity<UniversalException> WrongProperty() {
        return new ResponseEntity<>(new UniversalException("Property is wrong"), HttpStatus.NOT_FOUND);
    }

    protected static class UniversalException {
        private String message;

        public UniversalException(String message){
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    public static class ThereIsNoSuchProductException extends RuntimeException {
    }
    public static class NameShouldBeShorterException extends RuntimeException {
    }
    public static class DescriptionShouldBeShorterException extends RuntimeException {
    }
    public static class PriceShouldBeMoreThanZero extends RuntimeException {
    }
    public static class PriceShouldBeFloatingPointNumber extends RuntimeException {}
    public static class WrongProperty extends RuntimeException {}
}