package hu.unideb.inf.todo.controller;

import hu.unideb.inf.todo.dto.todo.TodoDTO;
import hu.unideb.inf.todo.model.Todo;
import hu.unideb.inf.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/api/v1/todo")
@CrossOrigin
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping(path = "")
    public @ResponseBody Iterable<Todo> getAllTodo() {
        return todoService.getAllTodo();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable long id) {
        Todo todo = todoService.getTodoById(id);
            return ResponseEntity.ok(todo);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Todo> deleteTodoById(@PathVariable long id) {
        Todo todo = todoService.deleteTodoById(id);
        return ResponseEntity.ok(todo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@RequestBody TodoDTO todoDTO, @PathVariable long id) {
        Todo todo = todoService.updateTodo(todoDTO, id);
        return ResponseEntity.ok(todo);
    }

    @PostMapping(path = "")
    public ResponseEntity<Todo> newTodo(@RequestBody TodoDTO todoDTO) {
        Todo todo = todoService.newTodo(todoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(todo);
    }

}
