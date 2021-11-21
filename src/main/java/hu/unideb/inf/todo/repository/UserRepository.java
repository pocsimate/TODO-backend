package hu.unideb.inf.todo.repository;

import hu.unideb.inf.todo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u from User u WHERE u.username = ?1")
    Optional<User> getUserByUsername(String username);

}


