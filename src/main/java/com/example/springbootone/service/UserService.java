package com.example.springbootone.service;
import java.util.*;
import com.example.springbootone.model.User;

public interface UserService {
    List<User> GetUserInfo(String name);
}
