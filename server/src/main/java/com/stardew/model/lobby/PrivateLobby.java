package com.stardew.model.lobby;

import com.stardew.model.LobbyDTO;
import com.stardew.model.userInfo.User;

public class PrivateLobby extends Lobby {
    private String password;
    public PrivateLobby(int id, String name, User admin, boolean visible , String password) {
        super(id, name, admin, visible);
        this.password = password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public LobbyDTO toDTO(){
        return new LobbyDTO(id , name , visible , true , admin.getUsername() ,getUsernameOfUsers() );
    }

}
