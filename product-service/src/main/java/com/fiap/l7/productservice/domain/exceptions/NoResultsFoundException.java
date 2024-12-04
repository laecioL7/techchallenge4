package com.fiap.l7.productservice.domain.exceptions;

public class NoResultsFoundException extends RuntimeException {
    public NoResultsFoundException(String message) {
        super(message);
    }
}