package com.stardew.models;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.stardew.models.app.App;
import com.stardew.models.manuFactor.Ingredient;
import com.stardew.models.userInfo.Coin;
import com.stardew.models.userInfo.Player;

import java.awt.*;
import java.util.HashMap;

public class ShippingBin implements Placeable{
    private final String backgroundCode = BackgroundColors.RED;
    private final String colorCode = ColorPrinter.BRIGHT_BLUE;
    private HashMap<Player,Integer> dailyRevenue = new HashMap<>();
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

    @Override
    public String getColor(){
        return colorCode;
    }

    @Override
    public String getBackground(){
        return backgroundCode;
    }

    @Override
    public TextureRegion getTexture() {
        return null;
    }

    @Override
    public Color getMiniMapColor() {
        return Color.PURPLE;
    }

}
