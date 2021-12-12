package hu.unideb.inf.todo.repository;

import java.util.Optional;

import hu.unideb.inf.todo.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Long> {

    Optional<UserModel> getUserModelByUsername(String username);

    Optional<UserModel> getUserModelById(Long id);

}


