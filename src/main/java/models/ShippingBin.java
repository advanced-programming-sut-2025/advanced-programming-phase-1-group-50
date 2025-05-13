package models;

import models.app.App;
import models.manuFactor.Ingredient;
import models.userInfo.Coin;
import models.userInfo.Player;

import java.awt.*;
import java.util.HashMap;

public class ShippingBin implements Placeable{

    private HashMap<Player,Integer> dailyRevenue;
    private final char symbol = 'ø';
    private final Rectangle bounds;

    public ShippingBin(int x, int y) {

        this.bounds = new Rectangle(x,y,1,1);
        for (Player player : App.getGame().getPlayers()) {
            dailyRevenue.put(player,0);
        }

    }

    @Override
    public Rectangle getBounds() {
        return bounds;
    }

    @Override
    public char getSymbol() {
        return symbol;
    }

    public void checkEveryNight() {

        for (Player player : this.dailyRevenue.keySet()) {
            for (Ingredient ingredient : player.getBackpack().getIngredientQuantity().keySet()) {
                if (ingredient instanceof Coin coin) {
                    player.getBackpack().addIngredients(coin, dailyRevenue.get(player));
                }
            }
            dailyRevenue.put(player,0);
        }

    }

    public void increaseRevenue(Player player,int revenue) {
        dailyRevenue.put(player,dailyRevenue.get(player) + revenue);
    }
}
