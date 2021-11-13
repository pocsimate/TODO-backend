package hu.unideb.inf.todo.controller;

import hu.unideb.inf.todo.model.Todo;
import hu.unideb.inf.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "/todos")
public class TodosController {

    @Autowired
    private TodoRepository todoRepository;

    @GetMapping(path = "")
    public @ResponseBody Iterable<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody Optional<Todo> getTodoById(@PathVariable long id) {
        return todoRepository.getTodoById(id);
    }

    @DeleteMapping(path = "/{id}")
    public @ResponseBody
    Optional<Todo> deleteTodoById(@PathVariable long id) {
        Optional<Todo> returnable = getTodoById(id);
        if (returnable != null) {
            todoRepository.deleteTodoById(id);
            return returnable;
        }
        return null;
    }

    @PutMapping("/{id}")
    public @ResponseBody Todo updateTodo(@RequestBody Todo newTodo, @PathVariable long id) {

        return todoRepository.getTodoById(id)
                .map(todo -> {
                    todo.setContent(newTodo.getContent());
                    return todoRepository.save(todo);
                })
                .orElseGet(() -> todoRepository.save(newTodo));
    }

    @PostMapping(path = "")
    public @ResponseBody Todo newTodo(@RequestBody Todo todo){
        return todoRepository.save(todo);
    }

}
