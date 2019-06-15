package com.dasu.ganhuo.entity;

/**
 * User
 *
 * @author fangyuan
 * @date 2019/6/15
 */
public class User {
    public String name;
    public String pwd;
    
    public User() {
    }
    
    public User(String name, String pwd) {
        this.name = name;
        this.pwd = pwd;
    }
    
    @Override
    public String toString() {
        return "User{" + "name='" + name + '\'' + ", pwd='" + pwd + '\'' + '}';
    }
}
