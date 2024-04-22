package com.hamza.taskgenie.controller;


import com.hamza.taskgenie.constants.UserConstants;
import com.hamza.taskgenie.dto.ResponseDto;
import com.hamza.taskgenie.dto.UserDTO;
import com.hamza.taskgenie.model.User;
import com.hamza.taskgenie.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/users", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@Validated @RequestBody UserDTO userDTO) {
            User savedUser = userService.registerNewUser(userDTO);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new ResponseDto(UserConstants.STATUS_201, UserConstants.MESSAGE_201));
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAllUsers();
        return ResponseEntity.ok(users);
    }


    @GetMapping("/find")
    public ResponseEntity<User> findUserByUsername(@RequestParam String username) {
        User user = userService.findByUsername(username).orElse(null);
        return ResponseEntity.ok(user);
    }

}
