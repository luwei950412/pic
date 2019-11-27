package com.example.dubbo.dubbolearn.domain;


import java.io.Serializable;

//import lombok.Builder;
//import lombok.Data;
//
//@Data
//@Builder
public class User implements Serializable {
    private String id;

    private String name;

    private Integer age;

    private String desc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
