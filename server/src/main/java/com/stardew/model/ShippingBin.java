package com.stardew.model;

import com.stardew.controller.GameSessionController;
import com.stardew.model.gameApp.Game;
import com.stardew.model.mapInfo.Placeable;
import com.stardew.model.userInfo.Coin;
import com.stardew.model.userInfo.Player;
import com.stardew.model.mapInfo.Ingredient;

import java.awt.*;
import java.util.HashMap;

public class ShippingBin implements Placeable {

    private final HashMap<Player, Integer> dailyRevenue = new HashMap<>();
    private final int id;
    private final char symbol = 'ø';
    private final Rectangle bounds;
    private final TextureID texture = TextureID.shippingBinTexture;

    public ShippingBin(int gameId,int id,int x, int y) {
        Game game = GameSessionController.getInstance().getGame(gameId);
        this.id = id;
        this.bounds = new Rectangle(x, y, 1, 1);
        for (Player player :game.getAllPlayers()) {
            dailyRevenue.put(player, 0);
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
            dailyRevenue.put(player, 0);
        }

    }

    public void increaseRevenue(Player player, int revenue) {
        dailyRevenue.put(player, dailyRevenue.get(player) + revenue);
    }

    public int getId() {
        return id;
    }


    @Override
    public TextureID getTexture() {
        return texture;
    }




}
