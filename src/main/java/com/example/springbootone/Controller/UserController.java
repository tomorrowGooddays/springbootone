package com.example.springbootone.Controller;

import com.example.springbootone.model.Response;
import com.example.springbootone.model.User;
import com.example.springbootone.model.UserDto;
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
    post请求，body里面传请求结构:http://localhost:8888/user/searchbypagenew
     */
    @PostMapping("/user/searchbypagenew")
    public Response<List<UserDto>> PageSearchNew(@RequestBody UserSearchRequest userSearchRequest) {
        Response<List<UserDto>> response = new Response<List<UserDto>>();
        List<UserDto> userDtos = null;
        try {
            Page<List> pageRows = this.userService.SearhByPageNew(userSearchRequest);

            long itemSize = pageRows.getTotalElements();//datacount
            response.setCount(itemSize);

            userDtos = new ArrayList<UserDto>((int) itemSize);
            List rowsContent = pageRows.getContent();//data
            if (itemSize > 0) {
                for (Object obj : rowsContent) {
                    HashMap hashMap = (HashMap) obj;

                    userDtos.add(BuildUserDto(hashMap));
                }
            }

            response.setResponse(userDtos);

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        response.setMessage("查询成功");
        response.setReturnCode(200);

        return response;
    }

    /*
    构造返回结构实体
     */
    private static UserDto BuildUserDto(HashMap hashMap) {
        return UserDto.builder()
                .id(Integer.valueOf(hashMap.get("id").toString()))
                .name(hashMap.get("name") == null ? "" : hashMap.get("name").toString())
                .mobile(hashMap.get("mobile") == null ? "" : hashMap.get("mobile").toString())
                .nick_name((hashMap.get("nickname") == null ? "" : hashMap.get("nickname").toString()))
                .password(hashMap.get("password") == null ? "" : hashMap.get("password").toString())
                .rtmp_pull_address(hashMap.get("rtmp_pull_address") == null ? "" : hashMap.get("rtmp_pull_address").toString())
                .rtmp_push_address(hashMap.get("rtmp_push_address") == null ? "" : hashMap.get("rtmp_push_address").toString())
                .build();
    }
}
