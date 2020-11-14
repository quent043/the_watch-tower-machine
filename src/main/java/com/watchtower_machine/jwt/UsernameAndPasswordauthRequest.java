package com.watchtower_machine.jwt;

public class UsernameAndPasswordauthRequest {

    private String username;
    private String password;

    public UsernameAndPasswordauthRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
