package com.stardew.model;

import com.stardew.model.mapInfo.Placeable;
import com.stardew.model.userInfo.Coin;
import com.stardew.model.userInfo.Player;
import com.stardew.model.mapInfo.Ingredient;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.HashMap;

public class ShippingBin implements Placeable {

    private final HashMap<Player, Integer> dailyRevenue = new HashMap<>();
    private final char symbol = 'ø';
    private final Rectangle bounds;
    private final TextureID texture = TextureID.shippingBinTexture;
    //private com.badlogic.gdx.scenes.scene2d.ui.Image shippingBinImage;

    public ShippingBin(int x, int y) {

        this.bounds = new Rectangle(x, y, 1, 1);
//        for (Player player : App.getGame().getPlayers()) {
//            dailyRevenue.put(player, 0);
//        }
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
//
//    public Image getShippingBinImage() {
//        if (shippingBinImage == null) {
//            shippingBinImage = new com.badlogic.gdx.scenes.scene2d.ui.Image(new TextureRegionDrawable(getTexture()));
//            shippingBinImage.setSize(TextureID.TILE_SIZE, TextureID.TILE_SIZE);
//            shippingBinImage.addListener(new InputListener() {
//                @Override
//                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//                    createShippingBinWindow();
//                    return true;
//                }
//            });
//            shippingBinImage.setPosition(bounds.x * TextureID.TILE_SIZE, bounds.y * TextureID.TILE_SIZE);
//        }
//        return shippingBinImage;
//    }
//
//    private void createShippingBinWindow() {
//        ((GameScreenMenu) Main.getMain().getScreen()).getGameMenuInputAdapter().createShippingBinWindow(this);
//    }

    public void increaseRevenue(Player player, int revenue) {
        dailyRevenue.put(player, dailyRevenue.get(player) + revenue);
    }



    @Override
    public TextureID getTexture() {
        return texture;
    }




}
