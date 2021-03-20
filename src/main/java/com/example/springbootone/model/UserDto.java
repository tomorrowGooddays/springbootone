package com.example.springbootone.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class UserDto implements Serializable {

    private Integer id;
    private String name;
    private String nick_name;
    private String mobile;
    private String password;
    private String rtmp_push_address;
    private String rtmp_pull_address;

}
