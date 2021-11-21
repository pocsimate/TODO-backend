package hu.unideb.inf.todo.exception.user;

public class NoSuchUserException extends RuntimeException {
    public NoSuchUserException(String username){
            super("User " + username + " doesn't exists");
    }
}
