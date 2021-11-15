package hu.unideb.inf.todo.controller;

import hu.unideb.inf.todo.exception.TodoNotFoundException;
import hu.unideb.inf.todo.model.Todo;
import hu.unideb.inf.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "/api/v1/todos")
public class TodosController {

    @Autowired
    private TodoRepository todoRepository;

    @GetMapping(path = "")
    public @ResponseBody Iterable<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable long id) {
        Todo todo = todoRepository.getTodoById(id).orElseThrow(() -> new TodoNotFoundException(id));
        return ResponseEntity.ok(todo);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Todo> deleteTodoById(@PathVariable long id) {
        Todo todo = todoRepository.getTodoById(id).orElseThrow(() -> new TodoNotFoundException(id));
        todoRepository.deleteTodoById(id);
        return ResponseEntity.ok(todo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@RequestBody Todo newTodo, @PathVariable long id) {
        Optional<Todo> todo = todoRepository.getTodoById(id);
        if(todo.isPresent()){
            todoRepository.updateTodo(newTodo.getContent(), id);
            todo.get().setContent(newTodo.getContent());
            return ResponseEntity.ok(todo.get());
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(todoRepository.save(newTodo));
        }
    }

    @PostMapping(path = "")
    public @ResponseBody Todo newTodo(@RequestBody Todo todo) {
        return todoRepository.save(todo);
    }

}
