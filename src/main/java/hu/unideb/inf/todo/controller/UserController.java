package hu.unideb.inf.todo.controller;

import javax.servlet.http.HttpServletRequest;

import hu.unideb.inf.todo.dto.user.UserAuthenticationDTO;
import hu.unideb.inf.todo.dto.user.UserRegistrationDTO;
import hu.unideb.inf.todo.dto.user.UserResponseDTO;
import hu.unideb.inf.todo.security.JwtTokenProvider;
import hu.unideb.inf.todo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/api/v1/user")
@CrossOrigin
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserService userService;

    @PostMapping(path = "/register")
    @ResponseBody
    public ResponseEntity<UserResponseDTO> register(@RequestBody UserRegistrationDTO userRegistrationDTO){
        UserResponseDTO response = userService.register(userRegistrationDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/me")
    public ResponseEntity<UserResponseDTO> me(HttpServletRequest req){
        UserResponseDTO response = userService.whoami(req);
        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "/login")
    @ResponseBody
    public ResponseEntity<String> login(@RequestBody UserAuthenticationDTO userAuthenticationDTO){
        Authentication request = new UsernamePasswordAuthenticationToken(userAuthenticationDTO.getUsername(), userAuthenticationDTO.getPassword());
        try {
            authenticationManager.authenticate(request);
            return ResponseEntity.ok(jwtTokenProvider.createToken(userAuthenticationDTO.getUsername()));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().body("Login failed due to incorrect credentials");
        }
    }
}
