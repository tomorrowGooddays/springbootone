package com.example.springbootone.Controller;

import com.example.springbootone.model.Response;
import com.example.springbootone.model.User;
import com.example.springbootone.service.UserService;
import com.example.springbootone.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /*
    通过name搜索，get请求
     */
    @RequestMapping("/searchbyname")
    public Response<List<User>> SearchByName(@RequestParam("name") String name) {
        Response<List<User>> response = new Response<List<User>>();

        List<User> users = this.userService.GetUserInfo(name);
        response.setMessage("查询成功");
        response.setReturnCode(200);
        response.setResponse(users);

        return response;
    }
}
