package com.hamza.taskgenie.service;

import com.hamza.taskgenie.Mapper.UserMapper;
import com.hamza.taskgenie.dto.UserDTO;
import com.hamza.taskgenie.exception.UserAlreadyExistsException;
import com.hamza.taskgenie.model.User;
import com.hamza.taskgenie.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


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
    public Optional<User> login(String username, String password) {

        Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
        Authentication result = authenticationManager.authenticate(authentication);
        SecurityContextHolder.getContext().setAuthentication(result);

        return Optional.ofNullable(userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found")));
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
