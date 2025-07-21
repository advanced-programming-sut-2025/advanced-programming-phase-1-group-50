package com.stardew.models;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.stardew.Main;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.app.App;
import com.stardew.models.manuFactor.Ingredient;
import com.stardew.models.userInfo.Coin;
import com.stardew.models.userInfo.Player;
import com.stardew.view.GameScreenMenu;

import java.awt.*;
import java.util.HashMap;

public class ShippingBin implements Placeable {
    private final String backgroundCode = BackgroundColors.RED;
    private final String colorCode = ColorPrinter.BRIGHT_BLUE;
    private final HashMap<Player, Integer> dailyRevenue = new HashMap<>();
    private final char symbol = 'ø';
    private final Rectangle bounds;
    private final TextureRegion texture = GamePictureManager.shippingBinTexture;
    private com.badlogic.gdx.scenes.scene2d.ui.Image shippingBinImage;

    public ShippingBin(int x, int y) {

        this.bounds = new Rectangle(x, y, 1, 1);
        for (Player player : App.getGame().getPlayers()) {
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

    public Image getShippingBinImage() {
        if (shippingBinImage == null) {
            shippingBinImage = new com.badlogic.gdx.scenes.scene2d.ui.Image(new TextureRegionDrawable(getTexture()));
            shippingBinImage.setSize(GamePictureManager.TILE_SIZE, GamePictureManager.TILE_SIZE);
            shippingBinImage.addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    createShippingBinWindow();
                    return true;
                }
            });
            shippingBinImage.setPosition(bounds.x * GamePictureManager.TILE_SIZE, bounds.y * GamePictureManager.TILE_SIZE);
        }
        return shippingBinImage;
    }

    private void createShippingBinWindow() {
        ((GameScreenMenu) Main.getMain().getScreen()).getGameMenuInputAdapter().createShippingBinWindow(this);
    }

    public void increaseRevenue(Player player, int revenue) {
        dailyRevenue.put(player, dailyRevenue.get(player) + revenue);
    }

    @Override
    public String getColor() {
        return colorCode;
    }

    @Override
    public String getBackground() {
        return backgroundCode;
    }

    @Override
    public TextureRegion getTexture() {
        return texture;
    }

    @Override
    public Color getMiniMapColor() {
        return Color.PURPLE;
    }

}
