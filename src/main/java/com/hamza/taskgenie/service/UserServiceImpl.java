package com.hamza.taskgenie.service;

import com.hamza.taskgenie.Mapper.UserMapper;
import com.hamza.taskgenie.dto.UserDTO;
import com.hamza.taskgenie.exception.UserAlreadyExistsException;
import com.hamza.taskgenie.model.User;
import com.hamza.taskgenie.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;


    @Override
    @Transactional
    public User registerNewUser(UserDTO dto) {
        if(userRepository.findByUsername(dto.getUsername()).isPresent()){
            throw new UserAlreadyExistsException("There is already a user registered with that username");
        }
        User user = UserMapper.convertToEntity(dto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> login(String username, String password) throws Exception {
        Optional<User> user = userRepository.findByUsername((username));

        if(user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            return user;
        }
        throw new Exception("Invalid username or password");

    }

    @Override
    public User updateUser(User user) throws Exception {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId) throws Exception {
        User user = userRepository.findById(userId).orElseThrow(() -> new Exception("User not found"));
        userRepository.delete(user);

    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
