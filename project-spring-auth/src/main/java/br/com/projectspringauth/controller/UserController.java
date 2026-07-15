package br.com.projectspringauth.controller;

import br.com.projectspringauth.model.User;
import br.com.projectspringauth.security.MyToken;
import br.com.projectspringauth.service.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.addUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<MyToken> login(@RequestBody User user) {
        return ResponseEntity.ok(userService.userLogin(user));
    }
}
