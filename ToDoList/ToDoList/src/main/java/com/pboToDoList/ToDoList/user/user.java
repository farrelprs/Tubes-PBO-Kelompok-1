package com.pboToDoList.ToDoList.user;

public abstract class user {
    private String username;
    private String password;

    public user() {
    }

    public user(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public abstract String getUsername();
    public abstract void setUsername(String username);
    public abstract String getPassword();
    public abstract void setPassword(String password);
}
