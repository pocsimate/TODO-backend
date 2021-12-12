package hu.unideb.inf.todo.repository;

import hu.unideb.inf.todo.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    List<Todo> findTodosByUserId(long userId);

    Optional<Todo> findTodoByIdAndUserId(long id, long userid);

    @Modifying
    @Query("UPDATE Todo SET content = ?1 WHERE id = ?2")
    void updateTodo(String content, long id);

    @Modifying
    @Query("DELETE FROM Todo t WHERE t.id = ?1")
    void deleteTodoById(long id);

}
