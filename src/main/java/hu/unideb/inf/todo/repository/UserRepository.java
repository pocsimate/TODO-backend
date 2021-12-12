package hu.unideb.inf.todo.repository;

import hu.unideb.inf.todo.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Long> {

    Optional<UserModel> getUserModelByUsername(String username);

    Optional<UserModel> getUserModelById(Long id);

}


