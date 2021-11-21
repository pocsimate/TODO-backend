package hu.unideb.inf.todo.repository;

import hu.unideb.inf.todo.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    @Query("SELECT t FROM Todo t WHERE t.id = ?1")
    Optional<Todo> getTodoById(long id);

    @Modifying
    @Query("UPDATE Todo SET content = ?1 WHERE id = ?2")
    void updateTodo(String content, long id);

    @Modifying
    @Query("DELETE FROM Todo t WHERE t.id = ?1")
    void deleteTodoById(long id);

}
