package hu.unideb.inf.todo.converter.user;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import hu.unideb.inf.todo.dto.user.UserRegistrationDTO;
import hu.unideb.inf.todo.dto.user.UserResponseDTO;
import hu.unideb.inf.todo.model.UserModel;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserConverter {

    public UserModel convertUserRegistrationDTOToUser(UserRegistrationDTO userRegistrationDTO) {
        UserModel userModel = new UserModel();
        userModel.setUsername(userRegistrationDTO.getUsername());
        String dateString = userRegistrationDTO.getBirthDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(userRegistrationDTO.getBirthDate(), formatter);
        userModel.setBirth(date);
        userModel.setPassword(new BCryptPasswordEncoder().encode(userRegistrationDTO.getPassword()));
        return userModel;
    }

    public UserResponseDTO convertUserToUserResponseDTO(UserModel newUserModel) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(newUserModel.getId());
        userResponseDTO.setUsername(newUserModel.getUsername());
        userResponseDTO.setBirthDate(DateTimeFormatter.ofPattern("yyyy-MM-dd").format(newUserModel.getBirth()));
        return userResponseDTO;
    }
}
