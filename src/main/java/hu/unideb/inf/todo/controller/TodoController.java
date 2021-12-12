package hu.unideb.inf.todo.controller;

import javax.servlet.http.HttpServletRequest;

import hu.unideb.inf.todo.dto.todo.TodoDTO;
import hu.unideb.inf.todo.model.Todo;
import hu.unideb.inf.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/api/v1/todo")
@CrossOrigin
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping(path = "")
    public @ResponseBody Iterable<Todo> getAllTodo(HttpServletRequest req) {
        return todoService.getAllTodo(req);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Todo> getTodoById(HttpServletRequest req, @PathVariable long id) {
        Todo todo = todoService.getTodoById(req, id);
            return ResponseEntity.ok(todo);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Todo> deleteTodoById(HttpServletRequest req, @PathVariable long id) {
        Todo todo = todoService.deleteTodoById(req, id);
        return ResponseEntity.ok(todo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(HttpServletRequest req, @RequestBody TodoDTO todoDTO, @PathVariable long id) {
        Todo todo = todoService.updateTodo(req, todoDTO, id);
        return ResponseEntity.ok(todo);
    }

    @PostMapping(path = "")
    public ResponseEntity<Todo> newTodo(HttpServletRequest req, @RequestBody TodoDTO todoDTO) {
        Todo todo = todoService.newTodo(req, todoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(todo);
    }

}
