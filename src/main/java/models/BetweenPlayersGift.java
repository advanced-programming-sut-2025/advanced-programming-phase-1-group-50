package models;

import models.manuFactor.Ingredient;
import models.userInfo.Player;

public class BetweenPlayersGift {
    private final Ingredient ingredient;
    private final Player sender;
    private final Player receiver;
    private final int id;
    private int rate = 3;
    private boolean isRated;

    public BetweenPlayersGift(Ingredient ingredient, Player sender, Player receiver, int id) {
        this.ingredient = ingredient;
        this.sender = sender;
        this.receiver = receiver;
        this.id = id;
        this.isRated = false;
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

    public boolean isRated() {
        return isRated;
    }

    public void setRated() {
        isRated = true;
    }

    @Override
    public String toString() {
        return "id:" + id + "   sender:" +  sender.getUsername() + "    rate:" + rate + "   " + ingredient.toString();
    }
}
