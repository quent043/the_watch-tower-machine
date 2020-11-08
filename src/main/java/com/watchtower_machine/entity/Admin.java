package com.watchtower_machine.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Admins")
public class Admin extends User {

    @Column(name = "is_admin")
    @NonNull
    private boolean isAdmin;

//    @JsonProperty("name")@JsonProperty("name")
    public Admin(String name, String surName, String userName, String password,
                 @JsonProperty("isAdmin")Boolean isAdmin) {
        super(name, surName, userName, password);
        this.isAdmin = isAdmin;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public String toString() {
        return "Admin{ name='" + getName() + '\'' +
                ", surName='" + getSurName() + '\'' +
                ", userName='" + getUserName() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }
}
