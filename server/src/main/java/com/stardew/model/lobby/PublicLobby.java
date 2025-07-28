package com.stardew.model.lobby;

import com.stardew.model.LobbyDTO;
import com.stardew.model.userInfo.User;

public class PublicLobby extends Lobby {
    public PublicLobby(int id, String name, User admin, boolean visible) {
        super(id, name, admin, visible);
    }

    public LobbyDTO toDTO(){
        return new LobbyDTO(id , name , visible , false , admin.getUsername(), getUsernameOfUsers() );
    }

}
