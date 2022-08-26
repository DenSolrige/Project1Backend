package dev.moore.exceptions;

public class NoAccountFoundException extends RuntimeException{

    public NoAccountFoundException(String message){
        super(message);
    }
}
