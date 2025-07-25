package com.stardew.model;

import java.util.ArrayList;

public class LobbyDTO {
    public int id;
    public String name;
    public boolean isVisible;
    public boolean isPrivate;
    public static final int maxPlayers = 4;
    public ArrayList<String > players;
    public String adminUsername;
    public boolean addUserSecondTime ;

    public LobbyDTO(int id, String name, boolean isVisible, boolean isPrivate , String adminUsername , ArrayList<String > players , boolean addUserSecondTime) {
        this.id = id;
        this.name = name;
        this.isVisible = isVisible;
        this.isPrivate = isPrivate;
        this.adminUsername = adminUsername;
        this.players = players;
        this.addUserSecondTime = addUserSecondTime;
    }
}
