package com.TraineProject.CustomerService.exceptionHandler;

public class InvalidUserException extends RuntimeException {
    public InvalidUserException ( String message )
    {
        super(message);
    }
}
