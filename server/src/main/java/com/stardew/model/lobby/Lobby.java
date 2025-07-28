package com.stardew.model.lobby;

import com.stardew.model.LobbyDTO;
import com.stardew.model.userInfo.User;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;


public abstract class Lobby {
    protected final int id;
    protected final String name;
    protected final CopyOnWriteArrayList<User> users = new CopyOnWriteArrayList<>();
    protected User admin;
    protected final boolean visible;
    protected final static int maxSize = 4;


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

    public CopyOnWriteArrayList<User> getUsers() {
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

    public ArrayList<String> getUsernameOfUsers(){
        ArrayList<String> usernames = new ArrayList<>();
        for(User u : users){
            usernames.add(u.getUsername());
        }
        return usernames;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public void removeUser(User user) {
        users.remove(user);
    }


    public boolean hasCapacity(){
        return users.size() < maxSize;
    }
}
