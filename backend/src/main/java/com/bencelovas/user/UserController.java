package com.bencelovas.user;

import com.bencelovas.user.exception.EmailAlreadyTakenException;
import com.bencelovas.user.exception.InvalidCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
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
    public ResponseEntity registration(@RequestBody User userInput, HttpSession session) {
        try {
            Long userID = this.userService.addUser(userInput);
            session.setAttribute("userID", userID);
            return ResponseEntity
                    .ok(Collections.singletonMap("response", "success"));
        } catch (EmailAlreadyTakenException e) {
            System.out.println(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("email", userInput.getEmail()));
        }
    }

    @PostMapping("/user/login")
    public ResponseEntity login(@RequestBody User userInput, HttpSession session) {
        try {
            Long userID = userService.validateUser(userInput);
            session.setAttribute("userID", userID);
            return ResponseEntity
                    .ok(Collections.singletonMap("response", "success"));
        } catch (InvalidCredentialsException e) {
            System.out.println(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("response", "not valid"));
        }
    }

}
