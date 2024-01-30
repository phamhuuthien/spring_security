package com.SpringSecurity.SpringSecurity.service;

import com.SpringSecurity.SpringSecurity.entity.Role;
import com.SpringSecurity.SpringSecurity.entity.User;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);

    void addToUser(String username, String rolename);
}
