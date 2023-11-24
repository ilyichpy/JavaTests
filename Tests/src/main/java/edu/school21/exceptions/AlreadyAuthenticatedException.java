package edu.school21.exceptions;

public class AlreadyAuthenticatedException extends RuntimeException {
    public AlreadyAuthenticatedException(String s) {
        throw new RuntimeException(s);
    }
}
