package com.stardew.network;

import com.stardew.model.LobbyDTO;
import com.stardew.model.userInfo.User;

import java.util.ArrayList;


public abstract class Lobby {
    protected final int id;
    protected final String name;
    protected ArrayList<User> users = new ArrayList<>();
    protected User admin;
    protected final boolean visible;
    protected final static int maxSize = 4;
    protected boolean addUserSecondTime = false;

    public Lobby(int id, String name, User admin, boolean visible) {
        this.id = id;
        this.name = name;
        this.admin = admin;
        this.visible = visible;
        addUser(admin);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public User getAdmin() {
        return admin;
    }

    public boolean isVisible() {
        return visible;
    }

    public void addUser(User user) {
        if(users.size() < maxSize) {
            users.add(user);
        }
    }

    public abstract LobbyDTO toDTO();

    public abstract ArrayList<String> getUsernameOfUsers();

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public void removeUser(User user) {
        users.remove(user);
    }

    public void setAddUserSecondTime(boolean addUserSecondTime) {
        this.addUserSecondTime = addUserSecondTime;
    }


    public boolean getAddUserSecondTime() {
        return addUserSecondTime;
    }
}
