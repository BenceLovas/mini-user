package com.bencelovas.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public ResponseEntity registration(@RequestBody User userInput) {
        System.out.println(userInput.getName());
        System.out.println(userInput.getPassword());
        System.out.println(userInput.getEmail());
        return ResponseEntity.ok(Collections.singletonMap("response", "success"));
    }

    @PostMapping("/user/login")
    public ResponseEntity login(@RequestBody User userInput) {
        System.out.println(userInput.getEmail());
        System.out.println(userInput.getPassword());
        return ResponseEntity.ok(Collections.singletonMap("response", "success"));
    }

}
