package hu.unideb.inf.todo.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class JWTInvalidExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(JWTInvalidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String JWTInvalidHandler(JWTInvalidException ex){
        return ex.getMessage();
    }
}
