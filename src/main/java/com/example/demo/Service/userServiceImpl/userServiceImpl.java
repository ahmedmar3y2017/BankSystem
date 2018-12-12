package com.example.demo.Service.userServiceImpl;


import com.example.demo.Dao.roleDao;
import com.example.demo.Dao.userDao;
import com.example.demo.Model.User;
import com.example.demo.Model.security.UserRole;
import com.example.demo.Service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Service
public class userServiceImpl implements userService {

    @Autowired
    userDao userDao;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Autowired
    roleDao roleDao;

    public void save(User user) {

        userDao.save(user);
    }

    public User findByUsername(String username) {

        return userDao.findByUsername(username);

    }

    public User findByEmail(String email) {

        return userDao.findByEmail(email);

    }

    public boolean checkUsernameExists(String username) {
        if (findByUsername(username) != null) {

            return true;

        }

        return false;


    }

    public boolean checkEmailExists(String email) {
        if (findByEmail(email) != null) {

            return true;

        }

        return false;


    }

    public boolean checkUserExists(String username, String email) {


        if (checkEmailExists(email) || checkUsernameExists(username)) {


            return true;

        }

        return false;
    }


    @Transactional
    public User createUser(User user, Set<UserRole> userRoles) {
        User localUser = userDao.findByUsername(user.getUsername());
        if (localUser != null) {


            System.out.println("Error this user exists ");

        } else {
            // encrypt password
            String encryptedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encryptedPassword);
            for (UserRole userRole : userRoles) {
                roleDao.save(userRole.getRole());
            }
            user.getUserRoles().addAll(userRoles);

            // create user
            localUser = userDao.save(localUser);

        }

        return localUser;
    }


}
