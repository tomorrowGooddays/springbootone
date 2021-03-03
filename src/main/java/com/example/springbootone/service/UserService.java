package com.example.springbootone.service;
import java.util.*;
import com.example.springbootone.model.User;
import com.example.springbootone.model.requestdto.UserSearchRequest;

public interface UserService {
    List<User> GetUserInfo(String name);

    List<User> FindAll();

    List<User> SearhByPage(UserSearchRequest userSearchRequest);
}
