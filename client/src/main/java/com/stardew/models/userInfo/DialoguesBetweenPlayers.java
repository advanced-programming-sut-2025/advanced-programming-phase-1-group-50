package com.stardew.models.userInfo;

public class DialoguesBetweenPlayers {
    private final Player sender;
    private final Player receiver;
    private final String dialogue;

    public DialoguesBetweenPlayers(Player sender, Player receiver, String dialogue) {
        this.sender = sender;
        this.receiver = receiver;
        this.dialogue = dialogue;
    }

    public Player getReceiver() {
        return receiver;
    }

    @Override
    public String toString() {
        return sender.getNickname() + " : " + dialogue;
    }
}
