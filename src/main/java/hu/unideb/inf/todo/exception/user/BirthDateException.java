package hu.unideb.inf.todo.exception.user;

public class BirthDateException extends RuntimeException {
    public BirthDateException(String date){
        super("Birth date " + date + " invalid");
    }

}
