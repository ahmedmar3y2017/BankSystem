package com.example.demo.Service;

import com.example.demo.Model.User;
import com.example.demo.Model.security.UserRole;

import java.util.Set;

public interface userService {

    public void save(User user);

    public User findByUsername(String username);

    public User findByEmail(String email);

    public boolean checkUsernameExists(String username);

    public boolean checkEmailExists(String email);

    public boolean checkUserExists(String username, String email);

    public User createUser(User user, Set<UserRole> userRoles);

}
