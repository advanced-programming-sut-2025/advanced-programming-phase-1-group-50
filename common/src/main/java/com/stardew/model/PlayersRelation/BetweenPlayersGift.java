package com.stardew.model.PlayersRelation;

public class BetweenPlayersGift {
    private final String productName;
    private final String senderUsername;
    private final String receiverUsername;
    private final int id;
    private int rate = 3;
    private boolean isRated;

    public BetweenPlayersGift(String productName, String senderUsername, String receiverUsername, int id) {
        this.productName = productName;
        this.senderUsername = senderUsername;
        this.receiverUsername = receiverUsername;
        this.id = id;
        this.isRated = false;
    }

    public String getProductName() {
        return productName;
    }

    public int getId() {
        return id;
    }

    public String getReceiverUsername() {
        return receiverUsername;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public boolean isRated() {
        return isRated;
    }

    public void setRated() {
        isRated = true;
    }
}
