package hu.unideb.inf.todo.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class BirthDateExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(BirthDateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String BirthDateInvalid(BirthDateException ex){
        return ex.getMessage();
    }
}
