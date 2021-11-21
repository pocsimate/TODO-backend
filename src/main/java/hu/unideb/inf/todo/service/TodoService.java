package hu.unideb.inf.todo.service;

import hu.unideb.inf.todo.dto.todo.TodoDTO;
import hu.unideb.inf.todo.exception.todo.TodoNotFoundException;
import hu.unideb.inf.todo.model.Todo;
import hu.unideb.inf.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public Iterable<Todo> getAllTodo() {
        return todoRepository.findAll();
    }

    @Transactional
    public Todo getTodoById(long id) {
        Optional<Todo> todo = todoRepository.getTodoById(id);
        if (todo.isPresent()) {
            return todo.get();
        } else {
            throw new TodoNotFoundException(id);
        }
    }

    @Transactional
    public Todo newTodo(TodoDTO todoDTO) {
        Todo todo = new Todo();
        todo.setContent(todoDTO.getContent());
        return todoRepository.save(todo);
    }

    @Transactional
    @Modifying
    public Todo deleteTodoById(long id) {
        Todo todo = todoRepository.getTodoById(id).orElseThrow(() -> new TodoNotFoundException(id));
        todoRepository.deleteTodoById(id);
        return todo;
    }

    @Transactional
    public Todo updateTodo(TodoDTO todoDTO, long id) {
        try {
            Todo todo = getTodoById(id);
            todoRepository.updateTodo(todoDTO.getContent(), id);
            todo.setContent(todoDTO.getContent());
            return todo;
        } catch (TodoNotFoundException ex) {
            Todo newTodo = new Todo();
            newTodo.setContent(todoDTO.getContent());
            return todoRepository.save(newTodo);
        }
    }

}
