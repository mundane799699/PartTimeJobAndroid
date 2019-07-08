package com.example.bean;

import java.io.Serializable;

public class User implements Serializable {
    public String name;
    public String password;
    public int id;
    
    public User() {
    }
    
    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }
    
}
