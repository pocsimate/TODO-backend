package hu.unideb.inf.todo.service;

import hu.unideb.inf.todo.converter.user.UserConverter;
import hu.unideb.inf.todo.dto.user.UserRegistrationDTO;
import hu.unideb.inf.todo.dto.user.UserResponseDTO;
import hu.unideb.inf.todo.exception.user.JWTInvalidException;
import hu.unideb.inf.todo.exception.user.NoSuchUserException;
import hu.unideb.inf.todo.exception.user.UserAlreadyExistsException;
import hu.unideb.inf.todo.model.UserModel;
import hu.unideb.inf.todo.repository.UserRepository;
import hu.unideb.inf.todo.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    Optional<UserModel> getUserByUsername(String username) {
        return userRepository.getUserModelByUsername(username);
    }

    public Optional<UserModel> getUserById(Long userid) {
        return userRepository.getUserModelById(userid);
    }

    public UserResponseDTO whoami(HttpServletRequest req) {
        Optional<UserModel> userModel =  userRepository.getUserModelByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
        if (userModel.isPresent()) {
            return userConverter.convertUserToUserResponseDTO(userModel.get());
        } else {
            throw new JWTInvalidException();
        }
    }

    public UserResponseDTO register(UserRegistrationDTO userRegistrationDTO){
        Optional<UserModel> user = getUserByUsername(userRegistrationDTO.getUsername());
        if(user.isEmpty()) {
            UserModel newUserModel = userConverter.convertUserRegistrationDTOToUser(userRegistrationDTO);
            userRepository.save(newUserModel);
            return userConverter.convertUserToUserResponseDTO(newUserModel);
        } else {
            throw new UserAlreadyExistsException(userRegistrationDTO.getUsername());
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserModel> user = getUserByUsername(username);
        if(user.isEmpty()) {
            throw new UsernameNotFoundException(username);
        } else {
            User.UserBuilder userBuilder = User.builder();
            return userBuilder
                    .username(user.get().getUsername())
                    .password(user.get().getPassword())
                    .roles("USER")
                    .build();
        }
    }
}
