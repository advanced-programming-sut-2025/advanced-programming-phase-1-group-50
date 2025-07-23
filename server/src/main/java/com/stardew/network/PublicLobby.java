package com.stardew.network;

import com.stardew.model.LobbyDTO;
import com.stardew.model.userInfo.User;

import java.util.ArrayList;

public class PublicLobby extends Lobby {
    public PublicLobby(int id, String name, User admin, boolean visible) {
        super(id, name, admin, visible);
    }

    public LobbyDTO toDTO(){
        return new LobbyDTO(id , name , visible , false , admin.getUsername(), getUsernameOfUsers());
    }

    public ArrayList<String > getUsernameOfUsers(){
        ArrayList<String> usernames = new ArrayList<>();
        for(User u : users){
            usernames.add(u.getUsername());
        }
        return usernames;
    }
}
