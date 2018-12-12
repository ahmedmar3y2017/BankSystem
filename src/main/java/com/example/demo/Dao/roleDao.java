package com.example.demo.Dao;

import com.example.demo.Model.security.Role;
import org.springframework.data.repository.CrudRepository;

public interface roleDao extends CrudRepository<Role, Integer> {
    Role findByName(String name);

}
