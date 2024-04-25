package com.hamza.taskgenie.dto;

import com.hamza.taskgenie.enumerations.Role;
import lombok.Data;

@Data
public class UserDTO {

    String username;
    String password;
    String email;
    Role role;
}
