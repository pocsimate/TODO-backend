package hu.unideb.inf.todo.exception;

public class TodoNotFoundException extends RuntimeException {

    public TodoNotFoundException(long id){
        super("Could not found TODO item with id " + id);
    }
}
