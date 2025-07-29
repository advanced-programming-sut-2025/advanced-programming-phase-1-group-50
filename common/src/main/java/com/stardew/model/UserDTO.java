package com.stardew.model;

public class UserDTO {
    private String username;
    private String nickname;

    public UserDTO() {}

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
