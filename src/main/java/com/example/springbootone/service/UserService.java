package com.example.springbootone.service;
import java.util.*;
import com.example.springbootone.model.User;
import com.example.springbootone.model.requestdto.UserSearchRequest;
import org.springframework.data.domain.Page;

public interface UserService {
    List<User> GetUserInfo(String name);

    List<User> FindAll();

    List<User> SearhByPage(UserSearchRequest userSearchRequest);

    Page<User> SearhByPageNew(UserSearchRequest userSearchRequest);
}
