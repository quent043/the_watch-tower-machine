package com.watchtower_machine.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
public class BasicUser extends User {

    public BasicUser(String name, String surName, String userName, String password) {
        super(name, surName, userName, password);
    }


}
