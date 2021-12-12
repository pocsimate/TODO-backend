package hu.unideb.inf.todo.exception.user;

public class JWTInvalidException extends RuntimeException {
    public JWTInvalidException() {
        super("Token invalid");
    }
}
