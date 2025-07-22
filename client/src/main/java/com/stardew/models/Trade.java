package com.stardew.models;

import com.stardew.models.stores.Sellable;
import com.stardew.models.userInfo.Player;

public class Trade {

    private int id;
    private final Player seller;
    private final Player buyer;
    private final int quantity;
    private final Sellable sellable;
    private final int price;
    private boolean isResponded;
    private boolean accepted;
    private boolean isViewed;

    public Trade(Player seller, Player buyer, int quantity, Sellable sellable, int price, int id) {
        this.seller = seller;
        this.buyer = buyer;
        this.quantity = quantity;
        this.sellable = sellable;
        this.price = price;
        this.isResponded = false;
        this.isViewed = false;
        this.accepted = false;
        this.id = id;
    }

    public Player getSeller() {
        return seller;
    }

    public Player getBuyer() {
        return buyer;
    }

    public int getQuantity() {
        return quantity;
    }

    public Sellable getSellable() {
        return sellable;
    }

    public int getPrice() {
        return price;
    }

    public boolean isResponded() {
        return isResponded;
    }

    public void setResponded(boolean responded) {
        isResponded = responded;
    }

    public boolean isViewed() {
        return isViewed;
    }

    public void setViewed(boolean viewed) {
        isViewed = viewed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    @Override
    public String toString() {
        String message =
                "Id: " + id + "        seller: " + seller.getUsername() + "    product: " + Sellable.getNameInString(sellable) +
                "       quantity: " + quantity + "    price: " + price + "    isResponded: ";
        if (isResponded) {
            if (accepted) {
                message += "accepted";
            } else {
                message += "rejected";
            }
        } else {
            message += "no";
        }

        return message;

    }

}
