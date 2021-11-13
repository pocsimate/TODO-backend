package hu.unideb.inf.todo.repository;

import hu.unideb.inf.todo.model.Todo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


public interface TodoRepository extends CrudRepository<Todo, Long> {

    @Query("SELECT t FROM Todo t WHERE t.id = ?1")
    public Optional<Todo> getTodoById(long id);

    @Transactional
    @Modifying
    @Query("DELETE FROM Todo t WHERE t.id = ?1")
    public void deleteTodoById(long id);


}
