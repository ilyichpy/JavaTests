package edu.school21.exceptions;

public class IllegalNumberException extends RuntimeException {
    public IllegalNumberException(String s) {
        throw new RuntimeException(s);
    }

}
