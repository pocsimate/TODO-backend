package hu.unideb.inf.todo.controller;

import hu.unideb.inf.todo.dto.user.UserAuthenticationDTO;
import hu.unideb.inf.todo.dto.user.UserRegistrationDTO;
import hu.unideb.inf.todo.dto.user.UserResponseDTO;
import hu.unideb.inf.todo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/api/v1/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/register")
    @ResponseBody
    public ResponseEntity<UserResponseDTO> register(@RequestBody UserRegistrationDTO userRegistrationDTO){
        UserResponseDTO response = userService.register(userRegistrationDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "/login")
    @ResponseBody
    public ResponseEntity<UserResponseDTO> register(@RequestBody UserAuthenticationDTO userAuthenticationDTO){
        UserResponseDTO response = userService.login(userAuthenticationDTO);
        return ResponseEntity.ok(response);
    }
}
