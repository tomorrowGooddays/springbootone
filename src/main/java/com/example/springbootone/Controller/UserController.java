package com.example.springbootone.Controller;

import com.example.springbootone.model.Response;
import com.example.springbootone.model.User;
import com.example.springbootone.model.requestdto.UserSearchRequest;
import com.example.springbootone.service.UserService;
import com.example.springbootone.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /*
    通过name搜索，get请求,请求示例：http://localhost:8888/user/searchbyname?name=user2
     */
    @RequestMapping("/user/searchbyname")
    public Response<List<User>> SearchByName(@RequestParam(name = "name", required = false) String name) {
        Response<List<User>> response = new Response<List<User>>();

        List<User> users = this.userService.GetUserInfo(name);
        response.setMessage("查询成功");
        response.setReturnCode(200);
        response.setResponse(users);

        return response;
    }

    /*
    post请求，body里面传请求结构:http://localhost:8888/user/searchbypage
     */
    @PostMapping("/user/searchbypage")
    public Response<List<User>> PageSearch(@RequestBody UserSearchRequest userSearchRequest) {
        Response<List<User>> response = new Response<List<User>>();
        List<User> users = null;
        try {
            users = this.userService.SearhByPage(userSearchRequest);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        response.setMessage("查询成功");
        response.setReturnCode(200);
        response.setResponse(users);
        return response;
    }

    /*
    post请求，body里面传请求结构:http://localhost:8888/user/searchbypage
     */
    @PostMapping("/user/searchbypagenew")
    public Response<List<User>> PageSearchNew(@RequestBody UserSearchRequest userSearchRequest) {
        Response<List<User>> response = new Response<List<User>>();
        List<User> users = null;
        try {
            Page<User> usersDto = this.userService.SearhByPageNew(userSearchRequest);
            response.setCount(usersDto.getTotalElements());
            users = usersDto.getContent();
            response.setResponse(users);

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        response.setMessage("查询成功");
        response.setReturnCode(200);

        return response;
    }
}
