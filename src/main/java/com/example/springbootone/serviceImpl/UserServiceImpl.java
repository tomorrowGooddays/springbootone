package com.example.springbootone.serviceImpl;

import com.example.springbootone.dao.UserDao;
import com.example.springbootone.dao.UserDaoImpl;
import com.example.springbootone.model.User;
import com.example.springbootone.model.requestdto.UserSearchRequest;
import com.example.springbootone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserDaoImpl userDaoImpl;

    @Override
    public List<User> GetUserInfo(String name) {
        return this.userDao.SearchByName(name);
    }

    public List<User> FindAll() {
        return this.userDao.findAll();
    }

    public List<User> SearhByPage(UserSearchRequest userSearchRequest){
        return this.userDaoImpl.GetUserInfoList(userSearchRequest);
    }
}
