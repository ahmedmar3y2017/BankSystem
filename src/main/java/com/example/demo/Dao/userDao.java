package com.example.demo.Dao;

import com.example.demo.Model.User;
import org.springframework.data.repository.CrudRepository;

public interface userDao extends CrudRepository<User, Long> {

    User findByUsername(String username);

    User findByEmail(String email);

}
