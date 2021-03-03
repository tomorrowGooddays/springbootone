package com.example.springbootone.serviceImpl;

import com.example.springbootone.dao.UserDao;
import com.example.springbootone.model.User;
import com.example.springbootone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> GetUserInfo(String name) {
        return this.userDao.SearchByName(name);
    }

    public List<User> FindAll() {
        return this.userDao.findAll();
    }
}
