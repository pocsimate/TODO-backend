package hu.unideb.inf.todo.service;

import hu.unideb.inf.todo.converter.user.UserConverter;
import hu.unideb.inf.todo.dto.user.UserAuthenticationDTO;
import hu.unideb.inf.todo.dto.user.UserRegistrationDTO;
import hu.unideb.inf.todo.dto.user.UserResponseDTO;
import hu.unideb.inf.todo.exception.user.IncorrectPasswordException;
import hu.unideb.inf.todo.exception.user.NoSuchUserException;
import hu.unideb.inf.todo.exception.user.UserAlreadyExistsException;
import hu.unideb.inf.todo.model.User;
import hu.unideb.inf.todo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Transactional
    public UserResponseDTO register(UserRegistrationDTO userRegistrationDTO){
        Optional<User> user = userRepository.getUserByUsername(userRegistrationDTO.getUsername());
        if(user.isEmpty()) {
            User newUser = userConverter.convertUserRegistrationDTOToUser(userRegistrationDTO);
            userRepository.save(newUser);
            return userConverter.convertUserToUserResponseDTO(newUser);
        } else {
            throw new UserAlreadyExistsException(userRegistrationDTO.getUsername());
        }
    }

    @Transactional
    public UserResponseDTO login(UserAuthenticationDTO userAuthenticationDTO) {
        Optional<User> user = userRepository.getUserByUsername(userAuthenticationDTO.getUsername());
        if(user.isEmpty()) {
            throw new NoSuchUserException(userAuthenticationDTO.getUsername());
        } else {
            if(user.get().getPassword().equals(userAuthenticationDTO.getPassword())){
                return userConverter.convertUserToUserResponseDTO(user.get());
            } else {
                throw new IncorrectPasswordException();
            }
        }
    }
}
