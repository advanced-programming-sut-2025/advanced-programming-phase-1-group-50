package models.Notification;

import models.userInfo.Player;

public class MarriageRequest extends Notification {

    private final Player sender;
    public MarriageRequest(String message, Player sender) {
        super(message);
        this.sender = sender;
    }

    public Player getSender() {
        return sender;
    }
}
