package com.stardew.model;

public class UserDTO {
    private final String username;
    private final String nickname;

    public UserDTO(String username, String nickname){
        this.username = username;
        this.nickname = nickname;
    }

    public String getUsername() {
        return username;
    }

    public String getNickname() {
        return nickname;
    }
}
