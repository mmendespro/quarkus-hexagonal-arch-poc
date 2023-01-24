package net.local.demo.hexagonal.application.exceptions;

public class NoFundsException extends RuntimeException {

    public NoFundsException(String message) {
        super(message);
    }
    
}
