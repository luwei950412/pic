package com.example.dubbo.dubbolearn.domain;

import java.io.Serializable;

//import lombok.Builder;
//import lombok.Data;
//
//@Data
//@Builder
public class Group implements Serializable {

    private String id;

    private String name;

    private User user;


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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String toString(){
        return "Group{id="+id+",name="+name+",user=User{id="+user.getId()+",name="+user.getName()+",age="+user.getAge()+",desc="+user.getDesc()+"}}";
    }
}
