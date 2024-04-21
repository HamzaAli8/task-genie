package com.hamza.taskgenie.service;

import com.hamza.taskgenie.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User registerNewUser (User user) throws Exception;

    Optional<User> findByUsername (String username);

    Optional<User> login (String username, String password) throws Exception;

    User updateUser(User user) throws Exception;

    void deleteUser(Long userId) throws Exception;

    List<User> findAllUsers();
}
