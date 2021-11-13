package hu.unideb.inf.todo.controller;

import hu.unideb.inf.todo.model.Todo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {

    @GetMapping("/todo")
    public Todo todo(){
        return new Todo(1,2, "Learn Spring");
    }
}
