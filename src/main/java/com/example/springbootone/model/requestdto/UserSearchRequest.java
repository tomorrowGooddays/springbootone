package com.example.springbootone.model.requestdto;

public class UserSearchRequest extends PageRequest{
    private String name;
    private Integer id;
    private String mobile;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


    @Override
    public String toString() {
        return "UserSearchRequest{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", mobile='" + mobile + '\'' +
                ", page='" + page + '\'' +
                ", pageSize='" + pageSize + '\'' +
                '}';
    }
}
