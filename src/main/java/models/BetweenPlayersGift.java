package models;

import models.manuFactor.Ingredient;
import models.userInfo.Player;

public class BetweenPlayersGift {
    private final Ingredient ingredient;
    private final Player sender;
    private final Player receiver;
    private final int id;
    private int rate = 3;

    public BetweenPlayersGift(Ingredient ingredient, Player sender, Player receiver, int id) {
        this.ingredient = ingredient;
        this.sender = sender;
        this.receiver = receiver;
        this.id = id;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public int getId() {
        return id;
    }

    public Player getReceiver() {
        return receiver;
    }

    public Player getSender() {
        return sender;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
