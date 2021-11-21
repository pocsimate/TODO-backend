package hu.unideb.inf.todo.converter.user;

import hu.unideb.inf.todo.dto.user.UserRegistrationDTO;
import hu.unideb.inf.todo.dto.user.UserResponseDTO;
import hu.unideb.inf.todo.exception.user.BirthDateException;
import hu.unideb.inf.todo.model.User;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;


@Service
public class UserConverter {

    public User convertUserRegistrationDTOToUser(UserRegistrationDTO userRegistrationDTO) {
        try {
            User user = new User();
            user.setUsername(userRegistrationDTO.getUsername());
            user.setBirth(new SimpleDateFormat("YYYY/MM/DD").parse(userRegistrationDTO.getBirthDate()));
            user.setPassword(userRegistrationDTO.getPassword());
            return user;
        } catch (ParseException ex) {
            throw new BirthDateException(userRegistrationDTO.getBirthDate());
        }

    }

    public UserResponseDTO convertUserToUserResponseDTO(User newUser) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(newUser.getId());
        userResponseDTO.setUsername(newUser.getUsername());
        userResponseDTO.setBirthDate(new SimpleDateFormat("YYYY/MM/DD").format(newUser.getBirth()));
        return userResponseDTO;
    }
}
