package com.stardew.model.Notification;

public class MarriageRequest extends Notification {
    public MarriageRequest(String message, String senderUsername) {
        super(message , senderUsername);
    }

    @Override
    public String toString() {
        return "sender : " + senderUsername + "  message: " + getMessage() +"   (Marriage Request)";
    }
}
