package com.stardew.models.ClientInfo;

import com.stardew.model.UserDTO;

public class LoggedInUser {
    private static UserDTO user;

    public static void setUser(UserDTO user) {
        LoggedInUser.user = user;
    }

    public static UserDTO getUser() {
        return user;
    }

    public boolean isLoggedIn() {
        return user != null;
    }

    public void logout() {
        user = null;
    }
}
