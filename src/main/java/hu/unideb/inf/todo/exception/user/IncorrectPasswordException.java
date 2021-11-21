package hu.unideb.inf.todo.exception.user;

public class IncorrectPasswordException extends RuntimeException {
    public IncorrectPasswordException() {
        super("The entered password is incorrect");
    }
}
