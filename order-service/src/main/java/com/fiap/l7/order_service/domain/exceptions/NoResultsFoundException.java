package com.fiap.l7.order_service.domain.exceptions;

public class NoResultsFoundException extends RuntimeException {
    public NoResultsFoundException(String message) {
        super(message);
    }
}