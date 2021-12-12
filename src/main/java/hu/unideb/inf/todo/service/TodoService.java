package hu.unideb.inf.todo.service;

import hu.unideb.inf.todo.dto.todo.TodoDTO;
import hu.unideb.inf.todo.exception.todo.TodoNotFoundException;
import hu.unideb.inf.todo.exception.user.JWTInvalidException;
import hu.unideb.inf.todo.exception.user.NoSuchUserException;
import hu.unideb.inf.todo.model.Todo;
import hu.unideb.inf.todo.model.UserModel;
import hu.unideb.inf.todo.repository.TodoRepository;
import hu.unideb.inf.todo.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserService userService;

    public Iterable<Todo> getAllTodo(HttpServletRequest req) {
        Optional<UserModel> user = userService.getUserByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
        if (user.isEmpty()) {
            throw new JWTInvalidException();
        }
        return todoRepository.findTodosByUserId(user.get().getId());
    }

    @Transactional
    public Todo getTodoById(HttpServletRequest req, long id) {
        Optional<UserModel> user = userService.getUserByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
        if (user.isEmpty()) {
            throw new JWTInvalidException();
        }
        Optional<Todo> todo = todoRepository.findTodoByIdAndUserId(id, user.get().getId());
        if (todo.isPresent()) {
            return todo.get();
        } else {
            throw new TodoNotFoundException(id);
        }
    }

    @Transactional
    public Todo newTodo(HttpServletRequest req, TodoDTO todoDTO) {
        Todo todo = new Todo();

        Optional<UserModel> user = userService.getUserByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
        if (user.isEmpty()) {
            throw new JWTInvalidException();
        }
        todo.setContent(todoDTO.getContent());
        todo.setUser(user.get());
        return todoRepository.save(todo);
    }

    @Transactional
    @Modifying
    public Todo deleteTodoById(HttpServletRequest req, long id) {
        Optional<UserModel> user = userService.getUserByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
        if (user.isEmpty()) {
            throw new JWTInvalidException();
        }
        Optional<Todo> optionalTodo = todoRepository.findTodoByIdAndUserId(id, user.get().getId());
        if (optionalTodo.isEmpty()) {
            throw new TodoNotFoundException(id);
        }
        todoRepository.deleteTodoById(id);
        return optionalTodo.get();
    }

    @Transactional
    public Todo updateTodo(HttpServletRequest req, TodoDTO todoDTO, long id) {
        Optional<UserModel> user = userService.getUserByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
        if (user.isEmpty()) {
            throw new JWTInvalidException();
        }
        Optional<Todo> optionalTodo = todoRepository.findTodoByIdAndUserId(id, user.get().getId());
        if (optionalTodo.isEmpty()) {
            throw new TodoNotFoundException(id);
        }
        Todo todo = optionalTodo.get();
        todoRepository.updateTodo(todoDTO.getContent(), id);
        todo.setContent(todoDTO.getContent());
        return todo;
    }

}
