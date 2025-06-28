package com.stardew.models.Notification;

import com.stardew.models.userInfo.Player;

public class MarriageRequest extends Notification {

    public MarriageRequest(String message, Player sender) {
        super(message , sender);
    }

    @Override
    public String toString() {
        return "sender : " + sender.getUsername() + "  message: " + getMessage() +"   (Marriage Request)";
    }

}
