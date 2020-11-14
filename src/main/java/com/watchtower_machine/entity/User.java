package com.watchtower_machine.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.NonNull;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

//TODO: Implement l'interface UserDetails pour que Sprign Security marche
@MappedSuperclass
public abstract class User {
    @Id
    @Column(name = "user_name", unique = true)
    @NonNull
    private String userName;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surName;
    @NonNull
    @Column(name = "password")
    private String password;

    public User(@JsonProperty("name") String name,
                @JsonProperty("surName") String surName,
                @JsonProperty("userName")String userName,
                @JsonProperty("pwd")String password) {
        this.name = name;
        this.surName = surName;
        this.userName = userName;
        this.password = password;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surName='" + surName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
