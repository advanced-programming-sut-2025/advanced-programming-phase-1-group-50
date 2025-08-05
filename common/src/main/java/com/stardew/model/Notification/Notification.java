package com.stardew.model.Notification;

public class Notification {
    protected final String message;
    protected boolean isChecked;
    protected final String senderUsername;

    public Notification(String message, String sender) {
        this.message = message;
        this.isChecked = false;
        this.senderUsername = sender;
    }

    public String getMessage() {
        return message;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getSender() {
        return senderUsername;
    }

    @Override
    public String toString() {
        return "sender: " + senderUsername + "  message: " + getMessage();
    }
}
