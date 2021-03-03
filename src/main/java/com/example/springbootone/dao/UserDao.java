package com.example.springbootone.dao;

import com.example.springbootone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.lang.Integer;
import java.util.List;

public interface UserDao extends JpaRepository<User, Integer> {

    @Query(value = "select * from user where name=?1", nativeQuery = true)
    List<User> SearchByName(String name);
}
